/*
 * FEQ Test Program
 *
 * Rafael Ferreira - 119356
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

#include "somm25nm.h"

/* ******************************************** */
/* print help message (same structure as sample) */
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

/* ******************************************** */
/* pause mechanism (copied exactly) */
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

    printf("Continue (Y/n)? ");
    fflush(stdout);

    while (true)
    {
        int res = getchar();
        if (res == '\n') break;
        printf("\n");
        if (res == 'n' || res == 'N') return false;
        if (res == 'y' || res == 'Y') break;
        printf("Bad option! Continue (Y/n)? ");
        fflush(stdout);
    }
    return true;
}

bool (*pauseSim)(void) = termPause;

/* ******************************************** */
/* banner (same formatting) */
void banner(const char *msg)
{
    fprintf(stdout, "\n\e[33;1m%s\e[0m\n\n", msg);
}

/* ******************************************** */
/* The main FEQ test */
int main(int argc, char *argv[])
{
    const char *progName = basename(argv[0]);

    /* by default */
    FILE *fout = stdout;
    soProbeOpen(stdout, 0, 0);

    /* process CLI options */
    const char *infile = NULL;
    (void)infile;
    const char *outfile = NULL;
    (void)outfile;

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
            case 'o':
            {
                outfile = optarg;
                if ((fout = fopen(outfile, "w")) == NULL)
                {
                    fprintf(stderr, "%s: Bad argument (\"%s\"): fail opening file.\n",
                            progName, optarg);
                    return EXIT_FAILURE;
                }
                break;
            }
            case 'O':
            {
                soProbeFile(optarg);
                break;
            }
            case 'P':
            {
                uint32_t lower, upper, cnt = 0;
                if ((sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2)
                    || (cnt != strlen(optarg)))
                {
                    fprintf(stderr, "%s: Bad argument to '-P' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soProbeSetIDs(lower, upper);
                break;
            }
            case 'A':
            {
                uint32_t lower, upper, cnt = 0;
                if ((sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2)
                    || (cnt != strlen(optarg)))
                {
                    fprintf(stderr, "%s: Bad argument to '-A' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soProbeAddIDs(lower, upper);
                break;
            }
            case 'R':
            {
                uint32_t lower, upper, cnt = 0;
                if ((sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2)
                    || (cnt != strlen(optarg)))
                {
                    fprintf(stderr, "%s: Bad argument to '-R' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soProbeRemoveIDs(lower, upper);
                break;
            }
            case 'n':
                pauseSim = noPause;
                break;
            case 'b':
                soBinSetIDs(100, 799);
                break;
            case 'g':
                soBinSetIDs(0, 0);
                break;
            case 'a':
            {
                uint32_t lower, upper, cnt = 0;
                if ((sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2)
                    || (cnt != strlen(optarg)))
                {
                    fprintf(stderr, "%s: Bad argument to '-a' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soBinAddIDs(lower, upper);
                break;
            }
            case 'r':
            {
                uint32_t lower, upper, cnt = 0;
                if ((sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2)
                    || (cnt != strlen(optarg)))
                {
                    fprintf(stderr, "%s: Bad argument to '-r' option.\n", progName);
                    printUsage(progName);
                    return EXIT_FAILURE;
                }
                soBinRemoveIDs(lower, upper);
                break;
            }
            case 'h':
                printUsage(progName);
                return 0;
            default:
                fprintf(stderr, "%s: Wrong option (\"-%c\").\n", progName, opt);
                printUsage(progName);
                return EXIT_FAILURE;
        }
    }

    /* no-buffer output */
    setvbuf(fout, NULL, _IONBF, 0);

    /* FEQ TEST SEQUENCE */

    banner("Starting the FEQ module");
    feqOpen();

    banner("Printing FEQ queue in CSV mode (empty)");
    feqPrint(stdout, true);

    banner("Printing FEQ queue in normal mode (empty)");
    feqPrint(stdout);

    /* Insert events */
    banner("Inserting events into FEQ");

    feqInsert(5.1, DISPATCH,     0x01010101);
    feqInsert(2.0, EXIT,         0x02020202);
    feqInsert(2.0, WAIT_EVENT,   0x03030303);
    feqInsert(2.0, ADMIT,        0x04040404);
    feqInsert(2.0, DISPATCH,     0x05050505);
    feqInsert(4.5, PREEMPT,      0x06060606);
    feqInsert(4.5, SUBMIT,       0x07070707);

    banner("Printing FEQ in CSV mode");
    feqPrint(stdout, true);

    banner("Printing FEQ in normal mode");
    feqPrint(stdout);

    /* Retrieve events */
    banner("Retrieving all events (non-blocking)");

    while (true)
    {
        double time;
        FeqEventType type;
        uint32_t xid;

        try
        {
            bool ok = feqRetrieve(&time, &type, &xid, false);
            if (!ok) break; // non-blocking never returns false
            fprintf(stdout,
                    "Retrieved: time=%.1f type=%d xid=0x%08x\n",
                    time, type, xid);
        }
        catch (Exception &e)
        {
            fprintf(stdout,
                    "Exception caught: %s (errno=%d func=%s)\n",
                    e.what(), e.en, e.func);
            break;
        }
    }

    banner("Printing FEQ after retrieval");
    feqPrint(stdout);

    /* Test blocking retrieve */
    banner("Testing blocking retrieve on empty FEQ");

    {
        double t; FeqEventType tp; uint32_t x;
        bool ok = false;
        try {
            ok = feqRetrieve(&t, &tp, &x, true);
        }
        catch (Exception &e)
        {
            fprintf(stdout,
                    "Exception caught: %s (errno=%d func=%s)\n",
                    e.what(), e.en, e.func);
        }
        fprintf(stdout, "Blocking retrieve result: %s\n", ok ? "true" : "false");
    }

    /* Close FEQ */
    banner("Closing FEQ module");
    feqClose();

    /* Test print after close */
    banner("FEQ closed — all FEQ operations are now forbidden");
    fprintf(stdout, "Skipping feqPrint() because module is closed.\n");


    banner("Bye!");
    return 0;
}
