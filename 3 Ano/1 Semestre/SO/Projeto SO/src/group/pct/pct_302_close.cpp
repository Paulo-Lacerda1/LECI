/*
 *  \author Diogo Ferreira 114002
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{
    void pctClose()
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
        for (uint16_t i = 0; i < pctPidCount; i++) {
            if (pctTable[i] != nullptr) {
                delete pctTable[i];
            }
        }
        delete[] pctTable;
        pctTable = PCT_UNDEF_TABLE;
        pctPidBase = 0;
        pctPidCount = 0;
        pctLastPid = 0;
    }
} // end of namespace group

