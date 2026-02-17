/*
 *  \author Diogo Ferreira
 */

#include "somm25nm.h"
#include "pct.h"

namespace group
{
    void simStepEventOccurs(uint16_t pid)
    {
        /* TODO POINT: Replace next instruction with your code */
        //throw Exception(ENOSYS, __func__);
       // 1. Obter o estado atual do processo
        PctProcessState state;
        pctGet(pid, PctState, &state);

        // 2. Lógica de transição de estado
        if (state == S_BLOCKED) 
        {
            // Se estava bloqueado e em swap, passa a S_READY (Ready Suspenso)
            swpUnblock(pid); // Move da lista de bloqueados para desbloqueados no SWP
            state = S_READY;
            pctSet(pid, PctState, &state);
        } 
        else if (state == BLOCKED) 
        {
            // Se estava bloqueado em memória, passa a READY
            state = READY;
            pctSet(pid, PctState, &state);

            // Obter a estimativa do próximo CPU burst para o escalonamento
            uint32_t jid;
            pctGet(pid, PctJid, &jid);
            double runTime;
            jobGet(jid, JobNextBurstDuration, &runTime);

            // Se for o último burst, o valor vem negativo (indicador do job.h), usamos o absoluto
            if (runTime < 0) runTime = -runTime;

            // Inserir na fila de prontos
            rdyInsert(pid, simTime, runTime);
        } 
        else 
        {
            // Estado inválido para este evento
            throw Exception(EPERM, __func__);
        }

        // 3. Agendar um evento DISPATCH para verificar se o processo pode correr
        // (Nota: o documento sim.h diz "If applicable, schedule a DISPATCH event")
        feqInsert(simTime, DISPATCH, 0);
    }
} // end of namespace group

