/*
 *  \author ...
 */

#include "somm25nm.h"

namespace group
{
    void simStepDispatch()
    {
        // If no ready process, do nothing
        if (rdyIsEmpty())
            return;

        // Retrieve a process from RDY
        uint16_t pid = rdyRetrieve(simTime);
        if (pid == 0)
            return;

        // Get the oldest idle processor (head of idle list)
        uint16_t cid = simIdleHead;
        if (cid == SIM_UNDEF_INDEX)
            throw Exception(EINVAL, __func__);

        // Remove cid from idle list
        uint16_t next = simProcessorState[cid].next;
        if (next == simProcessorCount)
        {
            simIdleHead = SIM_UNDEF_INDEX;
            simIdleTail = SIM_UNDEF_INDEX;
        }
        else
        {
            simIdleHead = next;
        }

        // Update processor state to be in use by pid
        simProcessorState[cid].idle = false;
        simProcessorState[cid].pid = pid;

        // Update process state to RUNNING
        PctProcessState st = RUNNING;
        pctSet(pid, PctState, &st);

        // Get job id for this process
        uint32_t jid = 0;
        pctGet(pid, PctJid, &jid);

        // Get duration of next CPU burst and advance index
        double duration = 0.0;
        jobGet(jid, JobNextBurstDuration, &duration);

        uint32_t index = 0;
        jobGet(jid, JobNextBurstIndex, &index);
        index++;
        jobSet(jid, JobNextBurstIndex, &index);

        // If duration is negative, it's the last CPU burst -> schedule EXIT
        if (duration < 0.0)
        {
            double realDur = -duration;
            feqInsert(simTime + realDur, EXIT, cid);
        }
        else
        {
            // Otherwise, schedule a WAIT_EVENT when CPU burst ends
            feqInsert(simTime + duration, WAIT_EVENT, cid);
        }
    }
} // end of namespace group

