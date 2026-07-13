/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void memClose() 
    {
        /* TODO POINT: Replace next instruction with your code */
        // se tiver blocos, libertar a memoria
        if (memBlocks != MEM_UNDEF_NODE && memBlocks != nullptr) {
            delete[] memBlocks;
        }

        // voltar ao estado inicial
        memBlocks = MEM_UNDEF_NODE;
        memBlockCount = 0;
        memMinLogSize = 0;
        memFreeHead = MEM_UNDEF_NODE;
        memOccupiedHead = MEM_UNDEF_NODE;
    }
} // end of namespace group


