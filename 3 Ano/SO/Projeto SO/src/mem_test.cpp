/*
 *  MEM test
 *
 *  \author Adriana
 */

#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <libgen.h>
#include <termios.h>

#include <string>
#include <iostream>
#include <map>

#include "somm25nm.h"
#include "mem.h"

/* ******************************************** */
/* print help message */
static void printUsage(const char *cmd_name)
{
    printf("Sinopsis: %s [OPTIONS]\n"
           "  OPTIONS:\n"
           "  -i infile      --- set input file (default: stdin)\n"
           "  -o outfile     --- set log file (default: stdout)\n"
           "  -O outfile     --- set probbing file (default: stdout)\n"
           "  -P num-num     --- set probe ID range (default: 0-0)\n"
           "  -A num-num     --- add range of IDs to probe configuration\n"
           "  -R num-num     --- remove range of IDs from probe configuration\n"
           "  -b             --- set bin selection map to 100-799\n"
           "  -g             --- set bin selection map to 0-0 (default)\n"
           "  -a num-num     --- add range of IDs to bin selection map\n"
           "  -r num-num     --- remove range of IDs from bin selection map\n"
           "  -n             --- run without pause (default: pause)\n"
           "  -h             --- print this help\n", cmd_name);
}

bool noPause()
{
   return true;
}

bool termPause()
{
    static bool firstTime = true;
    static struct termios prev, cur;
    if (firstTime)
    {
        firstTime = false;
        tcgetattr(STDIN_FILENO, &prev);
        cur = prev;
        cur.c_lflag &= (~ICANON);
        tcsetattr(STDIN_FILENO, TCSANOW, &cur);
    }

    printf("Continue (Y/n)? "); fflush(stdout);
    while (true)
    {
        int res = getchar();
        if (res == '\n') break;
        printf("\n");
        if (res == 'n' or res == 'N') return false;
        if (res == 'y' or res == 'Y') break;
        printf("Bad option! Continue (Y/n)? "); fflush(stdout);
    }
    return true;
}

bool (*pauseSim)(void) = termPause;

void banner(const char *msg)
{
    fprintf(stdout, "\n\e[33;1m%s\e[0m\n\n", msg);
}

/* ******************************************** */
/* The main function */
int main(int argc, char *argv[])
{
    const char *progName = basename(argv[0]); 

    /* by default, send probing to stdout */
    FILE *fout = stdout;
    soProbeOpen(stdout, 0, 0);

    /* default values for command line options */
    const char *infile = NULL;
    (void)infile; // to avoid warning
    const char *outfile = NULL;
    (void)outfile; // to avoid warning

    /* process command line options */
    int opt;
    while ((opt = getopt(argc, argv, "i:o:O:P:A:R:nbga:r:h")) != -1)
    {
        switch (opt)
        {
            case 'i':
            {
                infile = optarg;
                break;
            }
            case 'o':          // set output file
            {
                outfile = optarg;
                if ((fout = fopen(outfile, "w")) == NULL)
                {
                    fprintf(stderr, "%s: Bad argument (\"%s\"): fail opening file.\n", progName, optarg);
                    return EXIT_FAILURE;
                }
                break;
            }
            case 'O':          /* set probbing file */
            {
                soProbeFile(optarg);
                break;
            }
            case 'P':          /* set ID range to probing system */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != (uint32_t)strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-P' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soProbeSetIDs(lower, upper);
                break;
            }
            case 'A':          /* add IDs to probe conf */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != (uint32_t)strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-A' option.\n", basename(argv[0]));
                    printUsage(basename(argv[0]));
                    return EXIT_FAILURE;
                }
                soProbeAddIDs(lower, upper);
                break;
            }
            case 'R':          /* remove IDs from probe conf */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != (uint32_t)strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-R' option.\n", basename(argv[0]));
                    printUsage(basename(argv[0]));
                    return EXIT_FAILURE;
                }
                soProbeRemoveIDs(lower, upper);
                break;
            }
            case 'n':    // set no pause mode
            {
                pauseSim = noPause;
                break;
            }
            case 'b':  // set binary mode
            {
                soBinSetIDs(0, 999);
                break;
            }
            case 'g':  // set group-only mode
            {
                soBinSetIDs(0, 0);
                break;
            }
            case 'a':          /* add IDs to bin selection map */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != (uint32_t)strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-a' option.\n", basename(argv[0]));
                    printUsage(basename(argv[0]));
                    return EXIT_FAILURE;
                }
                soBinAddIDs(lower, upper);
                break;
            }
            case 'r':          /* remove IDs from bin selection map */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != (uint32_t)strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-r' option.\n", basename(argv[0]));
                    printUsage(basename(argv[0]));
                    return EXIT_FAILURE;
                }
                soBinRemoveIDs(lower, upper);
                break;
            }
            case 'h':
            {
                printUsage(progName);
                return 0;
            }
            default:
            {
                fprintf(stderr, "%s: Wrong option (\"-%c\".\n", progName, opt);
                printUsage(progName);
                return EXIT_FAILURE;
            }
        }
    }

    /* set fout stream as no buffered */
    setvbuf(fout, NULL, _IONBF, 0);

    /* ====== TESTES DO MÓDULO MEM ====== */

    uint32_t sizes[] = { 16, 8, 4 };
    uint32_t cnt = 3;
    uint32_t initAddr = 0x10000000;
    uint32_t minLogSize = 10;

    banner("STEP 1 - memOpen()");
    try {
        memOpen(initAddr, minLogSize, sizes, cnt);
        fprintf(fout, "memOpen() succeeded.\n");
    }
    catch (const Exception &e) {
        fprintf(fout, "memOpen() failed: %s (errno=%d)\n", e.what(), e.en);
        return EXIT_FAILURE;
    }

    banner("STEP 2 - memPrint (EMPTY, CSV and normal)");
    memPrint(stdout, MemPrintGlobal, true);
    memPrint(stdout, MemPrintGlobal, false);
    pauseSim();

    banner("STEP 3 - memAlloc() some blocks");
    uint32_t a1 = memAlloc(100, 512);
    uint32_t a2 = memAlloc(200, 2048);
    uint32_t a3 = memAlloc(300, 4096);
    fprintf(fout, "Allocated a1=%#010x (pid=100)\n", a1);
    fprintf(fout, "Allocated a2=%#010x (pid=200)\n", a2);
    fprintf(fout, "Allocated a3=%#010x (pid=300)\n", a3);

    banner("STEP 4 - memPrint after allocations");
    memPrint(stdout, MemPrintGlobal, false);
    memPrint(stdout, MemPrintFree, false);
    memPrint(stdout, MemPrintOccupied, false);
    pauseSim();

    banner("STEP 5 - memBiggestFreeBlock()");
    uint32_t big1 = memBiggestFreeBlock();
    fprintf(fout, "Biggest free block: %u bytes (%#x)\n", big1, big1);
    pauseSim();

    banner("STEP 6 - memFree() one block and re-check biggest");
    if (a2 != 0) {
        fprintf(fout, "Freeing block at %#010x\n", a2);
        memFree(a2);
    }
    memPrint(stdout, MemPrintGlobal, false);
    uint32_t big2 = memBiggestFreeBlock();
    fprintf(fout, "Biggest free block now: %u bytes (%#x)\n", big2, big2);
    pauseSim();

    banner("STEP 7 - memFree() remaining blocks");
    if (a1 != 0) memFree(a1);
    if (a3 != 0) memFree(a3);
    memPrint(stdout, MemPrintGlobal, false);
    pauseSim();

    banner("STEP 8 - memFree() invalid address (expect error)");
    try {
        memFree(0xDEADBEEF);
        fprintf(fout, "memFree(0xDEADBEEF) did NOT fail (unexpected).\n");
    }
    catch (const Exception &e) {
        fprintf(fout, "memFree(0xDEADBEEF) failed as expected: %s (errno=%d)\n",
                e.what(), e.en);
    }
    pauseSim();

    banner("STEP 9 - memBiggestFreeBlock() with all free");
    uint32_t big3 = memBiggestFreeBlock();
    fprintf(fout, "Biggest free block (all free): %u bytes (%#x)\n", big3, big3);
    pauseSim();

    banner("STEP 10 - memClose() and re-open");
    memClose();
    fprintf(fout, "memClose() done.\n");

    uint32_t sizes2[] = { 2 };
    try {
        memOpen(initAddr, minLogSize, sizes2, 1);
        fprintf(fout, "memOpen() again with sizes2.\n");
    }
    catch (const Exception &e) {
        fprintf(fout, "Second memOpen() failed: %s (errno=%d)\n", e.what(), e.en);
        return EXIT_FAILURE;
    }
    memPrint(stdout, MemPrintGlobal, false);
    pauseSim();

    banner("STEP 11 - Final memClose()");
    memClose();

    banner("Bye!");
    return 0;
}
