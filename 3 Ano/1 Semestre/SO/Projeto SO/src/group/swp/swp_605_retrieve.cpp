/*
 *  \author Tiago Alexandre Oliveira Ferreira 112787
 */

#include "swp.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    uint16_t swpRetrieve(uint32_t sizeAvailable, bool canBeBlocked)
    {
        // Return 0 if list is empty
        if (swpHead == nullptr) {
            return 0;
        }
        
        SwpNode* selectedNode = nullptr;
        SwpNode* selectedPrev = nullptr;
        
        if (swpPolicy == FirstFit) {
            // FirstFit: Prioritize unblocked processes
            // First pass: look for unblocked processes that fit
            SwpNode* current = swpHead;
            SwpNode* prev = nullptr;
            
            while (current != nullptr) {
                bool fitsSize = (current->size <= sizeAvailable);
                bool isUnblocked = !current->blocked;
                
                if (fitsSize && isUnblocked) {
                    selectedNode = current;
                    selectedPrev = prev;
                    break;  // Stop at FIRST unblocked match
                }
                
                prev = current;
                current = current->next;
            }
            
            // If no unblocked process found and canBeBlocked is true,
            // second pass: look for blocked processes that fit
            if (selectedNode == nullptr && canBeBlocked) {
                current = swpHead;
                prev = nullptr;
                
                while (current != nullptr) {
                    bool fitsSize = (current->size <= sizeAvailable);
                    bool isBlocked = current->blocked;
                    
                    if (fitsSize && isBlocked) {
                        selectedNode = current;
                        selectedPrev = prev;
                        break;  // Stop at FIRST blocked match
                    }
                    
                    prev = current;
                    current = current->next;
                }
            }
        } 
        else if (swpPolicy == FirstBest) {
            // FirstBest: Find process with size closest to available size (best fit)
            // This minimizes wasted space
            SwpNode* current = swpHead;
            SwpNode* prev = nullptr;
            SwpNode* bestPrev = nullptr;
            uint32_t bestWaste = UINT32_MAX;  // Track waste instead of size
            
            while (current != nullptr) {
                // Check if process fits
                bool fitsSize = (current->size <= sizeAvailable);
                // Check if meets blocking requirement
                bool meetsBlockReq = (canBeBlocked || !current->blocked);
                
                if (fitsSize && meetsBlockReq) {
                    // Calculate waste (how much memory would be left unused)
                    uint32_t waste = sizeAvailable - current->size;
                    
                    // Check if this is a better fit (less waste)
                    if (waste < bestWaste) {
                        selectedNode = current;
                        bestPrev = prev;
                        bestWaste = waste;
                    }
                }
                
                prev = current;
                current = current->next;
            }
            
            selectedPrev = bestPrev;
        }
        
        // If no suitable process found, return 0
        if (selectedNode == nullptr) {
            return 0;
        }
        
        // Remove selected node from list
        if (selectedPrev == nullptr) {
            // Removing head
            swpHead = selectedNode->next;
            if (swpHead == nullptr) {
                // List is now empty
                swpTail = nullptr;
            }
        } else {
            // Removing from middle or end
            selectedPrev->next = selectedNode->next;
            if (selectedNode == swpTail) {
                // Removed the tail
                swpTail = selectedPrev;
            }
        }
        
        // Save PID before freeing node
        uint16_t pid = selectedNode->pid;
        delete selectedNode;
        
        return pid;
    }
} // end of namespace group


