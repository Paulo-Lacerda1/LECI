#pragma once
/**
 * \defgroup rdy RDY Ready-to-Run Process Queue
 *
 * \details
 *   At the time a job is admitted to the pool of active processes,
 *   a new process is created to execute the job.
 *   The state of this new process depends on memory availability.
 *   If, its logical address space was stored in main memory, the process goes to the READY state,
 *   waiting for a processor.
 *   Otherwise, it is put in the SUSPENDED READY state, waiting for memory to be available.
 *   The Ready Process Queue (\c rdy) module is responsible for storing the relevant data
 *   of processes in the READY state.
 *   The data is the one required to support the different scheduling policies to be implemented
 *   in this project.
 *
 *   The supporting data structure of the module is a single linked list implemented from scratch in this module, where:
 *   - List elements are kept sorted in ascending order of burst duration
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
 * \brief Possible scheduling policies
 * \details
 */
enum RdySchedulingPolicy {
    //FCFS,       ///< First Come First Served policy
    SPN,        ///< Shortest Process Next policy
    HRRN,       ///< Highest Response Ratio Next policy
    //RR,         ///< Round Robin policy
    SRT         ///< Shortest Remaining Time policy
};

#define RDY_UNDEF_POLICY ((RdySchedulingPolicy)0xffffff5f)  ///< Pattern indicating the scheduling policy is undefined

// ================================================================================== //

/**
 * \brief Node definition for the queue of ready-to-run processes
 * \details
 */
struct RdyNode
{
    uint16_t pid;           ///< PID of a process
    double queueTime;       ///< Time at which the process was inserted into the RDY queue
    double runTime;         ///< Estimated time of the next process' CPU burst
    RdyNode *next;          ///< pointer to next node
};

#define RDY_UNDEF_NODE ((RdyNode*)0xffffff5e) ///< Pointer pattern indicating the queue RDY is closed (not the same as empty)

// ================================================================================== //

extern RdyNode *rdyHead;    ///< Pointer to head of list

extern RdySchedulingPolicy rdyPolicy;   ///< Scheduling policy in use

// ================================================================================== //

/**
 * \brief Opens the module, initializing the internal data structure
 * \details
 *   - The module's internal data structure, defined in file \c frontend/rdy.cpp,
 *     should be initialized properly
 *
 * \param [in] policy Sceduling policy to be used
 */
void rdyOpen(RdySchedulingPolicy policy);

// ================================================================================== //

/**
 * \brief Closes the module, setting the internal data structure to the closed state
 * \details
 *   - In the closed state, the supporting data structure has well-defined values.
 *   - This function, should also release any memory it has dynamically allocated.
 */
void rdyClose();

// ================================================================================== //

/**
 * \brief Prints the internal state of the RDY module
 * \details
 *  The current state of the ready queue (RDY) must be
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
void rdyPrint(FILE *fout, bool csv = false);

// ================================================================================== //

/**
 * \brief Add a new entry to the queue
 * \details
 *  - A new entry should be created and added to the queue.
 *  - List elements should be kept sorted in ascending order of burst duration.
 *  - If an anomalous situation occurs, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] pid Id of the process associated to the event
 * \param [in] curTime The current time
 * \param [in] runTime Estimated time the process will take to run the next CPU burst
 */
void rdyInsert(uint16_t pid, double curTime, double runTime);

// ================================================================================== //

/**
 * \brief Remove and return an entry from the queue, following the HRRN policy
 * \details
 *  - If the list is empty, 0 must be returned;
 *  - otherwise, the process to be retrieved depends on the scheduling policy:
 *  - In case of an error, an appropriate exception should be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] curTime The current time
 * \return The PID of the process retrieveed from the list; 0 if list is empty
 */
uint16_t rdyRetrieve(double curTime);

// ================================================================================== //

/**
 * \brief Inform about the emptiness of the queue
 * \remarks NOT TO BE IMPLEMENTED BY GROUP
 * \return \c true if empty; \c false otherwise
 */
bool rdyIsEmpty();

// ================================================================================== //

/** @} */

