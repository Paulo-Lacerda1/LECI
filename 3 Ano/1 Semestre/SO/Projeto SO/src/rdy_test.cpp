#include "rdy.h"
#if defined(__has_include)
#if __has_include("somm25.h")
#include "somm25.h"
#else
#include "somm25nm.h"
#endif
#else
#include "somm25nm.h"
#endif
#include <cstdio>
#include <cstring>
#include <unistd.h>

struct InsertCase
{
    uint16_t pid;
    double curTime;
    double runTime;
};

// Impressão do estado interno, capturando exceções do módulo
static void dumpState()
{
    printf("----- RDY state -----\n");
    try
    {
        rdyPrint(stdout);
    }
    catch (const Exception &e)
    {
        printf("rdyPrint failed: %s (errno=%d)\n", e.what(), e.en);
    }
    printf("---------------------\n");
}

// Insere um conjunto de casos de teste na fila RDY
static void insertSet(const InsertCase *cases, int count)
{
    int idx;
    for (idx = 0; idx < count; idx += 1)
    {
        rdyInsert(cases[idx].pid, cases[idx].curTime, cases[idx].runTime);
        printf("Inserted pid=%u (curTime=%.1f, runTime=%.1f)\n",
               cases[idx].pid, cases[idx].curTime, cases[idx].runTime);
    }
}

static void printUsage(const char *cmd)
{
    printf("Usage: %s [OPTIONS]\n"
           "  -b             --- use binary functions 0-999 (includes RDY)\n"
           "  -g             --- use group functions (clears bin map)\n"
           "  -a num-num     --- add range to bin selection map\n"
           "  -r num-num     --- remove range from bin selection map\n"
           "  -h             --- show this help\n",
           cmd);
}

int main(int argc, char *argv[])
{
    // Permite escolher funções binárias/grupo via opções de linha de comando
    int opt;
    while ((opt = getopt(argc, argv, "bga:r:h")) != -1)
    {
        switch (opt)
        {
            case 'b':
                soBinSetIDs(0, 999);
                break;
            case 'g':
                soBinSetIDs(0, 0);
                break;
            case 'a':
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ((sscanf(optarg, "%u%*[,-]%u %n", &lower, &upper, &cnt) != 2) ||
                    (cnt != strlen(optarg)))
                {
                    printUsage(argv[0]);
                    return 1;
                }
                soBinAddIDs(lower, upper);
                break;
            }
            case 'r':
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ((sscanf(optarg, "%u%*[,-]%u %n", &lower, &upper, &cnt) != 2) ||
                    (cnt != strlen(optarg)))
                {
                    printUsage(argv[0]);
                    return 1;
                }
                soBinRemoveIDs(lower, upper);
                break;
            }
            case 'h':
            default:
                printUsage(argv[0]);
                return 0;
        }
    }

    static const InsertCase baseSet[5] = {
        {1, 10.0, 5.0},
        {2, 12.0, 2.0},
        {3, 14.0, 8.0},
        {4, 16.0, 3.0},
        {5, 18.0, 1.0}
    };

    // Teste de abertura e inserções usando SPN
    printf("\n%s\n", "STEP 1 - Open module with SPN");
    rdyOpen(SPN);
    printf("RDY opened with SPN policy.\n");
    dumpState();

    printf("\n%s\n", "STEP 2 - Insert several processes");
    insertSet(baseSet, 5);
    dumpState();

    printf("\n%s\n", "STEP 3 - Retrieve using SPN");
    double spnCurTime = 30.0;
    while (true)
    {
        uint16_t pid = rdyRetrieve(spnCurTime);
        if (pid == 0)
        {
            printf("SPN retrieve returned 0 at time %.1f (queue empty).\n", spnCurTime);
            break;
        }
        printf("SPN retrieved pid=%u at time %.1f.\n", pid, spnCurTime);
        dumpState();
        spnCurTime += 1.0;
    }
    dumpState();

    // Repete cenário anterior para HRRN
    printf("\n%s\n", "STEP 4 - Repeat for HRRN");
    rdyClose();
    printf("RDY closed after SPN test.\n");

    static InsertCase hrrnSet[5];
    int idx;
    for (idx = 0; idx < 5; idx += 1)
    {
        hrrnSet[idx].pid = baseSet[idx].pid;
        hrrnSet[idx].runTime = baseSet[idx].runTime;
        hrrnSet[idx].curTime = 50.0 + idx * 3.0;
    }

    rdyOpen(HRRN);
    printf("RDY opened with HRRN policy.\n");
    insertSet(hrrnSet, 5);
    dumpState();

    double hrrnCurTime = 80.0;
    while (true)
    {
        uint16_t pid = rdyRetrieve(hrrnCurTime);
        if (pid == 0)
        {
            printf("HRRN retrieve returned 0 at time %.1f (queue empty).\n", hrrnCurTime);
            break;
        }
        printf("HRRN retrieved pid=%u at time %.1f.\n", pid, hrrnCurTime);
        dumpState();
        hrrnCurTime += 1.5;
    }
    dumpState();
    rdyClose();
    printf("RDY closed after HRRN test.\n");

    // Exercita ciclos open/close com inserções mínimas
    printf("\n%s\n", "STEP 5 - Multiple open/close cycles");
    int cycle;
    for (cycle = 0; cycle < 3; cycle += 1)
    {
        rdyOpen(SPN);
        printf("Cycle %d: RDY opened for cleanup test.\n", cycle + 1);
        double cycleBaseTime = 200.0 + cycle * 5.0;
        InsertCase tmp = { (uint16_t)(300 + cycle), cycleBaseTime, 2.0 + cycle };
        rdyInsert(tmp.pid, tmp.curTime, tmp.runTime);
        printf("Cycle %d: inserted pid=%u (curTime=%.1f, runTime=%.1f).\n",
               cycle + 1, tmp.pid, tmp.curTime, tmp.runTime);
        dumpState();
        rdyClose();
        printf("Cycle %d: RDY closed (memory released).\n", cycle + 1);
    }

    // Recuperação em lista vazia após reabrir
    printf("\n%s\n", "STEP 6 - Empty list retrieval");
    rdyOpen(SPN);
    printf("RDY reopened to test empty retrieval.\n");
    uint16_t emptyPid = rdyRetrieve(250.0);
    printf("Empty list retrieve returned pid=%u.\n", emptyPid);
    dumpState();
    rdyClose();
    printf("RDY closed after empty list test.\n");

    return 0;
}
