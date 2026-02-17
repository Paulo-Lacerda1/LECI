/*
 *  Paulo
 */

#include "rdy.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void rdyInsert(uint16_t pid, double curTime, double runTime)
    {
        // Criar nó 
        RdyNode *node = new RdyNode;
        node->pid = pid;
        node->queueTime = curTime;
        node->runTime = runTime;
        node->next = nullptr;

        // Inserção em lista vazia
        if (rdyHead == nullptr)
        {
            rdyHead = node;
            return;
        }

        // Inserir no início se tem burst mais curto (ou chegou mais cedo em empate)
        if (runTime < rdyHead->runTime ||
            (runTime == rdyHead->runTime && curTime < rdyHead->queueTime))
        {
            node->next = rdyHead;
            rdyHead = node;
            return;
        }

        // Percorrer até ao ponto de inserção 
        RdyNode *prev = rdyHead;
        RdyNode *cur = rdyHead->next;
        while (cur != nullptr &&
               (cur->runTime < runTime ||
                (cur->runTime == runTime && cur->queueTime <= curTime)))
        {
            prev = cur;
            cur = cur->next;
        }

        node->next = cur;
        prev->next = node;
    }
} // end of namespace group

