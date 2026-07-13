/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void swpClose()
    {
        /* TODO POINT: Replace next instruction with your code */

        // Free all nodes in the list
        SwpNode* current = swpHead;
        while (current != nullptr) {
            SwpNode* temp = current;
            current = current->next;
            delete temp;
        }
        
        // Set to closed state
        swpHead = SWP_UNDEF_NODE;
        swpTail = SWP_UNDEF_NODE;
    }
} // end of namespace group

