#pragma once
/**
 * \defgroup job JOB - Job Descriptor Table
 *
 * \details
 *  This modules is responsible for keeping information of the jobs driving the simulation.
 *
 *  \author Artur Pereira - 2025
 */

#include <stdint.h>
#include <stdio.h>

/** @{ */

// ================================================================================== //

#define JOB_MAX_CPU_BURSTS 5      ///< Maximum number of CPU-bursts in a job profile
#define JOB_MAX_BURSTS (2*JOB_MAX_CPU_BURSTS+1)      ///< Maximum number of bursts in a job profile

/**
 * \brief Node definition for the queue of jobs
 * \details
 *  The relevant data for a job is:
 *  - the job ID, a 8 hexadecimal digits number
 *  - the time of submission, a real number
 *  - the time of conclusion, a real number
 *  - the burst profile, an array of real numbers
 *  - an index, indicating the next burst to be processed
 */
struct JobNode
{
    uint32_t jid;                   ///< ID that uniquely identify a job
    double submissionTime;          ///< The time at which the job is submitted to the system
    double finishTime;              ///< The time at which the job ends its execution
    uint32_t memSize;               ///< Size of memory required by the job
    uint32_t nextBurstIndex;        ///< The index of the next burst to be processed
    double bursts[JOB_MAX_BURSTS];  ///< The job burst profile
    struct JobNode *next;           ///< Pointer to the next node
};

#define JOB_UNDEF_TIME 0xffffff2f   ///< Time pattern indicating a time in the context of this module is undefined

#define JOB_UNDEF_NODE ((JobNode*)0xffffff2e) ///< Pointer pattern indicating the JOB queue is closed (not the same as empty)

// ================================================================================== //

/**
 * \brief Key values to specify a field value to get/set
 */
enum JobField {
    JobSubmissionTime, ///< submition time
    JobFinishTime, ///< finish time
    JobMemSize, ///< memory size
    JobNextBurstIndex, ///< index of the next (CPU or IO) burst
    JobNextBurstDuration ///< duration of the next (CPU or IO) burst
};

// ================================================================================== //

/*
 * The supporting variables of the JOB module (NOT CHANGEABLE)
 */
extern JobNode *jobHead;       ///< Pointer to head of queue

// ================================================================================== //

/**
 * \brief Opens the module, initializing the internal data structure
 * \details
 *  The module's internal data structure, defined in file \c frontend/job.cpp,
 *  must be initialized properly.
 *
 */
void jobOpen();

// ================================================================================== //

/**
 * \brief Closes the module, setting the internal data structure to the closed state
 * \details
 *   - Any dinamically allocated variables should be freed
 *   - In the closed state, the supporting data structure has well-defined values.
 */
void jobClose();

// ================================================================================== //

/**
 * \brief Prints the internal state of the module
 * \details
 *  - The queue elements should be printed.
 *  - The format of the output depends on parameter \c csv:
 *    if it is \c true, CSV, using semi-colon as field separator, should be used;
 *    otherwise, a more human-readable format should be used.
 *  - In case of an error, an appropriate exception must be thrown.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] fout File stream where printing must be written to
 * \param [in] csv Flag indicating, if true, that output should be in CSV format
 */
void jobPrint(FILE *fout, bool csv = false);

// ================================================================================== //

/**
 * \brief Add a new job to the JOB queue
 * \details
 *  The following must be considered:
 *  - The queue must be kept sorted in ascending order of the job ID
 *  - In case of a system error, the \c errno error number should be thrown
 *  - If case of other erros, the EINVAL error number should be thrwon
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] jid ID of the new job
 * \param [in] submissionTime Time at which the job will arrive to the system
 * \param [in] memSize Amount of memory required by the job
 * \param [in] burstProfile Pointer to an array of JOB_MAX_BURSTS cells containing the profile.
 */
void jobInsert(uint32_t jid, double submissionTime, uint32_t memSize, double *burstProfile);

// ================================================================================== //

/**
 * \brief Get the value of a job data field
 * \details
 *  - The \c value pointer is a generic pointer and must be casted depending on the data \c field accessed.
 *  - In case of JobNextBurstDuration, the value returned may be:<br>
 *    -- a positive value, representing the duration of an IO-burst or a CPU-burst,
 *       except for the last one;<br>
 *    -- a negative value, whose absolute value represents the duration of the last CPU-burst;<br>
 *    -- 0, indicating there is no burst at the given index.
 *
 * \param [in] jid ID of the job
 * \param [in] field The field whose value is requested
 * \param [out] value Pointer to the recipient where the requested value is to be stored
 */
void jobGet(uint32_t jid, JobField field, void *value);

// ================================================================================== //

/**
 * \brief Set the value of a job data field
 * \details
 *  - Only fields \c finishTime and \c nextBurstIndex and directly settable;
 *    the other are set by the insert method.
 *  - Exception \c EACCES must be thown in case a not settable field is given.
 *  - The \c value pointer is a generic pointer and must be casted depending on the data \c field accessed.
 *  - All exceptions must be of the type defined in this project (Exception).
 *
 * \param [in] jid ID of the job
 * \param [in] field The field whose value is requested
 * \param [in] value Pointer to the recipient where the new value is stored
 */
void jobSet(uint32_t jid, JobField field, void *value);

// ================================================================================== //

/** @} */

