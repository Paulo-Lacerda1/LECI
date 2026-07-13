/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    uint16_t pctNew(uint32_t jid)
    {
        /* replace with your code */
        //throw Exception(ENOSYS, __func__);
        for (uint16_t i = 1; i < pctPidCount; i++) {
            if (pctTable[i] == nullptr) {
                PctNode* newNode = new PctNode;
                newNode->jid = jid;
                newNode->state = NEW;
                newNode->memAddr = PCT_UNDEF_ADDRESS;
                pctTable[i] = newNode;
                return pctPidBase + i ;
            }
        }
        return 0; // should not reach here
    }
} // end of namespace group
