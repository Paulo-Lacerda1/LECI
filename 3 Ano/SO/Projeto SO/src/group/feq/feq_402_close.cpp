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

    void feqClose()
    {
        if (feqHead == FEQ_UNDEF_NODE) 
            throw Exception(EINVAL, __func__);

        while (feqHead != nullptr) {
            FeqNode *tmp = feqHead;
            feqHead = feqHead->next;
            delete tmp;
        }
            
        feqHead = FEQ_UNDEF_NODE;
    }

// ================================================================================== //

} // end of namespace group

