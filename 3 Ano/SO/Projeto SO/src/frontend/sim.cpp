/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "sim.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>
#include <math.h>

// ================================================================================== //

/*
 * This set of supporting variables is NOT changeable
 */
double simTime = SIM_UNDEF_TIME; // The current simulation time

uint32_t simProcessorCount = 0; // The actual number of processors
SimProcessorState *simProcessorState = SIM_UNDEF_POINTER; // The array of processor states

uint16_t simIdleHead = SIM_UNDEF_INDEX; // First idle processor to be used
uint16_t simIdleTail = SIM_UNDEF_INDEX; // Last idle processor to be used

// ================================================================================== //

namespace binary {
    void simOpen(SimParameters *param);
    void simClose(bool closeSatelliteModules);
    void simPrint(FILE *fout, uint32_t which = SimPrintNone, bool csv = false);
    void simLoadBatch(FILE *fin, uint32_t maxMemSize);
    void simRun(uint32_t cnt, bool blocking = false);
    bool simStep(bool blocking);
    void simStepSubmit(uint32_t jid);
    void simStepAdmit(uint16_t pid);
    void simStepDispatch();
    void simStepWaitEvent(uint16_t cid);
    void simStepExit(uint16_t cid);
    void simStepEventOccurs(uint16_t pid);
    void simStepActivate();
    void simStepDelete(uint16_t pid);
    void simStepPreempt(uint16_t cid);
    void simStepSuspend(uint16_t pid);
    void simJobLauncher(uint32_t n, uint32_t seed);
}

namespace group {
    void simOpen(SimParameters *param);
    void simClose(bool closeSatelliteModules);
    void simPrint(FILE *fout, uint32_t which = SimPrintNone, bool csv = false);
    void simLoadBatch(FILE *fin, uint32_t maxMemSize);
    void simRun(uint32_t cnt, bool blocking = false);
    bool simStep(bool blocking);
    void simStepSubmit(uint32_t jid);
    void simStepAdmit(uint16_t pid);
    void simStepDispatch();
    void simStepWaitEvent(uint16_t cid);
    void simStepExit(uint16_t cid);
    void simStepEventOccurs(uint16_t pid);
    void simStepActivate();
    void simStepDelete(uint16_t pid);
    void simStepPreempt(uint16_t cid);
    void simStepSuspend(uint16_t pid);
    void simJobLauncher(uint32_t n, uint32_t seed);
}

// ================================================================================== //

void simOpen(SimParameters *param)
{
    require(simTime == SIM_UNDEF_TIME, "Module must be closed");
    require(simIdleHead == SIM_UNDEF_INDEX and simIdleTail == SIM_UNDEF_INDEX, "Module must be closed");
    require(param != nullptr, "Argument 'param' can not be a null pointer");

    if (soBinSelected(101))
    {
        soProbe(SOPROBE_RED, 101, "%s(%p)\n", __func__, param);
        binary::simOpen(param);
    }
    else
    {
        soProbe(101, "%s(%p)\n", __func__, param);
        group::simOpen(param);
    }
}

// ================================================================================== //

void simClose(bool closeSatelliteModules)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    if (soBinSelected(102))
    {
        soProbe(SOPROBE_RED, 102, "%s(%s)\n", __func__, closeSatelliteModules ? "true" : "false");
        binary::simClose(closeSatelliteModules);
    }
    else
    {
        soProbe(102, "%s(%s)\n", __func__, closeSatelliteModules ? "true" : "false");
        group::simClose(closeSatelliteModules);
    }
}

// ================================================================================== //

void simPrint(FILE *fout, uint32_t which, bool csv)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");
    require(fout != nullptr and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(103))
    {
        soProbe(SOPROBE_RED, 103, "%s(%d, %#x, %s)\n", __func__, fileno(fout), which, cas);
        binary::simPrint(fout, which, csv);
    }
    else
    {
        soProbe(103, "%s(%d, %#x, %s)\n", __func__, fileno(fout), which, cas);
        group::simPrint(fout, which, csv);
    }
}

// ================================================================================== //

void simLoadBatch(FILE *fin, uint32_t maxMemSize)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");
    require(fin != nullptr and fileno(fin) != -1, "fin must be a valid file stream");

    if (soBinSelected(104))
    {
        soProbe(SOPROBE_RED, 104, "%s(%d, %#x)\n", __func__, fileno(fin), maxMemSize);
        binary::simLoadBatch(fin, maxMemSize);
    }
    else
    {
        soProbe(104, "%s(%d, %#x)\n", __func__, fileno(fin), maxMemSize);
        group::simLoadBatch(fin, maxMemSize);
    }
}

// ================================================================================== //

void simRun(uint32_t cnt, bool blocking)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    if (soBinSelected(105))
    {
        soProbe(SOPROBE_RED, 105, "%s(%u,%s)\n", __func__, cnt, blocking ? "true" : "false");
        binary::simRun(cnt, blocking);
    }
    else
    {
        soProbe(105, "%s(%u,%s)\n", __func__, cnt, blocking ? "true" : "false");
        group::simRun(cnt, blocking);
    }
}

// ================================================================================== //

bool simStep(bool blocking)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    if (soBinSelected(106))
    {
        soProbe(SOPROBE_RED, 106, "%s(%s)\n", __func__, blocking ? "true" : "false");
        return binary::simStep(blocking);
    }
    else
    {
        soProbe(106, "%s(%s)\n", __func__, blocking ? "true" : "false");
        return group::simStep(blocking);
    }
}

// ================================================================================== //
// ================================================================================== //

void simStepSubmit(uint32_t jid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 110;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(0x%08x)\n", __func__, jid);
        binary::simStepSubmit(jid);
    }
    else
    {
        soProbe(fid, "%s(0x%08x)\n", __func__, jid);
        group::simStepSubmit(jid);
    }
}

// ================================================================================== //

void simStepAdmit(uint16_t pid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 111;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, pid);
        binary::simStepAdmit(pid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, pid);
        group::simStepAdmit(pid);
    }
}

// ================================================================================== //

void simStepDispatch()
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");
    require(simIdleHead != simProcessorCount, "At least one idle processor must exist");
    require(not rdyIsEmpty(), "At least one READY process must exist");

    uint32_t fid = 112;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s()\n", __func__);
        binary::simStepDispatch();
    }
    else
    {
        soProbe(fid, "%s()\n", __func__);
        group::simStepDispatch();
    }
}

// ================================================================================== //

void simStepWaitEvent(uint16_t cid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 113;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, cid);
        binary::simStepWaitEvent(cid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, cid);
        group::simStepWaitEvent(cid);
    }
}

// ================================================================================== //

void simStepExit(uint16_t cid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 114;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, cid);
        binary::simStepExit(cid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, cid);
        group::simStepExit(cid);
    }
}

// ================================================================================== //

void simStepEventOccurs(uint16_t pid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 115;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, pid);
        binary::simStepEventOccurs(pid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, pid);
        group::simStepEventOccurs(pid);
    }
}

// ================================================================================== //

void simStepActivate()
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 116;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s()\n", __func__);
        binary::simStepActivate();
    }
    else
    {
        soProbe(fid, "%s()\n", __func__);
        group::simStepActivate();
    }
}

// ================================================================================== //

void simStepDelete(uint16_t pid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 117;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, pid);
        binary::simStepDelete(pid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, pid);
        group::simStepDelete(pid);
    }
}

// ================================================================================== //

void simStepPreempt(uint16_t cid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 118;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, cid);
        binary::simStepPreempt(cid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, cid);
        group::simStepPreempt(cid);
    }
}

// ================================================================================== //

void simStepSuspend(uint16_t pid)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    uint32_t fid = 119;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s(%hu)\n", __func__, pid);
        binary::simStepSuspend(pid);
    }
    else
    {
        soProbe(fid, "%s(%hu)\n", __func__, pid);
        group::simStepSuspend(pid);
    }
}

// ================================================================================== //
// ================================================================================== //

void simJobLauncher(uint32_t n, uint32_t seed)
{
    require(simTime != SIM_UNDEF_TIME, "Module must be open");
    require(simIdleHead != SIM_UNDEF_INDEX and simIdleTail != SIM_UNDEF_INDEX, "Module must be open");

    if (soBinSelected(120))
    {
        soProbe(SOPROBE_RED, 120, "%s(%u,%u)\n", __func__, n, seed);
        binary::simJobLauncher(n, seed);
    }
    else
    {
        soProbe(120, "%s(%u,%u)\n", __func__, n, seed);
        group::simJobLauncher(n, seed);
    }
}

// ================================================================================== //

