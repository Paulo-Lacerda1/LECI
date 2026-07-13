/*
 *  \author Artur Pereira (artur at ua dot pt)
 */

#include "job.h"
#include "binselection.h"
#include "probing.h"
#include "dbc.h"

#include <stdio.h>
#include <stdint.h>

// ================================================================================== //

/*
 * This set of supporting variables CAN NOT BE CHANGED
 */
JobNode *jobHead = JOB_UNDEF_NODE;       // Pointer to head of queue

// ================================================================================== //

namespace binary {
    void jobOpen();
    void jobClose();
    void jobPrint(FILE *fout, bool csv = false);
    void jobInsert(uint32_t jid, double submissionTime, uint32_t memSize, double *burstProfile);
    void jobGet(uint32_t jid, JobField field, void *value);
    void jobSet(uint32_t jid, JobField field, void *value);
}

namespace group {
    void jobOpen();
    void jobClose();
    void jobPrint(FILE *fout, bool csv = false);
    void jobInsert(uint32_t jid, double submissionTime, uint32_t memSize, double *burstProfile);
    void jobGet(uint32_t jid, JobField field, void *value);
    void jobSet(uint32_t jid, JobField field, void *value);
}

// ================================================================================== //

void jobOpen()
{
    require(jobHead == JOB_UNDEF_NODE, "Module must be closed");

    if (soBinSelected(201))
    {
        soProbe(SOPROBE_RED, 201, "%s()\n", __func__);
        binary::jobOpen();
    }
    else
    {
        soProbe(201, "%s()\n", __func__);
        group::jobOpen();
    }
}

// ================================================================================== //

void jobClose()
{
    require(jobHead != JOB_UNDEF_NODE, "Module must be open");

    if (soBinSelected(202))
    {
        soProbe(SOPROBE_RED, 202, "%s()\n", __func__);
        binary::jobClose();
    }
    else
    {
        soProbe(202, "%s()\n", __func__);
        group::jobClose();
    }
}

// ================================================================================== //

void jobPrint(FILE *fout, bool csv)
{
    require(jobHead != JOB_UNDEF_NODE, "Module must be open");
    require(fout != nullptr and fileno(fout) != -1, "fout must be a valid file stream");

    const char *cas = csv ? "true" : "false";
    if (soBinSelected(203))
    {
        soProbe(SOPROBE_RED, 203, "%s(%d,%s)\n", __func__, fileno(fout), cas);
        binary::jobPrint(fout, csv);
    }
    else
    {
        soProbe(203, "%s(%d,%s)\n", __func__, fileno(fout), cas);
        group::jobPrint(fout, csv);
    }
}

// ================================================================================== //

void jobInsert(uint32_t jid, double submissionTime, uint32_t memSize, double *burstProfile)
{
    require(jobHead != JOB_UNDEF_NODE, "Module must be open");
    require(submissionTime >= 0, "Invalid submissionTime");
    require(memSize > 0, "Invalid memSize");
    require(burstProfile != nullptr, "burstProfile must be a valid pointer");

    if (soBinSelected(204))
    {
        soProbe(SOPROBE_RED, 204, "%s(0x%08x,%.1f,%#x,%p)\n", __func__, jid, submissionTime, memSize, burstProfile);
        binary::jobInsert(jid, submissionTime, memSize, burstProfile);
    }
    else
    {
        soProbe(204, "%s(0x%08x,%.1f,%#x,%p)\n", __func__, jid, submissionTime, memSize, burstProfile);
        group::jobInsert(jid, submissionTime, memSize, burstProfile);
    }
}

// ================================================================================== //

void jobGet(uint32_t jid, JobField field, void *value)
{
    require(jobHead != JOB_UNDEF_NODE, "Module must be open");
    require(field >= JobSubmissionTime and field <= JobNextBurstDuration, "Invalid field");
    require(value != nullptr, "Invalid pointer");

    const char *fas = "Invalid field";
    switch (field)
    {
        case JobSubmissionTime: fas = "JobSubmissionTime"; break;
        case JobFinishTime: fas = "JobFinishTime"; break;
        case JobMemSize: fas = "JobMemSize"; break;
        case JobNextBurstIndex: fas = "JobNextBurstIndex"; break;
        case JobNextBurstDuration: fas = "JobNextBurstDuration"; break;
    }

    if (soBinSelected(205))
    {
        soProbe(SOPROBE_RED, 205, "%s(0x%08x,%s,%p)\n", __func__, jid, fas, value);
        binary::jobGet(jid, field, value);
    }
    else
    {
        soProbe(205, "%s(0x%08x,%s,%p)\n", __func__, jid, fas, value);
        group::jobGet(jid, field, value);
    }
}

// ================================================================================== //

void jobSet(uint32_t jid, JobField field, void *value)
{
    require(jobHead != JOB_UNDEF_NODE, "Module must be open");
    require(field >= JobSubmissionTime and field <= JobNextBurstDuration, "Invalid field");
    require(value != nullptr, "Invalid pointer");

    const char *fas = "Invalid field";
    switch (field)
    {
        case JobSubmissionTime: fas = "JobSubmissionTime"; break;
        case JobFinishTime: fas = "JobFinishTime"; break;
        case JobMemSize: fas = "JobMemSize"; break;
        case JobNextBurstIndex: fas = "JobNextBurstIndex"; break;
        case JobNextBurstDuration: fas = "JobNextBurstDuration"; break;
    }

    if (soBinSelected(206))
    {
        soProbe(SOPROBE_RED, 206, "%s(0x%08x,%s,%p)\n", __func__, jid, fas, value);
        binary::jobSet(jid, field, value);
    }
    else
    {
        soProbe(206, "%s(0x%08x,%s,%p)\n", __func__, jid, fas, value);
        group::jobSet(jid, field, value);
    }
}

// ================================================================================== //

