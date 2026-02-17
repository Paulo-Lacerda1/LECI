/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void memOpen(uint32_t initAddr, uint32_t minLogSize, uint32_t *sizes, uint32_t cnt)
    {
        /* TODO POINT: Replace next instruction with your code */
        // validacao dos argumentos
        if (sizes == nullptr || cnt == 0)
            throw Exception(EINVAL, __func__);

        // calcular numeroo total de blocos
        uint32_t totalBlocks = 0;
        for (uint32_t i = 0; i < cnt; ++i)
            totalBlocks += sizes[i];

        if (totalBlocks == 0)
            throw Exception(EINVAL, __func__);

        // alocar o array de blocos
        MemNode *blocks = new MemNode[totalBlocks];
        if (blocks == nullptr)
            throw Exception(ENOMEM, __func__);

        // inicializar as variaveis
        memBlocks = blocks;
        memBlockCount = totalBlocks;
        memMinLogSize = (uint16_t)minLogSize;
        memFreeHead = nullptr;
        memOccupiedHead = nullptr;
        // preencher os blocos e construir a lista
        uint32_t addr = initAddr;
        uint32_t idx = 0;
        for (uint32_t i = 0; i < cnt; ++i) {
            uint32_t count = sizes[i];
            uint16_t logSize = (uint16_t)(minLogSize + i);
            uint32_t blockSize = (uint32_t)1u << logSize;

            for (uint32_t j = 0; j < count; ++j) {
                MemNode &n = memBlocks[idx++];
                n.addr = addr;
                n.logSize = logSize;
                n.pid = 0;
                // inserir no início da lista
                n.next = memFreeHead;
                memFreeHead = &n;

                addr += blockSize;
            }
        }
    }
} // end of namespace group

