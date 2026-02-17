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
    void jobOpen()
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
        jobHead = nullptr;
    }

// ================================================================================== //

} // end of namespace group

