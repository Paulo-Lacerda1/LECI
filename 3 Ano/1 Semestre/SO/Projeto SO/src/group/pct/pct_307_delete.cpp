/*
 *  \author ...
 */

#include "pct.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void pctDelete(uint16_t pid)
    {
        /* replace with your code */
        //throw Exception(ENOSYS, __func__);
        uint16_t index = pid - pctPidBase;
        // Delete the node
        delete pctTable[index];
        pctTable[index] = nullptr;  
    }
} // end of namespace group
