/*
 *  Rafael Ferreira - 119356
 */

#include "feq.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>

namespace group 
{

// ================================================================================== //

    void feqOpen()
    {
        if (feqHead == FEQ_UNDEF_NODE) {
            feqHead = nullptr;
            return;
        }

        while (feqHead != nullptr) {
            FeqNode *tmp = feqHead;
            feqHead = feqHead->next;
            delete tmp;
        }
        
        feqHead = nullptr;
    }

// ================================================================================== //

} // end of namespace group

