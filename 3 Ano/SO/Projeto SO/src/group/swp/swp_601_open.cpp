/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787 
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void swpOpen(SwpSwappingPolicy policy)
    {
        // Initialize the policy
        swpPolicy = policy;
        
        // Initialize the list as empty
        swpHead = nullptr;
        swpTail = nullptr;

    }
} // end of namespace group

