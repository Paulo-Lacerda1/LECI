#pragma once
/**
 * \defgroup pct PCT - Process Control Table
 *
 * \details
 *  This modules is responsible for keeping information of the active processes.
 *  The information kept is the followig:
 *  - ID of the job this process is related to
 *  - Start address of the memory where the address space is stored
 *  - The state of the process
 *
 *  The state of execution of the associated job is not kept here, but in module JOB.
 *
 *  When a new job is admitted for execution (as soon as it is submitted, in this project),
 *  a new process is created to be its context of execution.
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>

/** @{ */

// ================================================================================== //

/**
 * \brief Possible states a process can be
 * \details
 */
enum PctProcessState {
    NEW,        ///< a new process was created (a transient state)
    RUNNING,    ///< the process is running (using a processor)
    BLOCKED,    ///< the process is blocked (waiting for the end of an I/O burst)
    READY,      ///< the process is ready to run (waiting for a processor)
    S_BLOCKED,    ///< the process is suspended-blocked (swapped-out, waiting for the end of an I/O burst)
    S_READY,      ///< the process is suspended-ready (ready-to-run but swapped-out)
    ENDED, ///< the process has already finished its lifetime (a transient state)
};

// ================================================================================== //

/**
 * \brief Node for the PCT queue
 */
struct PctNode {
    uint32_t jid;           ///< ID of the associated job
    uint32_t memAddr;           ///< Start address of the memory block assigned to the process
    PctProcessState state;  ///< The state the process is at a given moment
};

#define PCT_UNDEF_TABLE ((PctNode**)0xffffff3e) ///< Pointer pattern indicating the PCT queue is closed (not the same as empty)

#define PCT_UNDEF_ADDRESS 0xffffff3f   ///< Time pattern indicating memory is undefined

// ================================================================================== //

enum PctField { PctJid = 1, PctMemAddr, PctState };

// ================================================================================== //

extern PctNode **pctTable; ///< Process Control Table
extern uint16_t pctLastPid;    ///< PID assigned to the last admitted job
extern uint16_t pctPidBase; ///< Lowest PID value for a process
extern uint16_t pctPidCount; ///< Maximum number of user processes allowed

// ================================================================================== //

/**
 * \brief Opens the module, initializing the internal data structure
 * \details
 *   The module's internal data structure, defined in file \c frontend/pct.cpp,
 *   should be initialized properly.
 *
 *   - In case of a system error, the \c errno error number should be thrown
 *   - All exceptions must be of the type defined in this project (Exception)
 * \param [in] base Lowest PID value
 * \param [in] cnt Maximum number of processes
 */
void pctOpen(uint16_t base, uint16_t cnt);

// ================================================================================== //

/**
 * \brief Closes the module, setting the internal data structure to the closed state
 * \details
 *   - Any dinamically allocated variables should be freed
 *   - In the closed state, the supporting data structure has well-defined values.
 */
void pctClose();

// ================================================================================== //

/**
 * \brief Print the internal state of the PCT module
 * \details
 *  The current state of the process control table (PCT) must be
 *  printed to the given file stream.
 *
 *  The following must be considered:
 *  - The queue elements should be printed in ascending order of pid.
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] fout File stream where printing must be written to
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void pctPrint(FILE *fout, bool csv = false);

// ================================================================================== //

/**
 * \brief Create a new entry and add it to the PCT queue
 * \details
 *  A new entry should be created and added to the linked-list that implements
 *  the process control table.
 *
 *  The following must be considered:
 *  - a not used PID, in the range of valid values, should be selected;
 *  - constants \c PID_SMALLEST and \c PID_MAX_COUNT defines possible values for valid PIDs;
 *  - the value of \c lastPid should be used as the starting point to choose, in a circular way, the PID to be used
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception)
 *
 * \param [in] jid Id of the job the new process will be associated with
 * \return The PID of the newly created process
 */
uint16_t pctNew(uint32_t jid);

// ================================================================================== //

/**
 * \brief Get the value of a process data field
 * \details
 *   the \c value pointer is generic as \c field may be \c uint32_t or \c double.
 *   thus, a cast of the pointer to the proper type should be done.
 *
 * \param [in] pid id of the process
 * \param [in] field the field whose value is requested
 * \param [out] value pointer to the recipient where the requested value is to be stored
 */
void pctGet(uint16_t pid, PctField field, void *value);

// ================================================================================== //

/**
 * \brief Set the value of a process data field
 * \details
 *   the \c value pointer is generic as \c field may be \c uint32_t or \c double.
 *   thus, a cast of the pointer to the proper type should be done.
 *
 * \param [in] pid id of the process
 * \param [in] field the field whose value is requested
 * \param [in] value pointer to the recipient where the new value is stored
 */
void pctSet(uint16_t pid, PctField field, void *value);

// ================================================================================== //

/**
 * \brief Release resources used by given PID
 * \details
 *  Release memory used by process control block
 * \param [in] pid id of the process
 */
void pctDelete(uint16_t pid);

// ================================================================================== //

/** @} */

