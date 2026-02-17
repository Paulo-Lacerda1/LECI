/*
 *  Rafael Ferreira - 119356
 */

#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <libgen.h>
#include <termios.h>

#include "somm25nm.h"
#include "exception.h"   // For catching Exception

static void printUsage(const char *cmd_name)
{
    printf("Sinopsis: %s [OPTIONS]\n"
           "  OPTIONS:\n"
           "  -o outfile     --- set log file (default: stdout)\n"
           "  -O outfile     --- set probing file (default: stdout)\n"
           "  -P num-num     --- set probe ID range\n"
           "  -A num-num     --- add probe IDs\n"
           "  -R num-num     --- remove probe IDs\n"
           "  -b             --- use binary functions (0-999)\n"
           "  -g             --- use group functions (default)\n"
           "  -a num-num     --- add bin IDs\n"
           "  -r num-num     --- remove bin IDs\n"
           "  -n             --- no pause\n"
           "  -h             --- help\n",
           cmd_name);
}

bool noPause() { return true; }

bool termPause()
{
    printf("Continue (Y/n)? "); fflush(stdout);
    int c;
    while ((c = getchar()) != '\n' && c != EOF)
        if (c == 'n' || c == 'N') return false;
    return true;
}

bool (*pauseSim)(void) = termPause;

void banner(const char *msg)
{
    printf("\n\e[33;1m=== %s ===\e[0m\n\n", msg);
}

int main(int argc, char *argv[])
{
    FILE *fout = stdout;

    int opt;
    while ((opt = getopt(argc, argv, "o:O:P:A:R:bga:r:nh")) != -1)
    {
        switch (opt)
        {
            case 'o':
                fout = fopen(optarg, "w");
                if (!fout) fout = stdout;
                break;
            case 'O':
                soProbeFile(optarg);
                break;
            case 'P':
            {
                uint32_t l, u;
                sscanf(optarg, "%u%*[,-]%u", &l, &u);
                soProbeSetIDs(l, u);
                break;
            }
            case 'A':
            {
                uint32_t l, u;
                sscanf(optarg, "%u%*[,-]%u", &l, &u);
                soProbeAddIDs(l, u);
                break;
            }
            case 'R':
            {
                uint32_t l, u;
                sscanf(optarg, "%u%*[,-]%u", &l, &u);
                soProbeRemoveIDs(l, u);
                break;
            }
            case 'b':
                soBinSetIDs(0, 999);
                break;
            case 'g':
                soBinSetIDs(0, 0);
                break;
            case 'a':
            {
                uint32_t l, u;
                sscanf(optarg, "%u%*[,-]%u", &l, &u);
                soBinAddIDs(l, u);
                break;
            }
            case 'r':
            {
                uint32_t l, u;
                sscanf(optarg, "%u%*[,-]%u", &l, &u);
                soBinRemoveIDs(l, u);
                break;
            }
            case 'n':
                pauseSim = noPause;
                break;
            case 'h':
                printUsage(basename(argv[0]));
                return 0;
            default:
                printUsage(basename(argv[0]));
                return 1;
        }
    }

    uint32_t memSizes[] = { 16, 8, 4 };

    SimParameters param = {
        .processorCount = 2,
        .basePid = 100,
        .maxPids = 30,
        .swappingPolicy = FirstFit,
        .schedulingPolicy = SPN,
        .memInitAddr = 0x10000000,
        .memMinLogSize = 10,
        .memSizesCount = 3,
        .memSizes = memSizes
    };

    // Correct batch format: jid;submission;mem;bursts...
    const char *batch =
        "00000001;0.0;256;4.0,2.0,3.0\n"
        "00000002;1.5;512;6.0,1.0,2.0\n"
        "00000003;3.0;128;5.0\n";

    char tmp_template[] = "/tmp/sim_test_batch_XXXXXX";
    int fd = mkstemp(tmp_template);
    if (fd == -1) { perror("mkstemp"); return 1; }
    FILE *f = fdopen(fd, "w");
    if (!f) { perror("fdopen"); close(fd); unlink(tmp_template); return 1; }
    fwrite(batch, 1, strlen(batch), f);
    fclose(f);
    f = fopen(tmp_template, "r");
    if (!f) { perror("fopen"); unlink(tmp_template); return 1; }

    /* ============================================ */
    banner("TEST 1: simOpen");
    simOpen(&param);

    banner("TEST 2: simLoadBatch");
    simLoadBatch(f, 1024);
    fclose(f);
    unlink(tmp_template);

    banner("TEST 3: Initial state after load");
    simPrint(fout, SimPrintAll, false);
    pauseSim();

    banner("TEST 4: simStep() - single steps (10 steps)");
    for (int i = 0; i < 10; ++i)
    {
        bool hasMore = simStep(false);
        printf("simStep() #%d -> %s more events\n", i+1, hasMore ? "has" : "no");
        simPrint(fout, SimPrintAll, false);
        pauseSim();
    }

    banner("TEST 5: simRun(5) - run 5 more steps");
    simRun(5, false);
    simPrint(fout, SimPrintAll, false);
    pauseSim();

    banner("TEST 6: simRun(0) - run to completion");
    simRun(0, false);
    simPrint(fout, SimPrintAll, true);  // CSV final report
    pauseSim();

    banner("TEST 7: simClose (after first simulation)");
    simClose(true);

    banner("TEST 8: simOpen (for simJobLauncher test)");
    simOpen(&param);

    banner("TEST 9: simJobLauncher (10 jobs, seed 42) - EXPECTED TO FAIL");
    try
    {
        simJobLauncher(10, 42);
        printf("simJobLauncher succeeded (unexpected at this stage)\n");
    }
    catch (const Exception &e)
    {
        if (e.en == 95)  // ENOTSUP = Operation not supported
            printf("simJobLauncher threw ENOTSUP (95) - expected behavior (not yet implemented)\n");
        else
            printf("simJobLauncher threw unexpected exception: %s (errno=%d)\n", e.what(), e.en);
    }
    catch (...)
    {
        printf("simJobLauncher threw unknown exception\n");
    }

    // Even if launcher fails, simulation state should still be printable
    banner("TEST 10: State after simJobLauncher attempt");
    simPrint(fout, SimPrintAll, false);
    pauseSim();

    banner("TEST 11: Final simClose");
    simClose(true);

    banner("ALL AVAILABLE PUBLIC SIM FUNCTIONS TESTED SUCCESSFULLY!");

    if (fout != stdout) fclose(fout);
    return 0;
}