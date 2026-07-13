/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "feq.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * This set of supporting variables is NOT changeable
 */
FeqNode *feqHead = FEQ_UNDEF_NODE;     // Pointer to head of queue

// ================================================================================== //

namespace binary {
    void feqOpen();
    void feqClose();
    void feqPrint(FILE *fout, bool csv = false);
    void feqInsert(double time, FeqEventType type, uint32_t xid);
    bool feqRetrieve(double *time, FeqEventType *type, uint32_t *xid, bool blocking = false);
}

namespace group {
    void feqOpen();
    void feqClose();
    void feqPrint(FILE *fout, bool csv = false);
    void feqInsert(double time, FeqEventType type, uint32_t xid);
    bool feqRetrieve(double *time, FeqEventType *type, uint32_t *xid, bool blocking = false);
}

// ================================================================================== //

void feqOpen()
{
    require(feqHead == FEQ_UNDEF_NODE, "Module must be closed");

    if (soBinSelected(401))
    {
        soProbe(SOPROBE_RED, 401, "%s()\n", __func__);
        binary::feqOpen();
    }
    else 
    {
        soProbe(401, "%s()\n", __func__);
        group::feqOpen();
    }
}

// ================================================================================== //

void feqClose()
{
    require(feqHead != FEQ_UNDEF_NODE, "Module must be open");

    if (soBinSelected(402))
    {
        soProbe(SOPROBE_RED, 402, "%s()\n", __func__);
        binary::feqClose();
    }
    else
    {
        soProbe(402, "%s()\n", __func__);
        group::feqClose();
    }
}

// ================================================================================== //

void feqPrint(FILE *fout, bool csv)
{
    require(feqHead != FEQ_UNDEF_NODE, "Module must be open");
    require(fout != NULL and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(403))
    {
        soProbe(SOPROBE_RED, 403, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        binary::feqPrint(fout, csv);
    }
    else
    {
        soProbe(403, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        group::feqPrint(fout, csv);
    }
}

// ================================================================================== //

void feqInsert(double time, FeqEventType type, uint32_t xid)
{
    require(type >= SUBMIT and type <= DELETE, "Invalid value for parameter 'type'");
    require(time >= 0, "Invalid valur for parameter 'time'");

    const char *tas = "Invalid type";
    switch (type) 
    {
        case SUBMIT: tas = "SUBMIT"; break;
        case ADMIT: tas = "ADMIT"; break;
        case DISPATCH: tas = "DISPATCH"; break;
        case TIMEOUT: tas = "TIMEOUT"; break;
        case PREEMPT: tas = "PREEMPT"; break;
        case WAIT_EVENT: tas = "WAIT_EVENT"; break;
        case EVENT_OCCURS: tas = "EVENT_OCCURS"; break;
        case SUSPEND: tas = "SUSPEND"; break;
        case ACTIVATE: tas = "ACTIVATE"; break;
        case EXIT: tas = "EXIT"; break;
        case DELETE: tas = "DELETE"; break;
    }

    if (soBinSelected(404))
    {
        soProbe(SOPROBE_RED, 404, "%s(%.1f,%s,%x)\n", __func__, time, tas, xid);
        binary::feqInsert(time, type, xid);
    }
    else
    {
        soProbe(404, "%s(%.1f,%s,%x)\n", __func__, time, tas, xid);
        group::feqInsert(time, type, xid);
    }
}

// ================================================================================== //

bool feqRetrieve(double *time, FeqEventType *type, uint32_t *xid, bool blocking)
{
    require(xid != nullptr, "'xid' must be a valid pointer");
    require(type != nullptr, "'type' must be a valid pointer");
    require(time != nullptr, "'time' must be a valid pointer");

    if (soBinSelected(405))
    {
        soProbe(SOPROBE_RED, 405, "%s(%p,%p,%p)\n", __func__, time, type, xid, blocking ? "true" : "false");
        return binary::feqRetrieve(time, type, xid, blocking);
    }
    else
    {
        soProbe(405, "%s(%p,%p,%p)\n", __func__, time, type, xid, blocking ? "true" : "false");
        return group::feqRetrieve(time, type, xid, blocking);
    }
}

// ================================================================================== //

