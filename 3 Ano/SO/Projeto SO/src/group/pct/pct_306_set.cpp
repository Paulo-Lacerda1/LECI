/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void pctSet(uint16_t pid, PctField field, void *value)
    {
        /* replace with your code */
        //throw Exception(ENOSYS, __func__);
        uint16_t index = pid - pctPidBase;

        // Ensure the PID is actually active
        // if (pctTable[index] == nullptr) {
        //     throw Exception(EINVAL, "%s: PID %u does not exist", __func__, pid);
        // }

        PctNode *node = pctTable[index];

        switch (field) {
            case PctJid:
                node->jid = *static_cast<uint32_t*>(value);
                break;
            case PctMemAddr:
                node->memAddr = *static_cast<uint32_t*>(value);
                break;
            case PctState:
                node->state = *static_cast<PctProcessState*>(value);
                break;
        }
    }
} // end of namespace group
