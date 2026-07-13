/*
 *  Paulo
 */

#include "somm25nm.h"

namespace
{
    struct ParsedJob
    {
        uint32_t jid;
        double submissionTime;
        uint32_t memSize;
        double bursts[JOB_MAX_BURSTS];
    };

    struct JobBuffer
    {
        ParsedJob *data;
        size_t count;
        size_t cap;
        JobBuffer() : data(NULL), count(0), cap(0) {}
    };

    static int is_space(char c)
    {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\v' || c == '\f';
    }

    static int is_digit(char c)
    {
        return c >= '0' && c <= '9';
    }

    static int hex_value(char c)
    {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'f')
            return 10 + (c - 'a');
        if (c >= 'A' && c <= 'F')
            return 10 + (c - 'A');
        return -1;
    }

    // Remove espacos no inicio/fim e devolve o primeiro caracter nao branco.
    static char *trim(char *s)
    {
        while (*s != '\0' && is_space(*s))
            s++;
        char *end = s;
        while (*end != '\0')
            end++;
        while (end > s && is_space(*(end - 1)))
            end--;
        *end = '\0';
        return s;
    }

    static void zero_bursts(double *bursts)
    {
        for (size_t i = 0; i < JOB_MAX_BURSTS; i++)
            bursts[i] = 0.0;
    }

    // Garante que o array dinamico consegue armazenar pelo menos "needed" jobs.
    static void ensure_capacity(JobBuffer &jobs, size_t needed)
    {
        if (needed <= jobs.cap)
            return;
        size_t newCap = jobs.cap == 0 ? 16 : jobs.cap * 2;
        while (newCap < needed)
            newCap *= 2;
        ParsedJob *newData = (ParsedJob *)realloc(jobs.data, newCap * sizeof(ParsedJob));
        if (newData == NULL)
            throw Exception(ENOMEM, "simLoadBatch");
        jobs.data = newData;
        jobs.cap = newCap;
    }

    // Procura duplicados linearmente; os jobs nao estao ordenados por JID.
    static int jid_exists(const JobBuffer &jobs, uint32_t jid)
    {
        for (size_t i = 0; i < jobs.count; i++)
        {
            if (jobs.data[i].jid == jid)
                return 1;
        }
        return 0;
    }

    // Faz parse de exatamente 8 digitos hex, sem prefixos/sufixos.
    static int parse_hex_jid(const char *s, uint32_t *out)
    {
        if (s[0] == '\0')
            return 0;
        uint32_t value = 0;
        for (int i = 0; i < 8; i++)
        {
            // Acumula cada digito hex num valor de 32 bits.
            int digit = hex_value(s[i]);
            if (digit < 0)
                return 0;
            value = (value << 4) | (uint32_t)digit;
        }
        if (s[8] != '\0')
            return 0;
        *out = value;
        return 1;
    }

    // Faz parse de inteiro sem sinal com prefixo 0x/0X opcional.
    static int parse_uint32(const char *s, uint32_t *out)
    {
        if (s[0] == '\0')
            return 0;
        int base = 10;
        const char *p = s;
        if (p[0] == '0' && (p[1] == 'x' || p[1] == 'X'))
        {
            // Aceita hexadecimal com prefixo 0x/0X.
            base = 16;
            p += 2;
        }
        if (*p == '\0')
            return 0;
        unsigned long long value = 0;
        while (*p != '\0')
        {
            // Constroi o valor verificando digitos invalidos e overflow.
            int digit = (base == 16) ? hex_value(*p) : (is_digit(*p) ? *p - '0' : -1);
            if (digit < 0)
                return 0;
            value = value * (unsigned long long)base + (unsigned long long)digit;
            if (value > 0xFFFFFFFFULL)
                return 0;
            p++;
        }
        *out = (uint32_t)value;
        return 1;
    }

    // Faz parse de numero decimal nao negativo com parte fracionaria opcional.
    static int parse_double_nonneg(const char *s, double *out)
    {
        if (s[0] == '\0')
            return 0;
        const char *p = s;
        double value = 0.0;
        int has_digits = 0;
        // Faz parse da parte inteira.
        while (is_digit(*p))
        {
            value = value * 10.0 + (double)(*p - '0');
            p++;
            has_digits = 1;
        }
        if (*p == '.')
        {
            // Faz parse da parte fracionaria.
            p++;
            double frac = 0.0;
            double scale = 1.0;
            while (is_digit(*p))
            {
                frac = frac * 10.0 + (double)(*p - '0');
                scale *= 10.0;
                p++;
                has_digits = 1;
            }
            value += frac / scale;
        }
        if (!has_digits)
            return 0;
        if (*p != '\0')
            return 0;
        *out = value;
        return 1;
    }

    static void syntax_error() // Função que nunca retorna nada
    {
        throw Exception(EINVAL, "simLoadBatch");
    }
}

namespace group
{
    void simLoadBatch(FILE *fin, uint32_t maxMemSize)
    {
        require(fin != NULL, "fin must be a valid file stream");
        require(maxMemSize > 0, "Invalid maxMemSize");

        JobBuffer jobs;
        char *line = NULL;
        size_t lineCap = 0;
        double lastSubmission = 0.0;
        int haveLast = 0;

        while (1)
        {
            long lineLen = getline(&line, &lineCap, fin);
            if (lineLen == -1)
                break;
            // Le a linha completa (sem limite fixo) e remove terminadores.
            if (lineLen > 0 && line[lineLen - 1] == '\n')
                line[--lineLen] = '\0';
            if (lineLen > 0 && line[lineLen - 1] == '\r')
                line[--lineLen] = '\0';

            // Ignora linhas vazias e linhas de comentario.
            char *start = trim(line);
            if (*start == '\0')
                continue;
            if (*start == '%')
                continue;

            char *fields[4];
            int fieldCount = 0;
            fields[fieldCount++] = start;
            // Separa a linha em exatamente 4 campos separados por ';'.
            for (char *p = start; *p != '\0'; p++)
            {
                if (*p == ';')
                {
                    *p = '\0';
                    if (fieldCount >= 4)
                        syntax_error();
                    fields[fieldCount++] = p + 1;
                }
            }
            if (fieldCount != 4)
                syntax_error();

            // Limpa cada campo e falha se algum ficar vazio.
            for (int i = 0; i < 4; i++)
            {
                fields[i] = trim(fields[i]);
                if (*fields[i] == '\0')
                    syntax_error();
            }

            // Campo 1: JID (8 hex) e detecao de duplicados.
            uint32_t jid = 0;
            if (!parse_hex_jid(fields[0], &jid))
                syntax_error();
            if (jid_exists(jobs, jid))
                syntax_error();

            // Campo 2: submission time (nao negativo, nao decrescente).
            double submissionTime = 0.0;
            if (!parse_double_nonneg(fields[1], &submissionTime))
                syntax_error();
            if (submissionTime < 0.0)
                syntax_error();
            if (haveLast && submissionTime < lastSubmission)
                syntax_error();

            // Campo 3: memory size (positivo e dentro do limite).
            uint32_t memSize = 0;
            if (!parse_uint32(fields[2], &memSize))
                syntax_error();
            if (memSize == 0 || memSize > maxMemSize)
                syntax_error();

            double bursts[JOB_MAX_BURSTS];
            zero_bursts(bursts);
            int burstCount = 0;
            // Faz parse do perfil de bursts: reais positivos separados por virgula.
            char *tokenStart = fields[3];
            for (char *p = fields[3]; ; p++)
            {
                if (*p == ',' || *p == '\0')
                {
                    char saved = *p;
                    *p = '\0';
                    char *token = trim(tokenStart);
                    if (*token == '\0')
                        syntax_error();
                    double burstValue = 0.0;
                    if (!parse_double_nonneg(token, &burstValue))
                        syntax_error();
                    if (burstValue <= 0.0)
                        syntax_error();
                    if (burstCount >= JOB_MAX_BURSTS)
                        syntax_error();
                    bursts[burstCount++] = burstValue;
                    if (saved == '\0')
                        break;
                    tokenStart = p + 1;
                }
            }
            // O perfil de bursts nao pode estar vazio e tem de ter tamanho impar.
            if (burstCount == 0 || (burstCount % 2) == 0)
                syntax_error();

            // Guarda o job; a insercao so acontece apos validacao completa.
            ensure_capacity(jobs, jobs.count + 1);
            ParsedJob *entry = &jobs.data[jobs.count++];
            entry->jid = jid;
            entry->submissionTime = submissionTime;
            entry->memSize = memSize;
            for (size_t i = 0; i < JOB_MAX_BURSTS; i++)
                entry->bursts[i] = bursts[i];

            // Guarda a ultima submission time para garantir ordem.
            lastSubmission = submissionTime;
            haveLast = 1;
        }

        // Deteta erros de I/O no ficheiro apos a leitura.
        if (ferror(fin))
        {
            if (line != NULL)
                free(line);
            if (jobs.data != NULL)
                free(jobs.data);
            throw Exception(EIO, "simLoadBatch");
        }

        // Apos parse com sucesso, insere jobs e agenda eventos SUBMIT.
        for (size_t i = 0; i < jobs.count; i++)
        {
            jobInsert(jobs.data[i].jid, jobs.data[i].submissionTime, jobs.data[i].memSize, jobs.data[i].bursts);
            feqInsert(jobs.data[i].submissionTime, SUBMIT, jobs.data[i].jid);
        }
        if (line != NULL)
            free(line);
        if (jobs.data != NULL)
            free(jobs.data);
    }
} // end of namespace group
