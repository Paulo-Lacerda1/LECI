/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "mem.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * The set of supporting variables CAN NOT BE CHANGED
 */
MemNode *memBlocks = MEM_UNDEF_NODE;
uint32_t memBlockCount = 0;
uint16_t memMinLogSize = 0;
MemNode *memFreeHead = MEM_UNDEF_NODE;
MemNode *memOccupiedHead = MEM_UNDEF_NODE;

// ================================================================================== //

namespace binary {
    void memOpen(uint32_t initAddr, uint32_t minLogSize, uint32_t *sizes, uint32_t cnt);
    void memClose();
    void memPrint(FILE *fout, MemPrintMode mode, bool csv = false);
    uint32_t memAlloc(uint32_t pid, uint32_t size);
    void memFree(uint32_t addr);
    uint32_t memBiggestFreeBlock();
}

namespace group {
    void memOpen(uint32_t initAddr, uint32_t minLogSize, uint32_t *sizes, uint32_t cnt);
    void memClose();
    void memPrint(FILE *fout, MemPrintMode mode, bool csv = false);
    uint32_t memAlloc(uint32_t pid, uint32_t size);
    void memFree(uint32_t addr);
    uint32_t memBiggestFreeBlock();
}

// ================================================================================== //

void memOpen(uint32_t initAddr, uint32_t minLogSize, uint32_t *sizes, uint32_t cnt)
{
    require(memBlocks == MEM_UNDEF_NODE and memFreeHead == MEM_UNDEF_NODE and memOccupiedHead == MEM_UNDEF_NODE, "Module must be closed");
    require(sizes != nullptr, "Invalid array of sizes");
    require(cnt > 0, "Invalid cnt");

    if (soBinSelected(701))
    {
        soProbe(SOPROBE_RED, 701, "%s(%#x,%d,%p,%d)\n", __func__, initAddr, minLogSize, sizes, cnt);
        binary::memOpen(initAddr, minLogSize, sizes, cnt);
    }
    else
    {
        soProbe(701, "%s(%#x,%d,%p,%d)\n", __func__, initAddr, minLogSize, sizes, cnt);
        group::memOpen(initAddr, minLogSize, sizes, cnt);
    }
}

// ================================================================================== //

void memClose()
{
    require(memBlocks != MEM_UNDEF_NODE and memFreeHead != MEM_UNDEF_NODE and memOccupiedHead != MEM_UNDEF_NODE, "Module must be open");

    if (soBinSelected(702))
    {
        soProbe(SOPROBE_RED, 702, "%s()\n", __func__);
        binary::memClose();
    }
    else
    {
        soProbe(702, "%s()\n", __func__);
        group::memClose();
    }
}

// ================================================================================== //

void memPrint(FILE *fout, MemPrintMode mode, bool csv)
{
    require(memBlocks != MEM_UNDEF_NODE and memFreeHead != MEM_UNDEF_NODE and memOccupiedHead != MEM_UNDEF_NODE, "Module must be open");
    require(fout != NULL and fileno(fout) != -1, "fout must be a valid file stream");
    require(mode >= MemPrintGlobal and mode <= MemPrintOccupied, "Invalid mode");

    const char *mas;
    switch (mode)
    {
        case MemPrintGlobal: mas = "MemPrintGlobal"; break;
        case MemPrintFree: mas = "MemPrintFree"; break;
        case MemPrintOccupied: mas = "MemPrintOccupied"; break;
    }
    const char *cas = csv ? "true" : "false";

    if (soBinSelected(703))
    {
        soProbe(SOPROBE_RED, 703, "%s(%d, %s, %s)\n", __func__, fileno(fout), mas, cas);
        binary::memPrint(fout, mode, csv);
    }
    else
    {
        soProbe(703, "%s(%d, %s, %s)\n", __func__, fileno(fout), mas, cas);
        group::memPrint(fout, mode, csv);
    }
}

// ================================================================================== //

uint32_t memAlloc(uint32_t pid, uint32_t size)
{
    require(memBlocks != MEM_UNDEF_NODE and memFreeHead != MEM_UNDEF_NODE and memOccupiedHead != MEM_UNDEF_NODE, "Module must be open");
    require(size > 0, "size must be a positive value");

    if (soBinSelected(704))
    {
        soProbe(SOPROBE_RED, 704, "%s(%hu, %#x)\n", __func__, pid, size);
        return binary::memAlloc(pid, size);
    }
    else
    {
        soProbe(704, "%s(%hu, %#x)\n", __func__, pid, size);
        return group::memAlloc(pid, size);
    }
}

// ================================================================================== //

void memFree(uint32_t addr)
{
    require(memBlocks != MEM_UNDEF_NODE and memFreeHead != MEM_UNDEF_NODE and memOccupiedHead != MEM_UNDEF_NODE, "Module must be open");

    if (soBinSelected(705))
    {
        soProbe(SOPROBE_RED, 705, "%s(%#x)\n", __func__, addr);
        return binary::memFree(addr);
    }
    else
    {
        soProbe(705, "%s(%#x)\n", __func__, addr);
        return group::memFree(addr);
    }
}

// ================================================================================== //

uint32_t memBiggestFreeBlock()
{
    require(memBlocks != MEM_UNDEF_NODE and memFreeHead != MEM_UNDEF_NODE and memOccupiedHead != MEM_UNDEF_NODE, "Module must be open");

    uint32_t fid = 706;
    if (soBinSelected(fid))
    {
        soProbe(SOPROBE_RED, fid, "%s()\n", __func__);
        return binary::memBiggestFreeBlock();
    }
    else
    {
        soProbe(704, "%s()\n", __func__);
        return group::memBiggestFreeBlock();
    }
}

// ================================================================================== //

