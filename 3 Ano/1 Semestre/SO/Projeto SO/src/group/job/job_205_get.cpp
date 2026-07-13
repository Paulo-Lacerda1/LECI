/*
 *  Paulo
 */

#include "job.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>

namespace group
{
    void jobGet(uint32_t jid, JobField field, void *value)
    {
        JobNode *node = jobHead;
        while (node != nullptr && node->jid != jid)     // job com o jid pretendido
            node = node->next;

        if (node == nullptr)
            throw Exception(EINVAL, __func__);

        switch (field)
        {
            case JobSubmissionTime:
                *static_cast<double *>(value) = node->submissionTime;
                break;
                
            case JobFinishTime:
                *static_cast<double *>(value) = node->finishTime;
                break;

            case JobMemSize:
                *static_cast<uint32_t *>(value) = node->memSize;
                break;

            case JobNextBurstIndex:
                *static_cast<uint32_t *>(value) = node->nextBurstIndex;
                break;

            case JobNextBurstDuration: {
                uint32_t index = node->nextBurstIndex;
                double duration = 0;

                if (index < JOB_MAX_BURSTS)
                {
                    duration = node->bursts[index];
                    if (duration != 0 && (index % 2 == 0))
                    {
                        bool lastCpu = (index + 1 >= JOB_MAX_BURSTS) || (node->bursts[index + 1] == 0);
                        if (lastCpu)
                            duration = -duration;           // se for o último burst,fica negativo  
                    }
                }

                *static_cast <double *>(value) = duration;
                break;
            }
            default:
                throw Exception(EINVAL, __func__);
        }
    }

} // end of namespace somm25nm
