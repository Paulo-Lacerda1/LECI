/*
 *  \author Diogo Ferreira 114002
 */

#include "job.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>

namespace group
{

// ================================================================================== //

    void jobClose()
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
        JobNode *current = jobHead;
        JobNode *nextNode = nullptr;
        while (current != nullptr) {
            nextNode = current->next;
            delete current;
            current = nextNode;
        }
        jobHead = nullptr;
    }

// ================================================================================== //

} // end of namespace group

