#pragma once
/**
 * \defgroup swp SWP - Swapped-out Queue
 *
 * \details
 *   At the time a process will be admitted to the system, maybe there is not enough main memory
 *   available to store its address space.
 *   In such a case, the process' address space is stored in the swapped area until main memory
 *   becomes available.
 *
 *   The Swapped-out Queue (\c SWP) module is responsible for storing the relevant process' data.
 *   Insertion in this queue is always done at its end (tail).
 *   Removal can be done at any position, depending on the swapping in policy.
 *
 *   The supporting data structure is a single linked list implemented from scratch in this module.
 *   The list must be kept sorted according to the insertion order.
 *
 * \note The use of functions from the **Standard Template Library** are forbidden by default
 *   unless previously agreed with teachers
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>

/** @{ */

// ================================================================================== //

/**
 * \brief Possible swapping-in policies
 * \details
 */
enum SwpSwappingPolicy {
    FirstFit = 1,  ///< First that fits
    FirstBest       ///< First that best fits
};

#define SWP_UNDEF_POLICY ((SwpSwappingPolicy)0xffffff6f)  ///< Pattern indicating the scheduling policy is undefined

// ================================================================================== //

/**
 * \brief Node for the list of swapped processes
 * \details
 */
struct SwpNode
{
    uint16_t pid;           ///< PID of the swapped-out process this node represents
    uint32_t size;          ///< size requested by the process
    bool blocked;           ///< Indicates if the process is blocked or not
    struct SwpNode *next;   ///< pointer no next node
};

#define SWP_UNDEF_NODE ((SwpNode*)0xffffff6e) ///< Pointer pattern indicating the SWP queue is closed (not the same as empty)

// ================================================================================== //

extern SwpNode *swpHead;    ///< Pointer to head of list
extern SwpNode *swpTail;    ///< Pointer to tail of list

extern SwpSwappingPolicy swpPolicy;   ///< Active swapping-in policy

// ================================================================================== //

/**
 * \brief Opens the module, initializing the internal data structure
 * \details
 *   - The module's internal data structure, defined in file \c frontend/swp.cpp,
 *     should be initialized properly
 *
 * \param [in] policy Swapping in policy to be used
 */
void swpOpen(SwpSwappingPolicy policy);

// ================================================================================== //

/**
 * \brief Closes the module, setting the internal data structure to the closed state
 * \details
 *   - In the closed state, the supporting data structure has well-defined values.
 *   - This function, should also release any memory it has dynamically allocated.
 */
void swpClose();

// ================================================================================== //

/**
 * \brief Prints the internal state of the SWP module
 * \details
 *  The current state of the swapped processes queue (SWP) must be
 *  printed to the given stream.
 *
 *  The following must be considered:
 *  - The linked-list elements should be printed in natural order.
 *  - The output must be exactly the same as the one produced by the binary version.
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] fout File stream where to send output
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void swpPrint(FILE *fout, bool csv = false);

// ================================================================================== //

/**
 * \brief Add a new entry to the queue
 * \details
 *  - A new entry should be created and added to the queue.
 *  - The list must be kept sorted according to the insertion order.
 *  - If an anomalous situation occurs, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] pid Id of the process swapped out
 * \param [in] size Amount of memory required by the address space
 * \param [in] blocked Is the process blocked?
 */
void swpInsert(uint16_t pid, uint32_t size, bool blocked);

// ================================================================================== //

/**
 * \brief Remove and return an entry from the SWP queue, accordingly to the active policy
 * \details
 *  - If the list is empty, 0 must be returned;
 *  - otherwise, the process to be retrieved depends on the active policy and
 *    may depends on the value of the \c canBeBlocked parameter:
 *  - If no swapped-out process fits in the memory available, 0 must be returned
 *  - In case of an error, an appropriate exception should be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] sizeAvailable Maximum block size available im main memory
 * \param [in] canBeBlocked flag indicating if the process to be swapped-in can be a blocked one
 * \return PID of the process associated to the removed entry or 0 (zero) if no process fits
 */
uint16_t swpRetrieve(uint32_t sizeAvailable, bool canBeBlocked);

// ================================================================================== //

/**
 * \brief Unblock a swapped-out process
 * \details
 *   The process must be moved from the list of blocked processes to the list of unblocked processes
 * \param pid ID of the process to unblock
 */
void swpUnblock(uint16_t pid);

// ================================================================================== //

/**
 * \brief Inform about the emptiness of the queue
 * \remarks NOT TO BE IMPLEMENTED BY GROUP
 * \return \c true if empty; \c false otherwise
 */
bool swpIsEmpty();

// ================================================================================== //

/** @} */

