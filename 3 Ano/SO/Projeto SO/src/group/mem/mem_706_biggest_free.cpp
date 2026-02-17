/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    uint32_t memBiggestFreeBlock()
    {
        /* TODO POINT: Replace next instruction with your code */
        // percorrer a lista de blocos livres e encontrar o maior
        uint32_t maxSize = 0;

        MemNode *curr = memFreeHead;
        while (curr != nullptr) {
            // o tamanho do bloco em bytes = 2^(logSize)
            uint32_t size = 1u << curr->logSize;
            if (size > maxSize)
                maxSize = size;

            curr = curr->next;
        }

        return maxSize;
    }
} // end of namespace group


