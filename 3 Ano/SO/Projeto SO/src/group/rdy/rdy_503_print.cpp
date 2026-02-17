/*
 *  Paulo
 */

#include "rdy.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void rdyPrint(FILE *fout, bool csv)
    {
        if (csv)
        {
            // cabeçalho CSV
            fprintf(fout, "pid;queueTime;runTime\n");

            // linhas CSV
            for (RdyNode *node = rdyHead; node != nullptr; node = node->next)
            {
                fprintf(fout, "%u;%.1f;%.1f\n", node->pid, node->queueTime, node->runTime);
            }
            return;
        }

        fprintf(fout, "\nRDY module internal state:\n");
        if (rdyHead == nullptr)
        {
            fprintf(fout, "  (empty)\n");
            return;
        }

        for (RdyNode *node = rdyHead; node != nullptr; node = node->next)
        {
            fprintf(fout, "  PID: %u;   queue time: %.1f;   burst time: %.1f\n",
                    node->pid, node->queueTime, node->runTime);
        }
    }
} // end of namespace group

