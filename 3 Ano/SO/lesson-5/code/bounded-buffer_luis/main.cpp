#include <stdio.h>
#include <unistd.h>
#include <stdint.h>
#include <stdlib.h>
#include <libgen.h>
#include "utils.h"
#include "thread.h"
#include "fifo.h"

bool verbose = false;
static Fifo *theFifo = NULL;

static void printUsage(FILE* fp, const char* cmd)
{
    fprintf(fp, "Synopsis %s [options]\n"
            "\t -i num   | number of items per producer (dfl: 500)\n"
            "\t -p num   | number of producers (dfl: 5)\n"
            "\t -c num   | number of consumers (dfl: 5)\n"
            "\t -V       | verbose mode\n"
            "\t -h       | help\n", cmd);
}

/*********************************************************/

void producerLifeCycle(uint32_t id, uint32_t ni)
{
    for (uint32_t i = 1; i <= ni; i++)
    {
        uint32_t v = id * 1000000 + i;
        Item item = {id, v, v};
        fifoInsert(theFifo, item);
        if (verbose)
            printf("\e[36;01mProducer %u inserted (%u,%u,%u)\e[0m\n", id, id, v, v);
    }
}

/*********************************************************/

void *producerThread(void *arg)
{
    uint32_t *p = (uint32_t*)arg;
    producerLifeCycle(*p, 500);
    thread_exit(NULL);
    return NULL;
}

/*********************************************************/

void consumerLifeCycle(uint32_t id)
{
    while (1)
    {
        Item item = fifoRetrieve(theFifo);

        // "item.id == 0" será usado como sinal de saída
        if (item.id == 0) break;

        uint32_t id1 = item.v1 / 1000000;
        uint32_t id2 = item.v2 / 1000000;
        bool raceCondition = (item.id == 0) || (item.v1 == 0) || (id1 != item.id) || (id2 != item.id) || (item.v1 != item.v2);

        if (raceCondition)
            printf("\e[31;01mConsumer %u retrieved (%u,%u,%u)\e[0m\n", id, item.id, item.v1, item.v2);
        else if (verbose)
            printf("\e[36;01mConsumer %u retrieved (%u,%u,%u)\e[0m\n", id, item.id, item.v1, item.v2);
    }

    printf("Consumer %u exiting\n", id);
}

/*********************************************************/

void *consumerThread(void *arg)
{
    uint32_t *p = (uint32_t*)arg;
    consumerLifeCycle(*p);
    thread_exit(NULL);
    return NULL;
}

/*********************************************************/

int main(int argc, char *argv[])
{
    uint32_t ni = 500, np = 5, nc = 5;
    const char *optstr = "i:p:c:Vh";
    int option;

    while ((option = getopt(argc, argv, optstr)) != -1)
    {
        switch (option)
        {
            case 'i': ni = atoi(optarg); break;
            case 'p': np = atoi(optarg); break;
            case 'c': nc = atoi(optarg); break;
            case 'V': verbose = true; break;
            case 'h': printUsage(stdout, basename(argv[0])); return 0;
            default: printUsage(stderr, basename(argv[0])); return 1;
        }
    }

    printf("Parameters: %d producers, %d consumers, %d items\n", np, nc, ni);

    theFifo = (Fifo*)mem_alloc(sizeof(Fifo));
    fifoInit(theFifo);

    pthread_t producers[np], consumers[nc];
    uint32_t producer_ids[np], consumer_ids[nc];

    for (uint32_t i = 0; i < nc; i++) {
        consumer_ids[i] = i + 1;
        thread_create(&consumers[i], NULL, consumerThread, &consumer_ids[i]);
    }

    for (uint32_t i = 0; i < np; i++) {
        producer_ids[i] = i + 1;
        thread_create(&producers[i], NULL, producerThread, &producer_ids[i]);
    }

    for (uint32_t i = 0; i < np; i++)
        thread_join(producers[i], NULL);

    printf("All producers finished.\n");

    // enviar item especial para terminar consumidores
    for (uint32_t i = 0; i < nc; i++) {
        Item exitItem = {0, 0, 0};
        fifoInsert(theFifo, exitItem);
    }

    for (uint32_t i = 0; i < nc; i++)
        thread_join(consumers[i], NULL);

    fifoDestroy(theFifo);
    printf("All done!\n");
    return 0;
}
