/*
 *  \author Diogo Ferreira
 */

#include "somm25nm.h"

namespace group
{
    void simStepWaitEvent(uint16_t cid)
    {
        uint16_t pid = simProcessorState[cid].pid;

        // 2. Adicionar o processador à lista de processadores livres (idle)
        simProcessorState[cid].idle = true;
        simProcessorState[cid].next = SIM_UNDEF_INDEX;

        if (simIdleHead == SIM_UNDEF_INDEX) {
            simIdleHead = cid;
        } else {
            simProcessorState[simIdleTail].next = cid;
        }
        simIdleTail = cid;

        // 3. Atualizar o estado do processo para bloqueado (BLOCKED)
        // O processo continua em memória, mas está à espera de I/O.
        PctProcessState state = BLOCKED;
        pctSet(pid, PctState, &state);

        // 4. Obter o ID da tarefa (job) para aceder à duração do burst
        uint32_t jid;
        pctGet(pid, PctJid, &jid);

        // 5. Obter a duração do próximo burst (que será de I/O)
        double duration;
        jobGet(jid, JobNextBurstDuration, &duration);

        // 6. Avançar o índice para o próximo burst (que será CPU ou fim)
        uint32_t idx;
        jobGet(jid, JobNextBurstIndex, &idx);
        idx++;
        jobSet(jid, JobNextBurstIndex, &idx);

        // 7. Agendar um evento EVENT_OCCURS para quando o burst de I/O terminar
        feqInsert(simTime + duration, EVENT_OCCURS, pid);

        // 8. Agendar um evento DISPATCH para ocupar o processador que ficou livre
        feqInsert(simTime, DISPATCH, 0);
        
    }
} // end of namespace group

