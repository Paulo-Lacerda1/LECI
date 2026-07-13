/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    uint32_t memAlloc(uint32_t pid, uint32_t size)
    {
        /* TODO POINT: Replace next instruction with your code */
        // determinar log2 minimo necessario para ver o tamanho pedido
        uint16_t logNeeded = memMinLogSize;
        uint32_t blockSize = 1u << logNeeded;
        while (blockSize < size)
        {
            logNeeded++;
            blockSize = 1u << logNeeded;
        }

        // procurar o primeiro bloco livre com: logSize maior ou igual a logNeeded
        MemNode *prev = nullptr;
        MemNode *curr = memFreeHead;

        while (curr != nullptr)
        {
            if (curr->logSize >= logNeeded)
            {
                // encontrado o bloco suficiente para retirar da lista de livres
                if (prev == nullptr)
                    memFreeHead = curr->next;
                else
                    prev->next = curr->next;

                // marcar o bloco  como ocupado
                curr->pid = (uint16_t)pid;
                curr->next = nullptr;

                // inserir no iniciio da lista de ocupados
                curr->next = memOccupiedHead;
                memOccupiedHead = curr;

                // devolver o endereco do bloco
                return curr->addr;
            }

            prev = curr;
            curr = curr->next;
        }

        // se nao tiver bloco suficiente, devolver 0
        return 0;
    }

} // end of namespace group


