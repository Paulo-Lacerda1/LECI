/*
 *  \author Adriana
 */

#include "somm25nm.h"

namespace group
{
    void simStepActivate()
    {
        /* TODO POINT: Replace next instruction with your code */
        // 1) Se SWP estiver vazia, nao ha nada para ativar
        if (swpIsEmpty())
            return;

        // 2) Ver o tamanho do maior bloco livre na memoria
        uint32_t sizeAvailable = memBiggestFreeBlock();
        if (sizeAvailable == 0)
            return; // nao tem memoria livre

        // 3) Tentar trazer um processo nao bloqueado pela SWP
        uint16_t pid = swpRetrieve(sizeAvailable, false);
        if (pid == 0) {
            // nenhum processo READY coube, ou seja, permite bloqueados
            pid = swpRetrieve(sizeAvailable, true);
            if (pid == 0)
                return; // nao cabe nada
        }

        // 4) Consultar o estado atual do processo no PCT
        PctProcessState state;
        pctGet(pid, PctState, &state);

        // 5) Obter info para alocar memoria
        uint32_t jid;
        pctGet(pid, PctJid, &jid);

        uint32_t memSize;
        jobGet(jid, JobMemSize, &memSize);

        // 6) Alocar memoria para o processo
        uint32_t memAddr = memAlloc(pid, memSize);
        if (memAddr == 0) {
            // Se nao conseguir alocar, voltar a por na SWP
            bool wasBlocked = (state == S_BLOCKED);
            swpInsert(pid, memSize, wasBlocked);
            return;
        }

        // 7) Atualizar endereco de memoria no PCT
        pctSet(pid, PctMemAddr, &memAddr);

        // 8) Atualizar o estado:
        //    S_READY   -> READY
        //    S_BLOCKED -> BLOCKED
        if (state == S_READY) {
            state = READY;
        } else if (state == S_BLOCKED) {
            state = BLOCKED;
        } else {
            throw Exception(EINVAL, __func__);
        }

        // 9) Gravar o novo estado no PCT
        pctSet(pid, PctState, &state);

        // 10) Se ta READY, meter na rdy
        if (state == READY) {
            double burstTime;
            jobGet(jid, JobNextBurstDuration, &burstTime);
            rdyInsert(pid, simTime, burstTime);
        }

        // 11) Se tiver CPU livre
        if (simIdleHead != SIM_UNDEF_INDEX) {
            feqInsert(simTime, DISPATCH, simIdleHead);
        }
    }
} // end of namespace group

