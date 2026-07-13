/*
 *  \author Tiago Alexandre Oliverira Ferreira 112787
 */

#include "job.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>

namespace group
{

// ================================================================================== //

    void jobPrint(FILE *fout, bool csv)
    {
        if (fout == NULL)
            return;

        JobNode *curr = jobHead;

        /* ----------------------------- CSV MODE ----------------------------- */
        if (csv)
        {
            fprintf(fout, "jid;submissionTime;finishTime;memSize;nextBurstIndex;profile\n");

            while (curr != nullptr && curr != JOB_UNDEF_NODE)
            {
                // Print jid (lowercase hex, no 0x)
                fprintf(fout, "%08x;", curr->jid);

                // submission time
                fprintf(fout, "%.1f;", curr->submissionTime);

                // finish time
                if (curr->finishTime == JOB_UNDEF_TIME)
                    fprintf(fout, "UNDEF;");
                else
                    fprintf(fout, "%.1f;", curr->finishTime);

                // memSize as hex
                fprintf(fout, "0x%x;", curr->memSize);

                // nextBurstIndex
                fprintf(fout, "%u;", curr->nextBurstIndex);

                // profile
                bool first = true;
                for (int i = 0; i < JOB_MAX_BURSTS && curr->bursts[i] != 0; i++) {
                    if (!first) fprintf(fout, ",");
                    fprintf(fout, "%.1f", curr->bursts[i]);
                    first = false;
                }

                fprintf(fout, "\n");
                curr = curr->next;
            }

            return;
        }

        /* ------------------------- NORMAL MODE --------------------------- */

        fprintf(fout, "\nJOB module internal state:\n");

        if (curr == nullptr)
        {
            fprintf(fout, "  (empty)\n");
            return;
        }
        else
        {
            while (curr != nullptr && curr != JOB_UNDEF_NODE)
            {
                fprintf(fout, "JOB: 0x%08x\n", curr->jid);

                fprintf(fout, "  Submission time: %.1f\n", curr->submissionTime);

                if (curr->finishTime == JOB_UNDEF_TIME)
                    fprintf(fout, "  Finish time: UNDEF\n");
                else
                    fprintf(fout, "  Finish time: %.1f\n", curr->finishTime);

                fprintf(fout, "  Memory size: %u/0x%x\n",
                        curr->memSize, curr->memSize);

                fprintf(fout, "  Next burst index: %u\n", curr->nextBurstIndex);

                fprintf(fout, "  Burst profile: ");

                bool first = true;
                for (int i = 0; i < JOB_MAX_BURSTS && curr->bursts[i] != 0; i++) {
                    if (!first) fprintf(fout, ", ");
                    fprintf(fout, "%.1f", curr->bursts[i]);
                    first = false;
                }
                fprintf(fout, "\n");

                curr = curr->next;
            }
        }
    }               
    
// ================================================================================== //

} // end of namespace group

