/*
 * \author Diogo Ferreira
 */

#include "somm25nm.h"

namespace group
{
    void simOpen(SimParameters *param)
    {
        require(param != NULL, "param must be non-NULL");
        require(param->processorCount > 0, "processor count must be positive");
        // Ensure processorCount fits in uint16_t as used in SimProcessorState::next and simIdleTail
        require(param->processorCount <= 0xFFFE, "processor count too high for uint16_t indexing");

        // Initialize simulation time
        simTime = 0.0;

        // Initialize processor count
        simProcessorCount = param->processorCount;

        // Allocate and initialize processor state array
        // Note: In a production environment, we should check for allocation failure (ENOMEM)
        simProcessorState = new SimProcessorState[simProcessorCount];

        // Initialize the linked list of idle processors
        // The list is kept sorted in chronological order of release (implicitly by index initially)
        for (uint32_t i = 0; i < simProcessorCount; i++)
        {
            simProcessorState[i].idle = true;
            simProcessorState[i].next = (uint16_t)(i + 1);
        }
        
        // The last processor points to the sentinel value (simProcessorCount)
        simProcessorState[simProcessorCount - 1].next = (uint16_t)simProcessorCount;

        // Set head and tail of the idle list
        simIdleHead = 0;
        simIdleTail = (uint16_t)(simProcessorCount - 1);

        // Open satellite modules
        jobOpen();
        pctOpen(param->basePid, param->maxPids);
        feqOpen();
        rdyOpen(param->schedulingPolicy);
        swpOpen(param->swappingPolicy);
        memOpen(param->memInitAddr, param->memMinLogSize, param->memSizes, param->memSizesCount);
    }
} // end of namespace group