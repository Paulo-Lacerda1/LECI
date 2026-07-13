/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void swpUnblock(uint16_t pid)
    {
        // Search for the process with matching PID
        SwpNode* current = swpHead;
        
        while (current != nullptr) {
            if (current->pid == pid) {
                // Found the process - unblock it
                current->blocked = false;
                return;
            }
            current = current->next;
        }
        
        throw Exception(EINVAL, __func__);
    }
} // end of namespace group


