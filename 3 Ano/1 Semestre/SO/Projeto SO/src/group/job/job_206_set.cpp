/*
 *  \author Adriana
 */

#include "job.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>

namespace group
{
    void jobSet(uint32_t jid, JobField field, void *value)
    {
        JobNode *node = jobHead;
        while (node != nullptr && node->jid != jid)    // jid que se pretende alterar
            node = node->next;

        if (node == nullptr)    // se não existir o job com o jid 
            throw Exception(EINVAL, __func__);

        switch (field)
        {
            case JobFinishTime:     // tempo de acabar o job
                node->finishTime = *static_cast<double *>(value);
                break;

            case JobNextBurstIndex: // dá update ao índice do próximo burst
                node->nextBurstIndex = *static_cast<uint32_t *>(value);
                break;

            case JobSubmissionTime: // nao é suposto ser alterável
            case JobMemSize:       // nao é suposto ser alterável
            case JobNextBurstDuration: // nao é suposto ser alterável
                throw Exception(EACCES, __func__);

            default:
                throw Exception(EINVAL, __func__);
        }
    }

} // end of namespace somm25nm