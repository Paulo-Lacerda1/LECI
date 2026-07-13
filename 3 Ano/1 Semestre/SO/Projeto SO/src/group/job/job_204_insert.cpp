/*
 *  \author: Rafael Ferreira - 119356
 */

#include "job.h"
#include "exception.h"

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

namespace group
{

// ================================================================================== //

    void jobInsert(uint32_t jid, double submissionTime, uint32_t memSize, double *burstProfile)
    {
        /* TODO POINT: Replace next instruction with your code */
        if (burstProfile == nullptr) 
            throw Exception(EINVAL, __func__);

        JobNode *node = (JobNode*)malloc(sizeof(JobNode));
        if (node == nullptr) 
            throw Exception(errno, __func__);

        node->jid = jid;
        node->submissionTime = submissionTime;
        node->finishTime = JOB_UNDEF_TIME;
        node->memSize = memSize;
        node->nextBurstIndex = 0;
        for (int i = 0; i < JOB_MAX_BURSTS; i++) node->bursts[i] = burstProfile[i];
        node->next = nullptr;

        if (jobHead == nullptr) {
            jobHead = node;
            return;
        }

        if (jid < jobHead->jid) {
            node->next = jobHead;
            jobHead = node;
            return;
        }

        JobNode *prev = jobHead;
        JobNode *curr = jobHead->next;
        while(curr != nullptr && curr->jid < jid) {
            prev = curr;
            curr = curr->next;
        }

        if (curr != nullptr && curr->jid == jid) {
            free(node);
            throw Exception(EINVAL, __func__);
        }

        prev->next = node;
        node->next = curr;
    }

// ================================================================================== //

} // end of namespace group

