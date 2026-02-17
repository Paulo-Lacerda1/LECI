/*
 *  \author ...
 */

#include "somm25nm.h"

namespace group
{
    void simStepSuspend(uint16_t pid)
    {
        // Get current state of process
        PctProcessState curState;
        pctGet(pid, PctState, &curState);

        // Only READY or BLOCKED processes may be swapped-out
        bool blocked = false;
        if (curState == READY)
        {
            blocked = false;
            PctProcessState newState = S_READY;
            pctSet(pid, PctState, &newState);
        }
        else if (curState == BLOCKED)
        {
            blocked = true;
            PctProcessState newState = S_BLOCKED;
            pctSet(pid, PctState, &newState);
        }
        else
        {
            throw Exception(EPERM, __func__); // disallowed by current state
        }

        // Retrieve memory address and ensure it's present
        uint32_t addr = PCT_UNDEF_ADDRESS;
        pctGet(pid, PctMemAddr, &addr);
        if (addr == PCT_UNDEF_ADDRESS)
            throw Exception(EINVAL, __func__); // nothing to swap out

        // Free memory block used by process
        memFree(addr);

        // Mark process as having no memory
        uint32_t undef = PCT_UNDEF_ADDRESS;
        pctSet(pid, PctMemAddr, &undef);

        // Get the job's memory size
        uint32_t jid = 0;
        pctGet(pid, PctJid, &jid);
        uint32_t size = 0;
        jobGet(jid, JobMemSize, &size);

        // Insert into swap queue with blocked flag set accordingly
        swpInsert(pid, size, blocked);
    }
} // end of namespace group

