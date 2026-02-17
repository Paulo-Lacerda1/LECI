/*
 *  Paulo
 */

#include "somm25nm.h"

namespace group
{
    void simStepDelete(uint16_t pid)
    {
        require(pctTable != PCT_UNDEF_TABLE, "PCT module must be open");
        require(pid >= pctPidBase and pid < pctPidBase + pctPidCount, "Invalid PID");

        uint16_t index = pid - pctPidBase;
        if (pctTable[index] == nullptr)
            throw Exception(EINVAL, __func__);

        pctDelete(pid);
    }
} // end of namespace group
