/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void pctGet(uint16_t pid, PctField field, void *value)
    {
        /* replace with your code */
        //throw Exception(ENOSYS, __func__);
        uint16_t index = pid - pctPidBase;

        // Ensure the PID is actually active (node exists)
        // if (pctTable[index] == nullptr) {
        //     throw Exception(EINVAL, "%s: PID %u does not exist", __func__, pid);
        // }

        PctNode *node = pctTable[index];

        switch (field) {
            case PctJid:
                *static_cast<uint32_t*>(value) = node->jid;
                break;
            case PctMemAddr:
                *static_cast<uint32_t*>(value) = node->memAddr;
                break;
            case PctState:
                *static_cast<PctProcessState*>(value) = node->state;
                break;
        }
    }
} // end of namespace group
