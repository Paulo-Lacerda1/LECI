/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void swpInsert(uint16_t pid, uint32_t size, bool blocked)
    {
        // Create new node
        SwpNode* newNode = new SwpNode;
        if (newNode == nullptr) {
            throw Exception(ENOMEM, __func__);
        }
        
        // Initialize nodes
        newNode->pid = pid;
        newNode->size = size;
        newNode->blocked = blocked;
        newNode->next = nullptr;
        
        // Insert at tail to maintain insertion order
        if (swpTail == nullptr) {
            // List is empty
            swpHead = newNode;
            swpTail = newNode;
        } else {
            // Append to end of list
            swpTail->next = newNode;
            swpTail = newNode;
        }
    }
} // end of namespace group


