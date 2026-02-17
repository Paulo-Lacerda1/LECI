#pragma once
/**
 * \defgroup sim SIM - Simulation Management
 *
 * \details
 *  This modules is responsible for managing the whole simulation.
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>
#include <cmath>

#include "job.h"
#include "pct.h"
#include "feq.h"
#include "rdy.h"
#include "swp.h"
#include "mem.h"

/** @{ */

// ================================================================================== //

/**
 * \brief Global simulation parameters
 * \details
 *   Some satellite modules have configuration data that have to be given when the
 *   modules are opened.
 *   This data structure holds all the relevant data for that. It includes:
 *   - The swapping policy, for the SWP module.
 *   - The scheduling policy, for the RDY module.
 *   - Data required to partition the memory.
 *     This is given by an initial address plus the number of blocks of every possible size.
 *     A smallest block size is given as a log 2 value.
 *     The second smallest block size is twice the smallest, and so on.
 *     An array is used to specify the number of blocks of every size from the smallest to the biggest.
 */
struct SimParameters
{
    uint32_t processorCount; ///< Number of processors
    uint16_t basePid; ///< Lowest value for the PID of a process
    uint16_t maxPids; ///< Maximum number of PIDs
    SwpSwappingPolicy swappingPolicy; ///< The swapping policy
    RdySchedulingPolicy schedulingPolicy; ///< The scheduling policy
    uint32_t memInitAddr; ///< Start address of memory for normal processes
    uint32_t memMinLogSize; ///< Log 2 size of the smallest memory block
    uint32_t memSizesCount; ///< Number of cells of the memSizes' array
    uint32_t *memSizes; ///< Pointer to array containing the number of blocks for every size from smallest to biggest
};

// ================================================================================== //

/**
 * \brief Processor state
 * \details
 *   The system has several processors, numbered from 0 to \c simProcessorCount.
 *   A processor may be idle (not running any process) or in use (running a process).
 *   When in use, the processor state represents the PID of the process being run in it.
 *   Idle processors are kept in a linked-list.
 *   In such situation, the state of a processor represents a sucessor idle processor,
 *   or \c simProcessorCount if it is the last one.
 *   The list is kept sorted in chronological order of release.
 */
struct SimProcessorState {
    bool idle; ///< Flag indicating if the processor is idle
    union {
        uint16_t pid; ///< PID of the process running in this processor, if in use
        uint16_t next; ///< next idle processor, if idle
    };
};

/**
 * \brief Flags indicating satellite modules to be printed
 */
#define SimPrintNone 0x0                ///< Print no satellite module (the default)
#define SimPrintJob 0x1                 ///< Print JOB module
#define SimPrintPct 0x2                 ///< Print PCT module
#define SimPrintFeq 0x4                 ///< Print FEQ module
#define SimPrintRdy 0x8                 ///< Print RDY module
#define SimPrintSwp 0x10                ///< Print SWP module
#define SimPrintMemGlobal 0x20          ///< Print MEM module in global node
#define SimPrintMemFreeOnly 0x40        ///< Print MEM free list only
#define SimPrintMemOccupiedOnly 0x80    ///< Print MEM occupied list only
#define SimPrintAll 0x3f                ///< Print all satellite modules, with MEM in global mode

// ================================================================================== //

extern double simTime;  ///< The current simulation time

extern uint32_t simProcessorCount; ///< Actual number of processors
extern SimProcessorState *simProcessorState; ///< Pointer to the array of processor states

extern uint16_t simIdleHead; ///< First idle processor to be used
extern uint16_t simIdleTail; ///< Last idle processor to be used

// ================================================================================== //

#define SIM_UNDEF_TIME (-INFINITY)   ///< Time pattern indicating simulation time is undefined

#define SIM_UNDEF_POINTER ((SimProcessorState*)0xffffff1f) ///< Pattern indicating an invalid pointer, in the context of this module

#define SIM_UNDEF_INDEX 0xff1e   ///< Pattern indicating an index is undefined

// ================================================================================== //

/**
 * \brief Open this module and the satellite modules
 * \details
 *  The module's internal data structure, defined in file \c frontend/sim.cpp,
 *  should be initialized properly, based on field \c processorCount of the
 *  parameters given.
 *  Additionally, the satellite modules should be opened taken into considerations the other fields.
 *
 * \param [in] param Pointer to the structure containing data to initialize this module and open the satellite modules
 */
void simOpen(SimParameters *param);

// ================================================================================== //

/**
 * \brief Close this module and, if asked for, close before the satellite modules
 * \details
 *   After calling, if asked for, the close functions of the satellite modules,
 *   the supporting data structure of this module must be put in the close state.
 *   - In the closed state, the supporting data structure has well-defined values.
 *
 * \param [in] closeSatelliteModules Flag indicating if satellite modules should be close too
 */
void simClose(bool closeSatelliteModules = false);

// ================================================================================== //

/**
 * \brief Print the current state of this module and, if asked for, print the states of the satellite modules
 * \details
 *  - The current state of the internal data structure of this module should be printed to the given file stream
 *  - Additionally, if asked for, the printing functions of the satellite modules should be called.
 *
 *  The following must be considered:
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] fout File stream where to send printing
 * \param [in] which bitwise OR of flags indicating which satellite modules should be printed
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void simPrint(FILE *fout, uint32_t which = SimPrintNone, bool csv = false);

// ================================================================================== //

/**
 * \brief Fills both the JOB and FEQ queues based on job info parsed from a file
 * \details
 *   - Syntactically, an input file stream is a sequence of jobs
 *     each one represented by a sequence of consecutive lines
 *   - ...
 *   - Each line is composed of a semicolon-separated sequence of the following fields:
 *     - the ID of a job, in the form of 8 hexadecimal digits;
 *     - the time of its submission to the system;
 *     - the burst profile, in the form of a comma-sepatared sequence of burst durations;
 *   - Lines starting with a % are comments and are to be ignored
 *   - white lines are also to be ignored
 *
 *  The following must be checked while parsing the input file:
 *  - job IDs should be different
 *  - every submission time must be a non-negative real number;
 *  - submission times should appear in ascending order
 *  - every burst profile must have an odd numbers of positive real numbers
 *  - a profile should have at most \c JOC_NBURSTS bursts
 *
 *  For every valid line in the input file stream,
 *  a job must be added to the JOB queue and a corresponding ADMISSION event should be added to the FEQ queue.
 *
 *  In case of an error:
 *  - if it is a system error, the \c errno error number should be thrown
 *  - if it is a syntax or semantic error,
 *    an appropriate error message should be printed to the <i>standard error</i>
 *    and the \c EINVAL error number should be thrown
 *  All exceptions must be of the type defined in this project (Exception)
 *
 * \param [in] fin Pointer to the input file stream
 * \param [in] maxMemSize Size of the biggest memory block available in the system
 */
void simLoadBatch(FILE *fin, uint32_t maxMemSize);

// ================================================================================== //

/**
 * \brief Run the simulation for a given number of steps or til the end
 * \details
 *  This function just call the \c simStep a number of times.
 *
 *  The following must be considered:
 *  - The simulation can reach the end in less than the given number of steps.
 *  - If the given number of steps is zero, the simulation must run til the end.
 *
 * \param [in] cnt Number of simulation steps to execute
 * \param [in] blocking Flag indicating the blocking mode
 */
void simRun(uint32_t cnt, bool blocking = false);

// ================================================================================== //

/**
 * \brief Process, if possible, a step of the simulation
 * \details
 *  This function is responsible for:
 *  - retrieveing an event from the FEQ
 *  - advane the simulation time to the timestamp of the event
 *  - delegate the processing of the event in the proper auxiliary function
 *  - return \c false if the FEQ queue is empty, and \c true otherwise
 *
 * \param [in] blocking Flag indicating the blocking mode
 * \return \c true if one step was processed; \c false otherwise
 */
bool simStep(bool blocking = false);

// ================================================================================== //

/**
 * \brief Process an event of type SUBMIT
 * \details
 *  This auxiliary function of the step method must:
 *   - Try to create a new process to execute the job
 *   - Reject the job, if no process is available
 *   - Schedule an ADMIT event, if the process was cretaed
 * \param [in] jid ID of the job that generated the event
 */
void simStepSubmit(uint32_t jid);

// ================================================================================== //

/**
 * \brief Process an event of type ADMIT
 * \details
 *  This auxiliary function of the step method must:
 *  - Try to allocate a memory block to host the address space of the job
 *  - If succeed, the process should be inserted into the RDY queue
 *  - If not, the process should be inserted into the SWP queue
 *  - Update process
 *  - If the process is inserted into the RDY queue and there are processors idle,
 *    schedule a DISPATCH event
 * \param [in] pid ID of the process that generated the event
 */
void simStepAdmit(uint16_t pid);

// ================================================================================== //

/**
 * \brief Process an event of type DISPATCH
 * \details
 *  This auxiliary function of the step method must:
 *  - Retrieve a process from the RDY queue, doing nothing if the queue is empty
 *  - Retrieve the ID of the oldest idle processor/core from the list of idle processors/cores
 *  - Update processor/core state
 *  - Update process data
 *  - Get the duration of the next CPU burst and advance index to the next burst
 *  - If it is the last CPU-burst, schedule an EXIT event
 *  - otherwise, schedule a WAIT_EVENT event
 */
void simStepDispatch();

// ================================================================================== //

/**
 * \brief Process an event of type WAIT_EVENT
 * \details
 *  This auxiliary function of the step method must:
 *  - Add processor to the idle list
 *  - Update the process state
 *  - Get the duration of the next IO burst and advance index to the next burst
 *  - Schedule an EVENT_OCCURS event
 *  - Schedule a DISPATCH event
 * \param [in] cid ID of the processor/core associated to the event
 */
void simStepWaitEvent(uint16_t cid);

// ================================================================================== //

/**
 * \brief Process an event of type EXIT
 * \details
 *  This auxiliary function of the step method must:
 *  - Add processor to the idle list
 *  - Update the process state
 *  - Release memory used by process
 *  - Update job data
 *  - Schedule an ACTIVATE event
 *  - Schedule a DISPATCH event
 * \param [in] cid ID of the processor/core associated to the event
 */
void simStepExit(uint16_t cid);

// ================================================================================== //

/**
 * \brief Process an event of type EVENT_OCCURS
 * \details
 *  This auxiliary function of the step method must:
 *  - If process state is S_BLOCKED, just unblock it
 *  - If it is READY, add process to the RDY queue
 *  - Otherwise, throw error number EPERM (meaning 'disallowed by current rules/state')
 *  - Update the process state
 *  - If applicable, schedule a DISPATCH event
 *  - All exceptions must be of the type defined in this project (Exception).
 * \param [in] pid ID of the process associated to the event
 */
void simStepEventOccurs(uint16_t pid);

// ================================================================================== //

/**
 * \brief
 * \details
 *  This auxiliary function of the step method must try to swap-in a swapped-out process,
 *  prioritizing a non-blocked process over a blocked one.
 *  A swap-in operation means:
 *  - Retrieve a process from the SWP queue
 *  - Allocate memory to the process
 *  - If applicable, add process to RDY queue
 *  - If applicable, schedule a DISPATCH event
 *  - Update process data
 */
void simStepActivate();

// ================================================================================== //

/**
 * \brief
 * \details
 *  This auxiliary function of the step method must:
 *  - delete process from list of processes
 * \param [in] pid ID of the process associated to the event
 */
void simStepDelete(uint16_t pid);

// ================================================================================== //

/**
 * \brief
 * \details
 *  This auxiliary function of the step method must:
 *  - swap-out process
 * \param [in] pid ID of the process associated to the event
 */
void simStepSuspend(uint16_t pid);

// ================================================================================== //

/**
 * \brief
 * \details
 *  This auxiliary function of the step method must:
 * \param [in] cid ID of the processor/core associated to the event
 */
void simStepPreempt(uint16_t cid);

// ================================================================================== //

/**
 * \brief Launches a thread that concurrentely feeds the JOB and FEQ queues
 * \details
 *  Details on the funcionality of this function will only be given after the remaining
 *  funcionality of the project is working properly.
 */
void simJobLauncher(uint32_t n, uint32_t seed);

// ================================================================================== //

/** @} */

