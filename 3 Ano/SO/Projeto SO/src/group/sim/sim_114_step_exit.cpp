/*
 *  \author ...
 */

#include "somm25nm.h"

namespace group
{
    void simStepExit(uint16_t cid)
    {
        /* Add processor to the idle list */
        simProcessorState[cid].idle = true;
        simProcessorState[cid].next = simProcessorCount; // end of list by default

        if (simIdleHead == SIM_UNDEF_INDEX) {
            simIdleHead = cid;
            simIdleTail = cid;
        } else {
            /* link previous tail to this new tail */
            simProcessorState[simIdleTail].next = cid;
            simIdleTail = cid;
        }

        /* Get pid of the process that was running on this processor */
        uint16_t pid = simProcessorState[cid].pid;

        /* Update the process state to ENDED */
        PctProcessState ended = ENDED;
        pctSet(pid, PctState, &ended);

        /* Release memory used by the process, if any */
        uint32_t addr = PCT_UNDEF_ADDRESS;
        pctGet(pid, PctMemAddr, &addr);
        if (addr != PCT_UNDEF_ADDRESS) {
            memFree(addr);
            /* mark process as having no memory */
            uint32_t undef = PCT_UNDEF_ADDRESS;
            pctSet(pid, PctMemAddr, &undef);
        }

        /* Update job finish time */
        uint32_t jid = 0;
        pctGet(pid, PctJid, &jid);
        double t = simTime;
        jobSet(jid, JobFinishTime, &t);

        /* Schedule ACTIVATE and DISPATCH events at current time */
        feqInsert(simTime, ACTIVATE, 0);
        feqInsert(simTime, DISPATCH, 0);
    }
} // end of namespace group

