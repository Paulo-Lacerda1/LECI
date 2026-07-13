/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{   static const char* stateToString(PctProcessState state) {
        switch(state) {
            case NEW: return "NEW";
            case RUNNING: return "RUNNING";
            case BLOCKED: return "BLOCKED";
            case READY: return "READY";
            case S_BLOCKED: return "S_BLOCKED";
            case S_READY: return "S_READY";
            case ENDED: return "ENDED";
            // Return empty string for invalid states to match prof.txt last line
            default: return ""; 
        }
    }
    void pctPrint(FILE *fout, bool csv)
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
        if (csv) {
            fprintf(fout, "pid;jid;memAddr;state\n");
        
        for (uint16_t i = 0; i < pctPidCount; i++) {
            // Check if slot is occupied
            if (pctTable[i] != nullptr) {
                uint16_t currentPid = pctPidBase + i;
                PctNode *node = pctTable[i];
                
                const char* stateStr = stateToString(node->state);

                // Handle Memory Address Logic
                // If address is PCT_UNDEF_ADDRESS, we must print "UNDEF"
                // Otherwise we print the hex value
                char memBuffer[16];
                if (node->memAddr == PCT_UNDEF_ADDRESS) {
                    sprintf(memBuffer, "UNDEF");
                } else {
                    sprintf(memBuffer, "0x%04x", node->memAddr);
                }

                if (csv) {
                    // Match prof.txt formatting:
                    // PID: %05hu (5 digits, zero padded)
                    // JID: %08x (Hexadecimal, 8 digits, zero padded)
                    // Separator: ;
                    fprintf(fout, "%05hu;%08x;%s;%s\n", 
                        currentPid, 
                        node->jid, 
                        memBuffer, 
                        stateStr);
                } else {
                    // Standard table formatting
                    fprintf(fout, "| %5u | %8u | %-9s | %-8s |\n", 
                        currentPid, node->jid, stateStr, memBuffer);
                }
            }
        }
    }
    }
} // end of namespace group

