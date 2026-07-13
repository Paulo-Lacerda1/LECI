/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void swpPrint(FILE *fout, bool csv)
    {
        if (fout == nullptr) {
            throw Exception(EINVAL, __func__);
        }
        
        if (csv) {
            // CSV format: header + data rows
            fprintf(fout, "pid;size;blocked\n");
            
            SwpNode* current = swpHead;
            while (current != nullptr) {
                fprintf(fout, "%u;%u;%s\n", 
                        current->pid, 
                        current->size, 
                        current->blocked ? "yes" : "no");
                current = current->next;
            }

        } else {
            // Regular format
            fprintf(fout, "\nSWP module internal state:\n");
            
            if (swpHead == nullptr) {
                fprintf(fout, "  (empty)\n");
            } else {
                SwpNode* current = swpHead;
                while (current != nullptr) {
                    fprintf(fout, "  PID: %u;  size: %u/0x%x;  state: %s\n",
                            current->pid,
                            current->size,
                            current->size,
                            current->blocked ? "SUSPENDED_BLOCKED" : "SUSPENDED_READY");
                    current = current->next;
                }
            }
        }
    }
} // end of namespace group


