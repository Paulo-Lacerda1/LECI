/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{
    void pctOpen(uint16_t base, uint16_t cnt)
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
        pctPidBase = base;
        pctPidCount = cnt;
        pctLastPid = base + cnt - 1;
        pctTable = new PctNode*[cnt];
        for (uint16_t i = 0; i < cnt; i++) {
            pctTable[i] = nullptr;
        }
    }
} // end of namespace group

