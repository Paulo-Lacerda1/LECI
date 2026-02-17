/*
 *  Paulo
 */

#include "rdy.h"
#include "exception.h"

#include <stdio.h>
#include <stdint.h>

namespace group
{
    void rdyOpen(RdySchedulingPolicy policy)
    {
        // validaa o agendamento
        if (policy != SPN && policy != HRRN && policy != SRT)
            throw Exception(EINVAL, __func__);

        // coloca um estado inicial para um queue vazia
        rdyHead = nullptr;
        rdyPolicy = policy;
    }
} // end of namespace group
