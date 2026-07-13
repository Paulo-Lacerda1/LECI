/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "swp.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * The set of supporting variables CAN NOT BE CHANGED
 */
SwpSwappingPolicy swpPolicy = SWP_UNDEF_POLICY;    // The active swapping in policy
SwpNode *swpHead = SWP_UNDEF_NODE;    // Pointer to head of list 
SwpNode *swpTail = SWP_UNDEF_NODE;    // Pointer to tail of list

// ================================================================================== //

namespace binary {
    void swpOpen(SwpSwappingPolicy policy);
    void swpClose();
    void swpPrint(FILE *fout, bool csv = false);
    void swpInsert(uint16_t pid, uint32_t size, bool blocked);
    uint16_t swpRetrieve(uint32_t sizeAvailable, bool blocked);
    void swpUnblock(uint16_t pid);
    bool swpIsEmpty();
}

namespace group {
    void swpOpen(SwpSwappingPolicy policy);
    void swpClose();
    void swpPrint(FILE *fout, bool csv = false);
    void swpInsert(uint16_t pid, uint32_t size, bool blocked);
    uint16_t swpRetrieve(uint32_t sizeAvailable, bool blocked);
    void swpUnblock(uint16_t pid);
    bool swpIsEmpty();
}

// ================================================================================== //

void swpOpen(SwpSwappingPolicy policy)
{
    require(swpHead == SWP_UNDEF_NODE and swpTail == SWP_UNDEF_NODE, "Module must be closed");
    require(policy >= FirstFit and policy <= FirstBest, "Invalid policy");

    const char *pas = "InvalidPattern";
    switch (policy)
    {
        case FirstFit: pas = "FirstFit"; break;
        case FirstBest: pas = "FirstBest"; break;
    }

    if (soBinSelected(601))
    {
        soProbe(SOPROBE_RED, 601, "%s(%s)\n", __func__, pas);
        binary::swpOpen(policy);
    }
    else
    {
        soProbe(601, "%s(%s)\n", __func__, pas);
        group::swpOpen(policy);
    }
}

// ================================================================================== //

void swpClose()
{
    require(swpHead != SWP_UNDEF_NODE and swpTail != SWP_UNDEF_NODE, "Module must be open");

    if (soBinSelected(602))
    {
        soProbe(SOPROBE_RED, 602, "%s()\n", __func__);
        binary::swpClose();
    }
    else
    {
        soProbe(602, "%s()\n", __func__);
        group::swpClose();
    }
}

// ================================================================================== //

void swpPrint(FILE *fout, bool csv)
{
    require(swpHead != SWP_UNDEF_NODE and swpTail != SWP_UNDEF_NODE, "Module must be open");
    require(fout != NULL and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(603))
    {
        soProbe(SOPROBE_RED, 603, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        binary::swpPrint(fout, csv);
    }
    else
    {
        soProbe(603, "%s(%d, %s)\n", __func__, fileno(fout), cas);
        group::swpPrint(fout, csv);
    }
}

// ================================================================================== //

void swpInsert(uint16_t pid, uint32_t size, bool blocked)
{
    require(swpHead != SWP_UNDEF_NODE and swpTail != SWP_UNDEF_NODE, "Module must be open");
    require(pid > 0, "Invalid PID");
    require(size > 0, "Invalid size");

    if (soBinSelected(604))
    {
        soProbe(SOPROBE_RED, 604, "%s(%hu,%u,%s)\n", __func__, pid, size, blocked ? "true" : "false");
        binary::swpInsert(pid, size, blocked);
    }
    else
    {
        soProbe(604, "%s(%hu,%u,%s)\n", __func__, pid, size, blocked ? "true" : "false");
        group::swpInsert(pid, size, blocked);
    }
}

// ================================================================================== //

uint16_t swpRetrieve(uint32_t sizeAvailable, bool blocked)
{
    require(swpHead != SWP_UNDEF_NODE and swpTail != SWP_UNDEF_NODE, "Module must be open");
    require(sizeAvailable > 0, "Invalid sizeAvailable");

    const char *bas = blocked ? "true" : "false";
    if (soBinSelected(605))
    {
        soProbe(SOPROBE_RED, 605, "%s(%u,%s)\n", __func__, sizeAvailable, bas);
        return binary::swpRetrieve(sizeAvailable, blocked);
    }
    else
    {
        soProbe(605, "%s(%u,%s)\n", __func__, sizeAvailable, bas);
        return group::swpRetrieve(sizeAvailable, blocked);
    }
}

// ================================================================================== //

void swpUnblock(uint16_t pid)
{
    require(swpHead != SWP_UNDEF_NODE and swpTail != SWP_UNDEF_NODE, "Module must be open");
    require(pid > 0, "Invalid PID");

    if (soBinSelected(606))
    {
        soProbe(SOPROBE_RED, 606, "%s(%hu)\n", __func__, pid);
        return binary::swpUnblock(pid);
    }
    else
    {
        soProbe(606, "%s(%hu)\n", __func__, pid);
        return group::swpUnblock(pid);
    }
}

// ================================================================================== //

bool swpIsEmpty()
{
    require(swpHead != SWP_UNDEF_NODE, "Module must be open");

    if (soBinSelected(607))
    {
        soProbe(SOPROBE_RED, 607, "%s()\n", __func__);
        return binary::swpIsEmpty();
    }
    else
    {
        soProbe(SOPROBE_GREEN, 607, "%s()\n", __func__);
        return binary::swpIsEmpty();
    }
}

// ================================================================================== //

