#pragma once
/**
 * \defgroup feq FEQ - Future Event Queue
 *
 * \details
 *   Several different types of events make the system evolve:
 *   SUBMIT, ADMIT, DISPATCH, WAIT_EVENT, EVENT_OCCURS, EXIT, ACTIVATE, SUSPEND, TIMEOUT, and PREEMPT.
 *
 *   The Future Event Queue (\c feq) module is responsible for storing these events and
 *   allowing them to be accessed in chronological order.
 *
 *   The supporting data structure is a single linked list implemented from scratch in this module.
 *   A module'a node is composed of:
 *   - the time at which the future event will occur;
 *   - the type of event, one of the above referred;
 *   - the identification of the process, job or processor associated to the event;
 *   - a pointer to the next node.
 *
 *   The queue must be kept sorted, according to the following criteria:
 *   - the queue tuples should be sorted in ascending order of event time;
 *   - for tuples with the same time stamp, DISPATCH events should appear first
 *   - otherwise, WAIT_EVENT, EXIT, TIMEOUT and PREEMPT events
 *     should appear before other types of events;
 *   - otherwise, order of insertion should be preserved.
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>

/** @{ */

// ================================================================================== //

/**
 * \anchor FeqEventType
 * \brief Possible types of future events
 * \details
 */
enum FeqEventType {
    SUBMIT,
    ADMIT,
    DISPATCH,
    TIMEOUT,
    PREEMPT,
    WAIT_EVENT,
    EVENT_OCCURS,
    SUSPEND,
    ACTIVATE,
    EXIT,
    DELETE
};

// ================================================================================== //

/**
 * \brief Node definition for the queue of future events
 * \details
 *   A <b>future event</b> is composed of:
 *   - the type of the event (\ref FeqEventType)
 *   - the identification of the job/process/processor producing the event
 *   - the time at which the event will occur
 *
 */
struct FeqNode
{
    double time;     ///< The time at which the event will occur
    FeqEventType type;   ///< The type of event
    uint32_t xid;    ///< Identification of the job/process/processor producing the event
    FeqNode *next;   ///< Pointer to next node
};

#define FEQ_UNDEF_NODE ((FeqNode*)0xffffff4e) ///< Pattern indicating the FEQ queue is closed (not the same as empty)

// ================================================================================== //

extern FeqNode *feqHead;       ///< Pointer to head of queue

// ================================================================================== //

/**
 * \brief Opens the module, initializing the internal data structure
 * \details
 *   The module's internal data structure, defined in file \c frontend/feq.cpp,
 *   should be initialized properly.
 *
 *   This is a quite trivial function.
 */
void feqOpen();

// ================================================================================== //

/**
 * \brief Closes the module, setting the internal data structure to the closed state
 * \details
 *   - Any dinamically allocated variables should be freed
 *   - In the closed state, the supporting data structure has well-defined values.
 */
void feqClose();

// ================================================================================== //

/**
 * \brief Prints the internal state of the FEQ module
 * \details
 *  The current state of the future event queue (FEQ) must be
 *  printed to the given stream.
 *
 *  The following must be considered:
 *  - The linked-list elements should be printed in natural order.
 *  - The output must be the same as the one produced by the binary version.
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] fout File stream where to send output
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void feqPrint(FILE *fout, bool csv = false);

// ================================================================================== //

/**
 * \brief Add a new tuple to the FEQ queue
 * \details
 *  A new entry should be created and added to the future event queue, such that:
 *  - the queue tuples should be sorted in ascending order of event time;
 *  - for tuples with the same time stamp, DISPATCH events should appear first
 *  - otherwise, WAIT_EVENT, EXIT, TIMEOUT and PREEMPT events
 *    should appear before other types of events;
 *  - otherwise, order of insertion should be preserved.
 *
 *  The following must be considered:
 *  - If an anomalous situation occurs, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] time The time at which the event will occur
 * \param [in] type The type of event
 * \param [in] xid ID of the job/process producing the event
 */
void feqInsert(double time, FeqEventType type, uint32_t xid);

// ================================================================================== //

/**
 * \brief Remove and returns data of the first event from the list
 * \details
 *  The following must be considered:
 *  - The node <b>must be removed</b> from the queue.
 *  - In the normal work to be delivered, blocking mode will not be used
 *  - In non blocking mode, the function returns \c false if the queue is empty and \c true otherwise
 *
 * \param time Pointer to the recipient where the time of occurrence of the event is to be stored
 * \param type Pointer to the recipient where the type of the event is to be stored
 * \param xid Pointer to the recipient where the ID of the job/process is to be stored
 * \param blocking Flag indicating the blocking mode
 * \return \c true if an event was retrieveed; \c false if \c blocking is false and the queue is empty
 */
bool feqRetrieve(double *time, FeqEventType *type, uint32_t *xid, bool blocking = false);

// ================================================================================== //

/** @} */

