/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "rdy.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * This set of supporting variables CAN NOT BE CHANGED
 */

RdyNode *rdyHead = RDY_UNDEF_NODE;    // Pointer to head of list

RdySchedulingPolicy rdyPolicy = RDY_UNDEF_POLICY;

// ================================================================================== //

namespace binary {
    void rdyOpen(RdySchedulingPolicy policy);
    void rdyClose();
    void rdyPrint(FILE *fout, bool csv = false);
    void rdyInsert(uint16_t pid, double curTime, double runTime);
    uint16_t rdyRetrieve(double curTime);
    bool rdyIsEmpty();
}

namespace group {
    void rdyOpen(RdySchedulingPolicy policy);
    void rdyClose();
    void rdyPrint(FILE *fout, bool csv = false);
    void rdyInsert(uint16_t pid, double curTime, double runTime);
    uint16_t rdyRetrieve(double curTime);
    bool rdyIsEmpty();
}

// ================================================================================== //

void rdyOpen(RdySchedulingPolicy policy)
{
    require(rdyHead == RDY_UNDEF_NODE, "Module must be closed");

    if (soBinSelected(501))
    {
        soProbe(SOPROBE_RED, 501, "%s()\n", __func__);
        binary::rdyOpen(policy);
    }
    else
    {
        soProbe(501, "%s()\n", __func__);
        group::rdyOpen(policy);
    }
}

// ================================================================================== //

void rdyClose()
{
    require(rdyHead != RDY_UNDEF_NODE, "Module must be open");

    if (soBinSelected(502))
    {
        soProbe(SOPROBE_RED, 502, "%s()\n", __func__);
        binary::rdyClose();
    }
    else
    {
        soProbe(502, "%s()\n", __func__);
        group::rdyClose();
    }
}

// ================================================================================== //

void rdyPrint(FILE *fout, bool csv)
{
    require(rdyHead != RDY_UNDEF_NODE, "Module must be open");
    require(fout != NULL and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(503))
    {
        soProbe(SOPROBE_RED, 503, "%s(%d)\n", __func__, fileno(fout), cas);
        binary::rdyPrint(fout, csv);
    }
    else
    {
        soProbe(503, "%s(%d)\n", __func__, fileno(fout), cas);
        group::rdyPrint(fout, csv);
    }
}

// ================================================================================== //

void rdyInsert(uint16_t pid, double curTime, double runTime)
{
    require(rdyHead != RDY_UNDEF_NODE, "Module must be open");
    require(pid > 0, "a valid process ID must be greater than zero");
    require(curTime >= 0, "curTime must non-negative");
    require(runTime > 0, "runTime must be positive");

    if (soBinSelected(504))
    {
        soProbe(SOPROBE_RED, 504, "%s(%u, %.1f, %.1f)\n", __func__, pid, curTime, runTime);
        binary::rdyInsert(pid, curTime, runTime);
    }
    else
    {
        soProbe(504, "%s(%u, %.1f, %.1f)\n", __func__, pid, curTime, runTime);
        group::rdyInsert(pid, curTime, runTime);
    }
}

// ================================================================================== //

uint16_t rdyRetrieve(double curTime)
{
    require(rdyHead != RDY_UNDEF_NODE, "Module must be open");
    require(curTime >= 0, "curTime must non-negative");

    if (soBinSelected(505))
    {
        soProbe(SOPROBE_RED, 505, "%s(%.1f)\n", __func__, curTime);
        return binary::rdyRetrieve(curTime);
    }
    else
    {
        soProbe(505, "%s(%.1f)\n", __func__, curTime);
        return group::rdyRetrieve(curTime);
    }
}

// ================================================================================== //

bool rdyIsEmpty()
{
    require(rdyHead != RDY_UNDEF_NODE, "Module must be open");

    if (soBinSelected(506))
    {
        soProbe(SOPROBE_RED, 506, "%s()\n", __func__);
        return binary::rdyIsEmpty();
    }
    else
    {
        soProbe(SOPROBE_GREEN, 506, "%s()\n", __func__);
        return binary::rdyIsEmpty();
    }
}

// ================================================================================== //
