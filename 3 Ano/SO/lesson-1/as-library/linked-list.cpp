#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <stdint.h>
#include <string.h>
#include <assert.h>

#include "linked-list.h"

/*******************************************************/

SllNode* sllDestroy(SllNode* list)
{
    return list;
}

/*******************************************************/

void sllPrint(SllNode *list, FILE *fout)
{
}

/*******************************************************/

SllNode* sllInsert(SllNode* list, uint32_t nmec, const char *name)
{
    assert(name != NULL && name[0] != '\0');
    assert(!sllExists(list, nmec));

    return list;
}

/*******************************************************/

bool sllExists(SllNode* list, uint32_t nmec)
{
    return false;
}

/*******************************************************/

SllNode* sllRemove(SllNode* list, uint32_t nmec)
{
    assert(list != NULL);
    assert(sllExists(list, nmec));

    return list;
}

/*******************************************************/

const char *sllGetName(SllNode* list, uint32_t nmec)
{
    assert(list != NULL);
    assert(sllExists(list, nmec));

    return NULL;
}

/*******************************************************/

SllNode* sllLoad(SllNode *list, FILE *fin, bool *ok)
{
    assert(fin != NULL);

    if (ok != NULL)
       *ok = false; // load failure

    return NULL;
}

/*******************************************************/

