/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void memFree(uint32_t addr)
    {
        /* TODO POINT: Replace next instruction with your code */
        MemNode *prev = nullptr;
        MemNode *curr = memOccupiedHead;
        // procurar na lista de ocupados o bloco com esse endereco addr
        while (curr != nullptr) {
            if (curr->addr == addr) {
                // se é encontrado entao retirar da lista de ocupados
                if (prev == nullptr)
                    memOccupiedHead = curr->next;
                else
                    prev->next = curr->next;

                // marcar como livre
                curr->pid = 0;

                // inserir no inicio da lista de livres
                curr->next = memFreeHead;
                memFreeHead = curr;

                return;
            }

            prev = curr;
            curr = curr->next;
        }

        // se nao encontrou nenhum bloco com esse addr, considera erro
        throw Exception(EINVAL, __func__);
    }
} // end of namespace group


