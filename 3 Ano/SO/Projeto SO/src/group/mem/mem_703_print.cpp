/*
 *  \author Adriana
 */

#include "mem.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void memPrint(FILE *fout, MemPrintMode mode, bool csv)
    {
        /* TODO POINT: Replace next instruction with your code */
        // cabecalho csv
        if (csv) {
            fprintf(fout, "type,addr,size,pid\n");
        } else {
            fprintf(fout, "TYPE   ADDR        SIZE   PID\n");
            fprintf(fout, "-------------------------------\n");
        }

        // percorrer os blocos todos pela ordem que esta no array
        for (uint32_t i = 0; i < memBlockCount; ++i) {
            MemNode *n = &memBlocks[i];

            bool isFree = (n->pid == 0);
            const char *type = isFree ? "FREE" : "OCCUP";

            // filtrar consoante o modo
            if (mode == MemPrintFree && !isFree)
                continue;
            if (mode == MemPrintOccupied && isFree)
                continue;

            uint32_t size = 1u << n->logSize;

            if (csv) {
                // type , addr , size , pid
                fprintf(fout, "%s,%#010x,%u,%hu\n",
                        type, n->addr, size, n->pid);
            } else {
                fprintf(fout, "%-6s %#010x %6u %5hu\n",
                        type, n->addr, size, n->pid);
            }
        }

        fflush(fout);
    }

} // end of namespace group


