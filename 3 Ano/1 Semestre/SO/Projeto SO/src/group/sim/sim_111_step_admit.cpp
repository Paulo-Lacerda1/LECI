/*
 *  Rafael Ferreira 119356
 */

#include "somm25nm.h"

namespace group
{
    void simStepAdmit(uint16_t pid)
    {
        uint32_t jid;
        pctGet(pid, PctJid, &jid);

        uint32_t memSize;
        jobGet(jid, JobMemSize, &memSize);

        uint32_t addr = memAlloc(pid, memSize);

        if (addr != 0)
        {
            PctProcessState st = READY;
            pctSet(pid, PctState, &st);
            double runtime;
            jobGet(jid, JobNextBurstDuration, &runtime);

            rdyInsert(pid, simTime, runtime);

            if (simIdleHead != SIM_UNDEF_INDEX)
            {
                feqInsert(simTime, DISPATCH, 0);
            }
        }
        else
        {
            PctProcessState st = S_READY;
            pctSet(pid, PctState, &st);

            swpInsert(pid, memSize, false);
        }
    }
} // end of namespace group

