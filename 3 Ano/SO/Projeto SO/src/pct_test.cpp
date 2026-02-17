/*
 * Expanded Test Suite for PCT Module
 * Includes: Basic IO, Circular Allocation, Edge Cases, and Exception Handling
 */

#include "pct.h"
#include "exception.h"

// Include appropriate binary selection header
#if defined(__has_include)
#if __has_include("somm25.h")
#include "somm25.h"
#else
#include "somm25nm.h"
#endif
#else
#include "somm25nm.h"
#endif

#include <cstdio>
#include <cstring>
#include <unistd.h>
#include <iostream>

// Utility to print section headers
void printHeader(const char* title) {
    printf("\n%s\n", "============================================================");
    printf(" TEST: %s\n", title);
    printf("%s\n", "============================================================");
}

// --------------------------------------------------------------------
// Test 1: The original test case (matches prof.txt logic)
// --------------------------------------------------------------------
void testOriginalScenario() {
    printHeader("Original Scenario (prof.txt reproduction)");
    
    int value = 12;
    int *pointer = &value;

    printf("--> Opening PCT (Base=1, Count=1000)\n");
    pctOpen(1, 1000);
    pctPrint(stdout, true); // Header only
    
    printf("--> Creating New Process (JID=123456)\n");
    pctNew(123456);
    pctPrint(stdout, true); 
    
    printf("--> Getting JID for PID 1\n");
    pctGet(1, PctJid, pointer);
    pctPrint(stdout, true);
    printf("--> Deleting PID 1\n");
    pctDelete(1);
    pctPrint(stdout, true);
    printf("--> Deleting PID 1\n");

    //printf("--> Setting State for PID 1 to INVALID (12)\n");
    // // Note: This relies on the print function handling invalid enums gracefully
    // pctSet(1, PctState, pointer);
    // pctPrint(stdout, true);
    
    pctClose();
}

// --------------------------------------------------------------------
// Test 2: Data Integrity (Set/Get all fields)
// --------------------------------------------------------------------
// void testDataIntegrity() {
//     printHeader("Data Integrity (Set/Get)");
//     pctOpen(10, 5); // Base 10

//     uint16_t pid = pctNew(999);
//     printf("Created PID %u\n", pid);

//     // Set valid state and memory
//     PctProcessState st = BLOCKED;
//     uint32_t mem = 0xDEADBEEF;

//     pctSet(pid, PctState, &st);
//     pctSet(pid, PctMemAddr, &mem);

//     // Read back
//     PctProcessState readSt;
//     uint32_t readMem;
//     uint32_t readJid;

//     pctGet(pid, PctState, &readSt);
//     pctGet(pid, PctMemAddr, &readMem);
//     pctGet(pid, PctJid, &readJid);

//     printf("Check JID (Expect 999): %u ... %s\n", readJid, (readJid == 999) ? "OK" : "FAIL");
//     printf("Check State (Expect BLOCKED): %d ... %s\n", readSt, (readSt == BLOCKED) ? "OK" : "FAIL");
//     printf("Check Mem (Expect 0xDEADBEEF): 0x%X ... %s\n", readMem, (readMem == 0xDEADBEEF) ? "OK" : "FAIL");

//     pctClose();
// }

// --------------------------------------------------------------------
// Test 3: Circular Allocation & Deletion
// --------------------------------------------------------------------
void testCircularBuffer() {
    printHeader("Circular Allocation & Logic");
    
    // Open small table: Base=100, Count=3
    // Valid PIDs: 100, 101, 102
    pctOpen(100, 3); 

    printf("1. Filling table...\n");
    pctNew(10); // PID 100
    pctNew(20); // PID 101
    pctNew(30); // PID 102
    pctPrint(stdout, false); // Print table format

    printf("2. Attempting Overflow (should throw Exception)...\n");
    try {
        pctNew(40);
        printf(" [FAIL] pctNew should have thrown exception\n");
    } catch(Exception &e) {
        printf(" [PASS] Caught expected exception: Process table full.\n");
    }

    printf("3. Deleting Middle PID (101)...\n");
    pctDelete(101);
    pctPrint(stdout, false);

    printf("4. Creating New Process (should reuse PID 101)...\n");
    // Search starts from LastPid (102) + 1 -> index 0 (100 occupied) -> index 1 (101 Free)
    uint16_t newPid = pctNew(99); 
    printf("Assigned PID: %u\n", newPid);
    
    if (newPid == 101) printf(" [PASS] Circular allocation worked.\n");
    else printf(" [FAIL] Expected PID 101, got %u\n", newPid);

    pctPrint(stdout, false);
    pctClose();
}

// --------------------------------------------------------------------
// Test 4: Invalid Access Handling
// --------------------------------------------------------------------
void testErrorHandling() {
    printHeader("Error Handling (Invalid PIDs)");
    pctOpen(500, 10);
    //uint16_t pid = pctNew(1); // PID 500

    printf("1. Accessing PID out of range (550)...\n");
    try {
        uint32_t val;
        pctGet(550, PctJid, &val);
        printf(" [FAIL] Should throw exception\n");
    } catch (Exception &e) {
        printf(" [PASS] Caught out-of-bounds exception.\n");
    }

    printf("2. Accessing valid PID that is NOT active (501)...\n");
    try {
        uint32_t val;
        pctGet(501, PctJid, &val); // 501 is in range [500, 509] but null
        printf(" [FAIL] Should throw exception\n");
    } catch (Exception &e) {
        printf(" [PASS] Caught null-entry exception.\n");
    }

    printf("3. Deleting non-existent PID (501)...\n");
    try {
        pctDelete(501);
        printf(" [FAIL] Should throw exception\n");
    } catch (Exception &e) {
        printf(" [PASS] Caught deletion exception.\n");
    }

    pctClose();
}

// --------------------------------------------------------------------
// Main
// --------------------------------------------------------------------
int main(int argc, char *argv[])
{
    int opt;
    while ((opt = getopt(argc, argv, "bga:r:h")) != -1)
    {
        switch (opt)
        {
            case 'b':
                soBinSetIDs(0, 999);
                break;
            case 'g':
                soBinSetIDs(0, 0);
                break;
            case 'a':
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ((sscanf(optarg, "%u%*[,-]%u %n", &lower, &upper, &cnt) != 2) ||
                    (cnt != strlen(optarg)))
                {
                    return 1;
                }
                soBinAddIDs(lower, upper);
                break;
            }
            case 'r':
            {
                uint32_t lower, upper;
                uint32_t cnt = 0;
                if ((sscanf(optarg, "%u%*[,-]%u %n", &lower, &upper, &cnt) != 2) ||
                    (cnt != strlen(optarg)))
                {
                    return 1;
                }
                soBinRemoveIDs(lower, upper);
                break;
            }
            case 'h':
            default:
                return 0;
        }
    }


    try {
        // Execute all tests
        testOriginalScenario();
        //testDataIntegrity();
        //testCircularBuffer();
        //testErrorHandling();
    } catch (Exception &e) {
        // Catch-all for unexpected errors in the test flow itself
        printf("!! FATAL UNCAUGHT EXCEPTION !!\n");
    }

    return 0;
}