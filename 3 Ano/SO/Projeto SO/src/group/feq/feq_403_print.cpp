/*
 *  Rafael Ferreira - 119356
 */

#include "feq.h"

#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{

// ================================================================================== //
void feqPrint(FILE *fout, bool csv)
{
    if (feqHead == FEQ_UNDEF_NODE || fout == nullptr)
        throw Exception(EINVAL, __func__);

    if (csv)
    {
        // Exact CSV header
        fprintf(fout, "time;event;jid;pid;cid\n");

        for (FeqNode *p = feqHead; p != nullptr; p = p->next)
        {
            const char *typeName = nullptr;
            switch (p->type)
            {
                case SUBMIT:        typeName = "SUBMIT"; break;
                case ADMIT:         typeName = "ADMIT"; break;
                case DISPATCH:      typeName = "DISPATCH"; break;
                case TIMEOUT:       typeName = "TIMEOUT"; break;
                case PREEMPT:       typeName = "PREEMPT"; break;
                case WAIT_EVENT:    typeName = "WAIT_EVENT"; break;
                case EVENT_OCCURS:  typeName = "EVENT_OCCURS"; break;
                case SUSPEND:       typeName = "SUSPEND"; break;
                case ACTIVATE:      typeName = "ACTIVATE"; break;
                case EXIT:          typeName = "EXIT"; break;
                case DELETE:        typeName = "DELETE"; break;
                default:            typeName = "UNKNOWN";
            }

            const char *jid = "---";
            const char *pid = "---";
            const char *cid = "---";
            char bufJID[32], bufPID[32], bufCID[32];

            switch (p->type)
            {
                case SUBMIT:
                    // Full JID in hex
                    snprintf(bufJID, sizeof(bufJID), "0x%08x", p->xid);
                    jid = bufJID;
                    break;

                case ADMIT:
                    // PID = low 12 bits
                    snprintf(bufPID, sizeof(bufPID), "%u", p->xid & 0xFFF);
                    pid = bufPID;
                    break;

                case EXIT:
                case WAIT_EVENT:
                case PREEMPT:
                case TIMEOUT:
                case EVENT_OCCURS:
                    // CID = low 12 bits
                    snprintf(bufCID, sizeof(bufCID), "%u", p->xid & 0xFFF);
                    cid = bufCID;
                    break;

                default:
                    // DISPATCH, SUSPEND, ACTIVATE, DELETE: all IDs '---'
                    break;
            }

            fprintf(fout,
                    "%.1f;%s;%s;%s;%s\n",
                    p->time,
                    typeName,
                    jid,
                    pid,
                    cid);
        }
    }
    else
    {
        fprintf(fout, "\nFEQ module internal state:\n");

        if (feqHead == nullptr)
        {
            fprintf(fout, "  (empty)\n");
            return;
        }

        for (FeqNode *p = feqHead; p != nullptr; p = p->next)
        {
            const char *typeName = nullptr;
            switch (p->type)
            {
                case SUBMIT:        typeName = "SUBMIT"; break;
                case ADMIT:         typeName = "ADMIT"; break;
                case DISPATCH:      typeName = "DISPATCH"; break;
                case TIMEOUT:       typeName = "TIMEOUT"; break;
                case PREEMPT:       typeName = "PREEMPT"; break;
                case WAIT_EVENT:    typeName = "WAIT_EVENT"; break;
                case EVENT_OCCURS:  typeName = "EVENT_OCCURS"; break;
                case SUSPEND:       typeName = "SUSPEND"; break;
                case ACTIVATE:      typeName = "ACTIVATE"; break;
                case EXIT:          typeName = "EXIT"; break;
                case DELETE:        typeName = "DELETE"; break;
                default:            typeName = "UNKNOWN";
            }

            fprintf(fout, "Time: %.1f\n", p->time);
            fprintf(fout, "  type: %s\n", typeName);

            switch (p->type)
            {
                case SUBMIT:
                    fprintf(fout, "  JID: 0x%08x\n", p->xid);
                    break;

                case ADMIT:
                    fprintf(fout, "  PID: %u\n", p->xid & 0xFFF);
                    break;

                case EXIT:
                case WAIT_EVENT:
                case PREEMPT:
                case TIMEOUT:
                case EVENT_OCCURS:
                    fprintf(fout, "  CID: %u\n", p->xid & 0xFFF);
                    break;

                default:
                    // DISPATCH, ACTIVATE, SUSPEND, DELETE: no ID line
                    break;
            }
        }
    }
}



// ================================================================================== //

} // end of namespace group

