/*
 *  
 *  ...
 *
 *  \author Artur Pereira - 2025
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

    /* init test */
    banner("Starting the JOB module");
    jobOpen();

    /* test jobPrint when JOB queue is empty */
    banner("Printing JOB queue in CSV mode");
    jobPrint(stdout, true);
    banner("Printing JOB queue in normal mode");
    jobPrint(stdout);

    /* test jobInsert, filling JOQ queue */
    banner("Filling JOB queue");
    struct { double profile[JOB_MAX_BURSTS]; } burstProfile;
    burstProfile = {{100.0, 10.0, 101.1, 0, 0, 0, 0, 0, 0, 0, 0}};
    jobInsert(0x12345678, 0.0, 500000, burstProfile.profile);
    burstProfile = {{100, 1, 101, 2, 102, 3, 103, 0, 0, 0, 0}};
    jobInsert(0xabcdef00, 10.1, 0x1000000, burstProfile.profile);
    burstProfile = {{1000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    jobInsert(0xA0B1C2D3, 10.2, 0x4000000, burstProfile.profile);
    burstProfile = {{22, 11, 122, 21, 222, 31, 322, 41, 422, 51, 111}};
    jobInsert(0xb1111111, 22.7, 13000000, burstProfile.profile);

    /* test jobPrint job queue */
    banner("Printing JOB queue in CSV mode");
    jobPrint(stdout, true);
    banner("Printing JOB queue in normal mode");
    jobPrint(stdout);

    /* test jobGet, getting and printing submissionTime */
    banner("Printing submissionTime");
    double submissionTime;
    jobGet(0xabcdef00, JobSubmissionTime, &submissionTime);
    fprintf(fout, "submissionTime: %.1f\n", submissionTime);

    /* test jobGet, getting finishTime before setting it */
    banner("Printing finishTime before setting it");
    double finishTime;
    jobGet(0xb1111111, JobFinishTime, &finishTime);
    if (finishTime == JOB_UNDEF_TIME)
        fprintf(fout, "finishTime: UNDEF\n");
    else
        fprintf(fout, "finishTime: %.1f\n", finishTime);

    /* test jobSet, setting finishTime and printing it afterwards */
    banner("Setting finishTime and printing it afterwards");
    finishTime = 10.4;
    jobSet(0xb1111111, JobFinishTime, &finishTime);
    finishTime = 99999.9;
    jobGet(0xb1111111, JobFinishTime, &finishTime);
    if (finishTime == JOB_UNDEF_TIME)
        fprintf(fout, "finishTime: UNDEF\n");
    else
        fprintf(fout, "finishTime: %.1f\n", finishTime);

    /* test jobGet, getting memSize */
    banner("Printing memSize");
    uint32_t memSize;
    jobGet(0xb1111111, JobMemSize, &memSize);
    fprintf(fout, "memSize: %d/%#x\n", memSize, memSize);

    /* test jobGet and jobSet on bursts */
    banner("Printing, one by one, bursts of all jobs");
    uint32_t jids[4] = { 0xb1111111, 0xabcdef00, 0xA0B1C2D3, 0x12345678 };
    for (uint32_t jid : jids)
    {
        fprintf(fout, "Jid: 0x%08x:\n", jid);
        for (int i = 0; i < 12; i++)
        {
            uint32_t index;
            jobGet(jid, JobNextBurstIndex, &index);
            double duration;
            jobGet(jid, JobNextBurstDuration, &duration);
            if (duration == 0) break;
            fprintf(fout, "  index: %u; ", index);
            fprintf(fout, "  burst[%d]: %.1f\n", i, duration);
            index = index + 1;
            jobSet(jid, JobNextBurstIndex, &index);
        }
        fprintf(fout, "\n");
    }

    /* end simulation */
    banner("Close and end the JOB module");
    jobClose();

    banner("Bye!");
}
