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

bool feqRetrieve(double *time, FeqEventType *type, uint32_t *xid, bool blocking)
{
    if (feqHead == FEQ_UNDEF_NODE || time == nullptr || type == nullptr || xid == nullptr)
        throw Exception(EINVAL, __func__);   // module closed

    // FEQ empty → NO EXCEPTION, always return false
    if (feqHead == nullptr)
    {
        return false; // blocking or not — binary behaves this way
    }

    // FEQ has elements → pop head
    FeqNode *node = feqHead;
    feqHead = feqHead->next;

    *time = node->time;
    *type = node->type;
    *xid  = node->xid;

    delete node;
    return true;
}


// ================================================================================== //

} // end of namespace group

