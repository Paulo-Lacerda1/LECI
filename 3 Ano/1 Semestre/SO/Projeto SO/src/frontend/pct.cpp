/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "pct.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * This set of supporting variables is NOT changeable
 */
PctNode **pctTable = PCT_UNDEF_TABLE;
uint16_t pctLastPid = 0; // PID assigned to the last accepted job
uint16_t pctPidBase = 0; // Lowest PID value
uint16_t pctPidCount = 0; // Maximum number of processes allowed

// ================================================================================== //

namespace binary {
    void pctOpen(uint16_t base, uint16_t cnt);
    void pctClose();
    void pctPrint(FILE *fout, bool csv = false);
    uint16_t pctNew(uint32_t jid);
    void pctGet(uint16_t pid, PctField field, void *value);
    void pctSet(uint16_t pid, PctField field, void *value);
    void pctDelete(uint16_t pid);
}

namespace group {
    void pctOpen(uint16_t base, uint16_t cnt);
    void pctClose();
    void pctPrint(FILE *fout, bool csv = false);
    uint16_t pctNew(uint32_t jid);
    void pctGet(uint16_t pid, PctField field, void *value);
    void pctSet(uint16_t pid, PctField field, void *value);
    void pctDelete(uint16_t pid);
}

// ================================================================================== //

void pctOpen(uint16_t base, uint16_t cnt)
{
    require(base > 0, "The base PID should be greater than zero");
    require(cnt > 0, "The maximum number of processes should be greater than zero");
    require(pctTable == PCT_UNDEF_TABLE, "Module must be closed");

    if (soBinSelected(301))
    {
        soProbe(SOPROBE_RED, 301, "%s(%hu, %hu)\n", __func__, base, cnt);
        binary::pctOpen(base, cnt);
    }
    else
    {
        soProbe(301, "%s(%hu, %hu)\n", __func__, base, cnt);
        group::pctOpen(base, cnt);
    }
}

// ================================================================================== //

void pctClose()
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");

    if (soBinSelected(302))
    {
        soProbe(SOPROBE_RED, 302, "%s()\n", __func__);
        binary::pctClose();
    }
    else
    {
        soProbe(302, "%s()\n", __func__);
        group::pctClose();
    }
}

// ================================================================================== //

void pctPrint(FILE *fout, bool csv)
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");
    require(fout != NULL and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(303))
    {
        soProbe(SOPROBE_RED, 303, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        binary::pctPrint(fout, csv);
    }
    else
    {
        soProbe(303, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        group::pctPrint(fout, csv);
    }
}

// ================================================================================== //

uint16_t pctNew(uint32_t jid)
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");

    if (soBinSelected(304))
    {
        soProbe(SOPROBE_RED, 304, "%s(%8x)\n", __func__, jid);
        return binary::pctNew(jid);
    }
    else
    {
        soProbe(304, "%s(%8x)\n", __func__, jid);
        return group::pctNew(jid);
    }
}

// ================================================================================== //

void pctGet(uint16_t pid, PctField field, void *value)
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");
    require(pid >= pctPidBase and pid < pctPidBase + pctPidCount, "Invalid PID");
    require(field >= PctJid and field <= PctState, "Invalid field name");
    require(value != nullptr, "Invalid pointer");

    const char *fas = "Invalid field name";
    switch (field)
    {
        case PctState: fas = "PctState"; break;
        case PctMemAddr: fas = "PctMem"; break;
        case PctJid: fas = "PctJid"; break;
    }

    if (soBinSelected(305))
    {
        soProbe(SOPROBE_RED, 305, "%s(%hu,%s,%p)\n", __func__, pid, fas, value);
        binary::pctGet(pid, field, value);
    }
    else
    {
        soProbe(305, "%s(%hu,%s,%p)\n", __func__, pid, fas, value);
        group::pctGet(pid, field, value);
    }
}

// ================================================================================== //

void pctSet(uint16_t pid, PctField field, void *value)
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");
    require(pid >= pctPidBase and pid < pctPidBase + pctPidCount, "Invalid PID");
    require(field >= PctJid and field <= PctState, "Invalid field name");
    require(value != nullptr, "Invalid pointer");

    const char *fas = "Invalid field name";
    switch (field)
    {
        case PctState: fas = "PctState"; break;
        case PctMemAddr: fas = "PctMem"; break;
        case PctJid: fas = "PctJid"; break;
    }

    if (soBinSelected(306))
    {
        soProbe(SOPROBE_RED, 306, "%s(%hu,%s,%p)\n", __func__, pid, fas, value);
        binary::pctSet(pid, field, value);
    }
    else
    {
        soProbe(306, "%s(%hu,%s,%p)\n", __func__, pid, fas, value);
        group::pctSet(pid, field, value);
    }
}

// ================================================================================== //

void pctDelete(uint16_t pid)
{
    require(pctPidCount != 0 and pctPidBase != 0 and pctTable != PCT_UNDEF_TABLE, "Module must be open");
    require(pid >= pctPidBase and pid < pctPidBase + pctPidCount, "Invalid PID");

    if (soBinSelected(307))
    {
        soProbe(SOPROBE_RED, 307, "%s(%hu)\n", __func__, pid);
        binary::pctDelete(pid);
    }
    else
    {
        soProbe(307, "%s(%hu)\n", __func__, pid);
        group::pctDelete(pid);
    }
}

// ================================================================================== //

