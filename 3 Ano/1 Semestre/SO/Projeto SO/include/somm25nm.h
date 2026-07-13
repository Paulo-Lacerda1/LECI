#pragma once
/**
 *  \mainpage SO group work, academic year 2025-2026, normal season
 *
 *  \brief Implementing a multi-processor scheduler of a simulated multiprogrammed batch system, 
 *      based on an unequal-size fixed partitioning memory allocation approach
 *    
 *  \author Artur Pereira - 2025
 *
 *  \details
 *    The purpose of this project is to implement a simulation system that simulates the activity of a multi-processor scheduler,
 *    assuming main memory is divided into a number of static partitions at system generation time.
 *    In such a nowadays obsolete system, 
 *    the address space of a process may be loaded into any free single partition of memory whose size is equal or greater than the size required by the process.
 *
 *    \image html sched.png "Processor scheduler to be implemented" width=500
 *
 *    Every job (task to be executed by the system) is considered to be composed of a sequence of alternate CPU- and I/O-bursts, 
 *    represented by a sequence of real numbers (the durations of the bursts).
 *    So, a valid sequence contains an odd number of values.
 *
 * \anchor keyterms
 *
 *    The following terms are key for a good understanding of the system:
 *    - <b>job</b> - a task to be executed by the system;
 *    - <b>jid</b> - job indentification, 32-bits number, that uniquely represent a job 
 *    - <b>submission time</b> - the time at which a job arrives to the system (if accepted, a process is created);
 *    - <b>burst profile</b> - a sequence of an odd number of real numbers, 
 *          representing the sequence of CPU- and I/O-bursts that characterizes a job;
 *    - <b>process</b> - active entity representing a job being executed;
 *    - <b>pid</b> - process indentification, a positive, greater than zero, 16-bits value 
 *      that uniquely identifies a process;
 *    - <b>admission time</b> - the time at which a new process is admitted to the system;
 *    - <b>sleep time</b> - the time at which a process that owns a processor starts an I/O-burst, and so enters the blocked state;
 *    - <b>wakeup time</b> - the time at which a blocked process ends its I/O-burst;
 *    - <b>finish time</b> - the time at which a process that owns a processor ends its last CPU-burst, and so finishes execution;
 *    - <b>process state</b> - the state at which a process is at a given moment in time, being one of
 *      new (just created), 
 *      running (owning a processor),
 *      blocked (waiting for the completion of an I/O operation,
 *      ready-to-run (waiting for a processor),
 *      suspended-blocked (blocked and swapped-out),
 *      suspended-ready (ready but swapped-out),
 *      and terminated (waiting for resource realease)
 *
 *    The system is composed of the following main modules:
 *    - \c SIM, central module that manages the whole simulation
 *    - \c JOB, satellite module responsible for keeping information of the jobs driving the simulation
 *    - \c PCT, satellite module that holds information about the states of active processes
 *    - \c FEQ, satellite module that deals with the events that will command the simulation
 *    - \c RDY, satellite module that manages the queue of processes waiting for a processor
 *    - \c SWP, satellite module that manages the queue of processes swapped-out, including ready and blocked
 *    - \c MEM, satellite module that manages main memory
 *
 *    There is also a number of auxiliary modules:
 *    - \c probing, which provides a probing mechanism
 *    - \c binselection, which allows to use a binary version of a function
 *    - \c exception, which provides a common way to throw exceptions
 *    - \c DbC, which provides a set of asserting functions
 * 
 */

/**
 * \defgroup somm25nm Main modules
 *  \brief 
 *    modules to be developed
 *
 * \defgroup sim SIM - Simulation Management
 * \ingroup somm25nm
 * \brief 
 *   Central module that manages the whole simulation
 *
 * \defgroup job JOB - Job Descriptor Table
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing information of the jobs driving the simulation.
 *
 * \defgroup pct PCT - Process Control Table
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing information of the current processes.
 *
 * \defgroup feq FEQ - Future Event Queue
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing information of the events that will command the simulation.
 *
 * \defgroup rdy RDY Ready-to-Run Process Queue
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing information of the processes that are waiting for a processor to be available
 *
 * \defgroup swp SWP - Swapped-out Queue
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing information of the processes that are swapped-out
 *
 * \defgroup mem MEM - Memory Management
 * \ingroup somm25nm
 * \brief 
 *  Satellite module responsible for managing allocation and releasing of memory
 *
 * <br>
 *
 * \defgroup aux Auxiliary modules
 * \brief Modules to aid in the development
 *
 * \defgroup exception Exception 
 * \ingroup aux
 *
 * \defgroup probing Probing
 * \ingroup aux
 *
 * \defgroup binselection BinSelection
 * \ingroup aux
 *
 * \defgroup dbc DbC 
 * \ingroup aux
 *
 */

#include "dbc.h"
#include "exception.h"
#include "probing.h"
#include "binselection.h"

#include "sim.h"
#include "job.h"
#include "pct.h"
#include "feq.h"
#include "rdy.h"
#include "swp.h"
#include "mem.h"

