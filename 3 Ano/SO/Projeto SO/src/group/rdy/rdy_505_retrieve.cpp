/*
 *  Paulo
 */

#include "rdy.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    uint16_t rdyRetrieve(double curTime)
    {
        // Se a fila estiver vazia, devolve 0
        if (rdyHead == nullptr)
            return 0;

        RdyNode *prev = nullptr;
        RdyNode *selPrev = nullptr;
        RdyNode *sel = rdyHead;

        switch (rdyPolicy)
        {
            case SPN:
            case SRT:
                // Caso a lista já está ordenada pelo burst: retira o primeiro nó
                sel = rdyHead;
                selPrev = nullptr;
                break;

            case HRRN:
            {
                // Percorre à procura do maior response ratio ( (espera+burst)/burst )
                double bestRatio = -1.0;
                for (RdyNode *cur = rdyHead; cur != nullptr; cur = cur->next)
                {
                    double wait = curTime - cur->queueTime;
                    double ratio = (wait + cur->runTime) / cur->runTime;
                    if (ratio > bestRatio)
                    {
                        bestRatio = ratio;
                        sel = cur;
                        selPrev = prev;
                    }
                    prev = cur;
                }
                break;
            }

            default:
                throw Exception(EINVAL, __func__);
        }

        // Retirar o nó escolhido da lista
        if (selPrev == nullptr)
            rdyHead = sel->next;
        else
            selPrev->next = sel->next;

        uint16_t pid = sel->pid;
        delete sel;
        return pid;
    }
} // end of namespace group
