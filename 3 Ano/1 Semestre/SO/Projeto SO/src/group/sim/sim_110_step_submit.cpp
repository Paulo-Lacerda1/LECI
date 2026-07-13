/*
 *  Paulo
 */

#include "somm25nm.h"

#include <errno.h>

namespace group
{
    namespace
    {
        void rejectJob(uint32_t jid)
        {
            double finishTime = simTime;
            jobSet(jid, JobFinishTime, &finishTime);
        }
    }

    void simStepSubmit(uint32_t jid)
    {
        uint16_t pid = 0;

        try
        {
            pid = pctNew(jid);
        }
        catch (const Exception &e)
        {
            if (e.en == ENOSPC || e.en == EAGAIN)
            {
                rejectJob(jid);
                return;
            }
            throw;
        }

        if (pid == 0)
        {
            rejectJob(jid);
            return;
        }

        feqInsert(simTime, ADMIT, pid);
    }
} // end of namespace group
