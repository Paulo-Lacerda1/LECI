/*
 *  Rafael Ferreira - 119356
 */

#include "feq.h"

#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{

// ================================================================================== //

void feqInsert(double time, FeqEventType type, uint32_t xid)
{
    if (feqHead == FEQ_UNDEF_NODE)
        throw Exception(EINVAL, __func__);   // Module is closed

    // Allocate and initialize node
    FeqNode *node = new FeqNode;
    node->time = time;
    node->type = type;
    node->xid  = xid;
    node->next = nullptr;

    FeqNode *prev = nullptr;
    FeqNode *curr = feqHead;

    while (curr != nullptr)
    {
        // Rule 1: ascending time
        if (time < curr->time)
            break;

        if (time == curr->time)
        {
            // Inline priority computation (no helper)
            int newPrio;
            if (type == DISPATCH)
                newPrio = 0;
            else if (type == WAIT_EVENT || type == EXIT ||
                     type == TIMEOUT    || type == PREEMPT)
                newPrio = 1;
            else
                newPrio = 2;

            int currPrio;
            if (curr->type == DISPATCH)
                currPrio = 0;
            else if (curr->type == WAIT_EVENT || curr->type == EXIT ||
                     curr->type == TIMEOUT    || curr->type == PREEMPT)
                currPrio = 1;
            else
                currPrio = 2;

            // Rule 2: higher-priority event first
            if (newPrio < currPrio)
                break;

            // Rule 3: same priority → FIFO (do not break)
        }

        prev = curr;
        curr = curr->next;
    }

    // Insert node in the list
    if (prev == nullptr)
    {
        node->next = feqHead;
        feqHead = node;
    }
    else
    {
        node->next = curr;
        prev->next = node;
    }
}


// ================================================================================== //

} // end of namespace group

