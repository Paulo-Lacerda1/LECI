/*
 *  \author Adriana
 */

#include "somm25nm.h"

namespace group
{
    void simRun(uint32_t cnt, bool blocking)
    {
        /* TODO POINT: Replace next instruction with your code */
        // se o cnt == 0 entao corre ate nao haver mais eventos
        if (cnt == 0) {
            while (true) {
                bool hasMore = simStep(blocking);
                if (!hasMore)
                    break;
            }
            return;
        }

        // se o cnt for maior que 0 entao correr ate no máximo cnt passos
        for (uint32_t i = 0; i < cnt; i++) {
            bool hasMore = simStep(blocking);
            if (!hasMore)
                break;
        }
    }
} // end of namespace group

