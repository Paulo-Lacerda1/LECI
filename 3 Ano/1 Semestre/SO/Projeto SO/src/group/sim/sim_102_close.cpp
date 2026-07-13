/*
 *  Rafael Ferreira - 119356
 */

#include "somm25nm.h"
#include "sim.h"
#include "job.h"
#include "feq.h"
#include "mem.h"
#include "pct.h"
#include "rdy.h"
#include "swp.h"

namespace group 
{
    void simClose(bool closeSatelliteModules) 
    {
        if (closeSatelliteModules) {
            jobClose();
            feqClose();
            memClose();
            pctClose();
            rdyClose();
            swpClose();
        }

        if (simProcessorState != SIM_UNDEF_POINTER) {
            delete [] simProcessorState;
        }

        simTime = SIM_UNDEF_TIME;
        simProcessorCount = 0;
        simProcessorState = SIM_UNDEF_POINTER;
        simIdleHead = SIM_UNDEF_INDEX;
        simIdleTail = SIM_UNDEF_INDEX;
    }
} // end of namespace group

