#pragma once
/**
 * \defgroup mem MEM - Memory Management
 *
 * \details
 *    Memory management follows a contiguous memory allocation approach with
 *    main memory being divided into a number of static partitions at system generation time.
 *    The address space of a process may be loaded into any free single partition of memory
 *    whose size is equal or greater size than the size required by the process.<br>
 *
 *  - The whole memory is represented by an array of nodes, each one representing a block
 *  - A block is characterized by its log size, its start address, and the PID of a process, if in use
 *  - Nodes are kept in two lists, representing free and occupied blocks
 *  - The free list is kept sorted by log size and time of insertion
 *  - The occupied list is kept sorted by start address of the blocks
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>

/** @{ */

// ================================================================================== //

/**
 * \brief Node definition for the lists of free and occupied memory blocks
 * \details
 */
struct MemNode
{
    uint32_t addr;          ///< initial address of the block this node represents
    uint16_t logSize;       ///< log2 of the block size in bytes
    uint16_t pid;           ///< PID of a process using the block this node represents; 0 if free
    MemNode *next;          ///< pointer to next node
};

#define MEM_UNDEF_NODE ((MemNode*)0xffffff7e) ///< Pointer pattern indicating a list is closed (not the same as empty)

// ================================================================================== //

/**
 * \brief Indication of which memory state is requested
 */
enum MemPrintMode {
    MemPrintGlobal, ///< Print state of every block
    MemPrintFree, ///< Print only free blocks
    MemPrintOccupied ///< Print only occupied blocks
};

// ================================================================================== //

extern MemNode *memBlocks;          ///< Pointer to the array representing all the blocks
extern uint32_t memBlockCount;      ///< Total number of blocks
extern uint16_t memMinLogSize;      ///< log2 size of the smallest block
extern MemNode *memFreeHead;        ///< Pointer to head of list of free blocks
extern MemNode *memOccupiedHead;    ///< Pointer to head of list of occupied blocks

// ================================================================================== //

/**
 * \brief Open the MEM module, initializing its internal data structure
 * \details
 *  The module's internal data structure, defined in file \c frontend/mem.cpp,
 *  should be initialized properly.<br>
 *  Main memory is divided into a fixed number of static partitions at system generation time.
 *  Not all partitions have the same size.
 *  All partition sizes are powers of 2, starting at a given exponent and incrementing.
 *  Thus, sizes[i] indicates the number of blocks with size pow(2,minLogSize+i).
 *  The number of entries in the array \c sizes is given by parameter \c cnt.
 *  Any entry of the array \c sizes may be 0, meaning there are no blocks of the size the entry represents.
 *
 *  The following must be considered:
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] initAddr Initial address for processes
 * \param [in] minLogSize log2 of the minimum size of a block
 * \param [in] sizes Pointer to array containing the amount of blocks of every size
 * \param [in] cnt Number of elements in array \c sizes
 */
void memOpen(uint32_t initAddr, uint32_t minLogSize, uint32_t *sizes, uint32_t cnt);

// ================================================================================== //

/**
 * \brief Free any dynamic memory allocated by the module
 *   and reset supporting data structures to the close state
 */
void memClose();

// ================================================================================== //

/**
 * \brief Print the internal state of the memory management module
 * \details
 *  The current state of the list of free and of the list of occupied blocks
 *  (module MEM) must be
 *  printed to the given file.<br>
 *  The following must be considered:
 *  - For each list, the printing must be done in ascending order of block address.
 *  - The output must be the same as the one produced by the binary version.
 *  - If print mode is NEW, print to a new file (zapping if necessary).
 *  - If print mode is APPEND, append printing to the end of the file.
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 * \remarks See the result of the binary version, to replicate its behavior
 *
 * \param [in] fout File stream where to send output
 * \param [in] mode Defines the kind of output to be produced
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void memPrint(FILE *fout, MemPrintMode mode, bool csv = false);

// ================================================================================== //

/**
 * \brief Try to allocate the address space profile of a process
 * \details
 *  ...
 *
 * \param [in] pid PID of the process requesting memory
 * \param [in] size Amount of memory (not the log size) requested by the process
 *
 * \return The reference index of block assigned to the process; 0 if none
 */
uint32_t memAlloc(uint32_t pid, uint32_t size);

// ================================================================================== //

/**
 * \brief Free a previously allocated address space mapping
 * \details
 *  ...
 *
 * \param [in] addr initial memory address of the block to be released
 */
void memFree(uint32_t addr);

// ================================================================================== //

/**
 * \brief Returns the size of the biggest free memory block
 * \return The size of the biggest free block or 0 if none
 */
uint32_t memBiggestFreeBlock();

// ================================================================================== //

/** @} */


