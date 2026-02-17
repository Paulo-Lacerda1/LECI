#include <dbc.h>
#include <string.h>
#include "pfifo.h"

static void print_pfifo(PriorityFIFO* pfifo);
static int empty_pfifo(PriorityFIFO* pfifo);

static int full_pfifo(PriorityFIFO* pfifo);
/* --------------------------------------- */
// Inicializa estrutura e mecanismos de sincronização
void init_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   memset(pfifo->array, 0, sizeof(pfifo->array));
   pfifo->inp = pfifo->out = pfifo->cnt = 0;
   pfifo->is_closed = 0;

   mutex_init(&pfifo->access, NULL);
   cond_init(&pfifo->notFull, NULL);
   cond_init(&pfifo->notEmpty, NULL);
}

/* --------------------------------------- */
// Liberta recursos no final
void term_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   require(is_closed_pfifo(pfifo), "FIFO open");

   mutex_destroy(&pfifo->access);
   cond_destroy(&pfifo->notFull);
   cond_destroy(&pfifo->notEmpty);
}

/* --------------------------------------- */
// Inserção (produtor)
void insert_pfifo(PriorityFIFO* pfifo, int id, int priority)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   require(id >= 0 && id <= MAX_ID, "invalid id");
   require(priority > 0 && priority <= MAX_PRIORITY, "invalid priority value");

   mutex_lock(&pfifo->access);   // 🔹 Entrada na região crítica

   // 🔹 Enquanto a FIFO estiver aberta e cheia → espera
   while (!pfifo->is_closed && full_pfifo(pfifo))
      cond_wait(&pfifo->notFull, &pfifo->access);

   // 🔹 Se a FIFO foi fechada durante a espera, sai sem inserir
   if (pfifo->is_closed) {
      mutex_unlock(&pfifo->access);
      return;
   }
   

   // Inserção normal (mantém ordem de prioridade)
   int idx = pfifo->inp;
   int prev = (idx + FIFO_MAXSIZE - 1) % FIFO_MAXSIZE;
   while ((idx != pfifo->out) && (pfifo->array[prev].priority > priority)) {
      pfifo->array[idx] = pfifo->array[prev];
      idx = prev;
      prev = (idx + FIFO_MAXSIZE - 1) % FIFO_MAXSIZE;
   }

   pfifo->array[idx].id = id;
   pfifo->array[idx].priority = priority;
   pfifo->inp = (pfifo->inp + 1) % FIFO_MAXSIZE;
   pfifo->cnt++;

   // 🔹 Acorda consumidores que possam estar à espera
   cond_broadcast(&pfifo->notEmpty);

   mutex_unlock(&pfifo->access);  // 🔹 Sai da região crítica
}

/* --------------------------------------- */
// Retirada (consumidor)
int retrieve_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");

   mutex_lock(&pfifo->access);   

   //Enquanto a FIFO estiver aberta e vazia → espera
   while (!pfifo->is_closed && empty_pfifo(pfifo))
      cond_wait(&pfifo->notEmpty, &pfifo->access);


   // Só termina se a FIFO estiver FECHADA e VAZIA
   if (pfifo->cnt == 0 && pfifo->is_closed) {
      mutex_unlock(&pfifo->access);
      return -1;   
   }

   // Retira o elemento mais antigo
   int result = pfifo->array[pfifo->out].id;
   pfifo->array[pfifo->out].id = INVALID_ID;
   pfifo->array[pfifo->out].priority = INVALID_PRIORITY;
   pfifo->out = (pfifo->out + 1) % FIFO_MAXSIZE;
   pfifo->cnt--;

   // Atualiza prioridades dos restantes
   int idx = pfifo->out;
   for (int i = 1; i <= pfifo->cnt; i++) {
      if (pfifo->array[idx].priority > 1 && pfifo->array[idx].priority != INVALID_PRIORITY)
         pfifo->array[idx].priority--;
      idx = (idx + 1) % FIFO_MAXSIZE;
   }

   // 🔹 Acorda produtores que possam estar bloqueados (agora há espaço)
   cond_broadcast(&pfifo->notFull);

   mutex_unlock(&pfifo->access);  // 🔹 Sai da região crítica
   return result;
}

/* --------------------------------------- */
// Fecha a FIFO de forma segura
void close_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   require(!is_closed_pfifo(pfifo), "FIFO already closed");

   mutex_lock(&pfifo->access);

   pfifo->is_closed = 1;  // 🔹 Marca como fechada

   cond_broadcast(&pfifo->notFull);    //acorda as outras threads
   cond_broadcast(&pfifo->notEmpty);

   mutex_unlock(&pfifo->access);
}

/* --------------------------------------- */
// Retorna se a FIFO está fechada
int is_closed_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   return pfifo->is_closed;
}

/* --------------------------------------- */
// Funções auxiliares (internas)
static int empty_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   return pfifo->cnt == 0;
}

/* --------------------------------------- */
static int full_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");
   return pfifo->cnt == FIFO_MAXSIZE;
}

/* --------------------------------------- */
static void print_pfifo(PriorityFIFO* pfifo)
{
   require(pfifo != NULL, "NULL pointer to FIFO");

   int idx = pfifo->out;
   for (int i = 1; i <= pfifo->cnt; i++) {
      check_valid_patient_id(pfifo->array[idx].id);
      check_valid_priority(pfifo->array[idx].priority);
      printf("[%02d] value = %d, priority = %d\n",
             i, pfifo->array[idx].id, pfifo->array[idx].priority);
      idx = (idx + 1) % FIFO_MAXSIZE;
   }
}
