/*
 *  \author ...
 */

#include "somm25nm.h"

#include <stdio.h>
#include <stdint.h>

namespace group 
{
    void simPrint(FILE *fout, uint32_t which, bool csv)
    {
        // 1. Verificar se o ficheiro de saída é válido
        if (fout == nullptr) {
            throw Exception(EINVAL, __func__);
        }

        // 2. Imprimir o estado interno do módulo SIM
        // O estado interno é composto pelo tempo de simulação e pelo estado dos processadores
        if (csv) {
            // Em modo CSV, imprimimos o tempo (formato simplificado)
            fprintf(fout, "processor;pid\n");
            if (simProcessorState != nullptr) {
                for (uint32_t i = 0; i < simProcessorCount; i++) {
                    fprintf(fout, "%u;", i);
                    if (simProcessorState[i].idle) {
                        // Se estiver idle, indica o próximo processador livre na lista
                        fprintf(fout, "---\n");
                    } else {
                        // Se estiver ocupado, indica o PID do processo em execução
                        fprintf(fout, "%u\n", simProcessorState[i].pid);
                    }
                }
            } else {
                fprintf(fout, "Processors: Not Initialized\n");
            }
        } else {
            // Em modo texto, imprimimos de forma formatada

            fprintf(fout, "\nSIM module internal state:\n");
            
            // Verificar se o array de processadores foi alocado
            if (simProcessorState != nullptr) {
                for (uint32_t i = 0; i < simProcessorCount; i++) {
                    fprintf(fout, "  proc[%u]: ", i);
                    if (simProcessorState[i].idle) {
                        // Se estiver idle, indica o próximo processador livre na lista
                        fprintf(fout, "(idle)\n");
                    } else {
                        // Se estiver ocupado, indica o PID do processo em execução
                        fprintf(fout, "%u\n", simProcessorState[i].pid);
                    }
                }
            } else {
                fprintf(fout, "Processors: Not Initialized\n");
            }
        }

        // 3. Delegar a impressão para os módulos satélite baseados nas flags
        if (which & SimPrintJob) {
            jobPrint(fout, csv);
        }
        if (which & SimPrintPct) {
            pctPrint(fout, csv);
        }
        if (which & SimPrintFeq) {
            feqPrint(fout, csv);
        }
        if (which & SimPrintRdy) {
            rdyPrint(fout, csv);
        }
        if (which & SimPrintSwp) {
            swpPrint(fout, csv);
        }

        // Flags de impressão do módulo MEM (verificar prioridade ou exclusividade)
        // SimPrintAll (0x3f) inclui SimPrintMemGlobal (0x20)
        if (which & SimPrintMemGlobal) {
            memPrint(fout, MemPrintGlobal, csv);
        } else if (which & SimPrintMemFreeOnly) {
            memPrint(fout, MemPrintFree, csv);
        } else if (which & SimPrintMemOccupiedOnly) {
            memPrint(fout, MemPrintOccupied, csv);
	}
    }
} // end of namespace group

