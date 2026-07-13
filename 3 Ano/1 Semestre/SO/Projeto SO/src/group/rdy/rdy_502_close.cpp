/*
 *  Paulo
 */

#include "rdy.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void rdyClose()
    {
        // libertar todos os nós da fila RDY
        RdyNode *cur = rdyHead;
        while (cur != nullptr)
        {
            RdyNode *next = cur->next;
            delete cur;
            cur = next;
        }

        // marcar módulo como fechado
        rdyHead = RDY_UNDEF_NODE;
        rdyPolicy = RDY_UNDEF_POLICY;
    }
} // end of namespace group
