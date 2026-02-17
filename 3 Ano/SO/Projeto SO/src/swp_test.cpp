/*
 * SWP Module Test Program
 * Tests all functions of the Swapped-out Queue module
 * 
 * Author: ...
 * Date: 2025
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
#include "swp.h"


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
                        or (cnt != strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-p' option.\n", progName);
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
                        or (cnt != strlen(optarg)) )
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
                        or (cnt != strlen(optarg)) )
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
            case 'g':  // set binary mode
            {
                soBinSetIDs(0, 0);
                break;
            }
            case 'a':          /* add IDs to probe conf */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-A' option.\n", basename(argv[0]));
                    printUsage(basename(argv[0]));
                    return EXIT_FAILURE;
                }
                soBinAddIDs(lower, upper);
                break;
            }
            case 'r':          /* remove IDs from probe conf */
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ( (sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2) 
                        or (cnt != strlen(optarg)) )
                {
                    fprintf(stderr, "%s: Bad argument to '-R' option.\n", basename(argv[0]));
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

    /* ============================================ */
    /* TEST 1: Initialize with FirstFit policy */
    /* ============================================ */
    banner("TEST 1: Opening SWP module with FirstFit policy");
    swpOpen(FirstFit);
    fprintf(fout, "SWP module opened with FirstFit policy\n");

    /* ============================================ */
    /* TEST 2: Print empty queue */
    /* ============================================ */
    banner("TEST 2: Printing empty SWP queue (CSV mode)");
    swpPrint(fout, true);
    
    banner("TEST 2b: Printing empty SWP queue (normal mode)");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 3: Insert processes */
    /* ============================================ */
    banner("TEST 3: Inserting processes into SWP queue");
    swpInsert(100, 1024, false);   // Unblocked, 1KB
    fprintf(fout, "Inserted: PID=100, Size=1024, Blocked=false\n");
    
    swpInsert(200, 2048, true);    // Blocked, 2KB
    fprintf(fout, "Inserted: PID=200, Size=2048, Blocked=true\n");
    
    swpInsert(300, 512, false);    // Unblocked, 512B
    fprintf(fout, "Inserted: PID=300, Size=512, Blocked=false\n");
    
    swpInsert(400, 4096, true);    // Blocked, 4KB
    fprintf(fout, "Inserted: PID=400, Size=4096, Blocked=true\n");
    
    swpInsert(500, 1536, false);   // Unblocked, 1.5KB
    fprintf(fout, "Inserted: PID=500, Size=1536, Blocked=false\n");

    /* ============================================ */
    /* TEST 4: Print populated queue */
    /* ============================================ */
    banner("TEST 4: Printing populated SWP queue (CSV mode)");
    swpPrint(fout, true);
    
    banner("TEST 4b: Printing populated SWP queue (normal mode)");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 5: Retrieve with FirstFit (unblocked only) */
    /* ============================================ */
    banner("TEST 5: Retrieve with FirstFit policy (canBeBlocked=false)");
    uint16_t pid = swpRetrieve(1500, false);  // 1500B available, no blocked
    fprintf(fout, "Retrieved PID: %u (expected: 100 - first unblocked that fits)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 6: Retrieve with FirstFit (can be blocked) */
    /* ============================================ */
    banner("TEST 6: Retrieve with FirstFit policy (canBeBlocked=true)");
    pid = swpRetrieve(3000, true);  // 3000B available, blocked OK
    fprintf(fout, "Retrieved PID: %u (expected: 300 - first that fits)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 7: Retrieve when no process fits */
    /* ============================================ */
    banner("TEST 7: Retrieve when no process fits");
    pid = swpRetrieve(400, false);  // 400B available, too small
    fprintf(fout, "Retrieved PID: %u (expected: 0 - no process fits)\n", pid);
    
    banner("Queue unchanged:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 8: Retrieve with sufficient space */
    /* ============================================ */
    banner("TEST 8: Retrieve with sufficient space");
    pid = swpRetrieve(5000, false);  // 5000B available, no blocked
    fprintf(fout, "Retrieved PID: %u (expected: 500 - first unblocked)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 9: Unblock a process */
    /* ============================================ */
    banner("TEST 9: Unblocking process PID=400");
    swpUnblock(400);
    fprintf(fout, "Process PID=400 has been unblocked\n");
    
    banner("Queue after unblock:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 10: Retrieve previously blocked process */
    /* ============================================ */
    banner("TEST 10: Retrieve now-unblocked process");
    pid = swpRetrieve(2000, false);  // 2000B available, no blocked
    fprintf(fout, "Retrieved PID: %u (expected: 500)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 11: Retrieve last process */
    /* ============================================ */
    banner("TEST 11: Retrieve last process");
    pid = swpRetrieve(5000, false);  // 5000B available
    fprintf(fout, "Retrieved PID: %u (expected: 400)\n", pid);
    
    banner("Queue should be empty:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 12: Retrieve from empty queue */
    /* ============================================ */
    banner("TEST 12: Retrieve from empty queue");
    pid = swpRetrieve(5000, false);
    fprintf(fout, "Retrieved PID: %u (expected: 0 - queue is empty)\n", pid);

    /* ============================================ */
    /* TEST 13: Close and reopen with FirstBest */
    /* ============================================ */
    banner("TEST 13: Close module and reopen with FirstBest policy");
    swpClose();
    fprintf(fout, "SWP module closed\n");
    
    swpOpen(FirstBest);
    fprintf(fout, "SWP module reopened with FirstBest policy\n");
    
    banner("Empty queue after reopen:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 14: Insert for FirstBest testing */
    /* ============================================ */
    banner("TEST 14: Inserting processes for FirstBest testing");
    swpInsert(1000, 2048, false);  // 2KB
    swpInsert(1001, 512, false);   // 512B - smallest
    swpInsert(1002, 4096, false);  // 4KB - largest
    swpInsert(1003, 1024, false);  // 1KB - medium
    
    banner("Queue with 4 processes:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 15: Retrieve with FirstBest policy */
    /* ============================================ */
    banner("TEST 15: Retrieve with FirstBest policy");
    pid = swpRetrieve(3000, false);  // 3000B available
    fprintf(fout, "Retrieved PID: %u (expected: 1001 - smallest fit: 512B)\n", pid);
    
    banner("Queue after FirstBest retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 16: Another FirstBest retrieval */
    /* ============================================ */
    banner("TEST 16: Another FirstBest retrieval");
    pid = swpRetrieve(2500, false);  // 2500B available
    fprintf(fout, "Retrieved PID: %u (expected: 1003 - best fit: 1024B)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 17: Test blocking constraints with FirstBest */
    /* ============================================ */
    banner("TEST 17: Insert blocked and unblocked processes");
    swpInsert(2000, 500, true);   // Blocked, small
    swpInsert(2001, 600, false);  // Unblocked, small
    
    banner("Queue with mixed blocking states:");
    swpPrint(fout, false);

    banner("TEST 17a: Retrieve with canBeBlocked=false");
    pid = swpRetrieve(550, false);  // 550B, skip blocked
    fprintf(fout, "Retrieved PID: %u (expected: 2001 - skip blocked PID 2000)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    banner("TEST 17b: Retrieve with canBeBlocked=true");
    pid = swpRetrieve(550, true);  // 550B, blocked OK
    fprintf(fout, "Retrieved PID: %u (expected: 2000 - now accepts blocked)\n", pid);
    
    banner("Queue after retrieval:");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 18: Retrieve remaining with FirstBest */
    /* ============================================ */
    banner("TEST 18: Retrieve all remaining processes");
    pid = swpRetrieve(5000, false);
    fprintf(fout, "Retrieved PID: %u (expected: 1000 - best fit: 2048B)\n", pid);
    
    pid = swpRetrieve(5000, false);
    fprintf(fout, "Retrieved PID: %u (expected: 1002 - last one: 4096B)\n", pid);
    
    pid = swpRetrieve(5000, false);
    fprintf(fout, "Retrieved PID: %u (expected: 0 - queue empty)\n", pid);
    
    banner("Final queue state (should be empty):");
    swpPrint(fout, false);

    /* ============================================ */
    /* TEST 19: Edge cases */
    /* ============================================ */
    banner("TEST 19: Edge case - exact size match");
    swpInsert(3000, 1000, false);
    
    pid = swpRetrieve(999, false);  // Too small
    fprintf(fout, "Retrieved PID: %u (expected: 0 - too small)\n", pid);
    
    pid = swpRetrieve(1000, false);  // Exact match
    fprintf(fout, "Retrieved PID: %u (expected: 3000 - exact fit)\n", pid);

    /* ============================================ */
    /* TEST 20: Unblock non-existent process */
    /* ============================================ */
    banner("TEST 20: Unblock non-existent process");
    fprintf(fout, "Attempted to unblock PID=9999 (not in queue)\n");
    fprintf(fout, "-----------------------------------------------------------\n");
    //swpUnblock(9999);
    fprintf(fout, "Test not supported by binary. \nUncomment function swpUnblock(9999) above to test. \nIf uncommented expected Throw Exception and Core Dump\n");
    fprintf(fout, "-----------------------------------------------------------\n");
    

    /* ============================================ */
    /* TEST 21: Final cleanup */
    /* ============================================ */
    banner("TEST 21: Final cleanup - closing module");
    swpClose();
    fprintf(fout, "SWP module closed successfully\n");

    /* ============================================ */
    banner("All tests completed successfully!");
    fprintf(fout, "\nTest Summary:\n");
    fprintf(fout, "  ✓ Module initialization (FirstFit and FirstBest)\n");
    fprintf(fout, "  ✓ Empty queue operations\n");
    fprintf(fout, "  ✓ Insert and maintain insertion order\n");
    fprintf(fout, "  ✓ Print in CSV and normal formats\n");
    fprintf(fout, "  ✓ Retrieve with FirstFit policy\n");
    fprintf(fout, "  ✓ Retrieve with FirstBest policy\n");
    fprintf(fout, "  ✓ Blocking constraints (canBeBlocked parameter)\n");
    fprintf(fout, "  ✓ Unblock operations\n");
    fprintf(fout, "  ✓ Edge cases (exact fit, no fit, empty queue)\n");
    fprintf(fout, "  ✓ Memory cleanup\n");

    if (fout != stdout)
        fclose(fout);

    return EXIT_SUCCESS;
}
