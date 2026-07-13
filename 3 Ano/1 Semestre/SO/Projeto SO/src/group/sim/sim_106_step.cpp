/*
 *  \author Adriana
 */

#include "somm25nm.h"

namespace group
{
    bool simStep(bool blocking)
    {
        /* TODO POINT: Replace next instruction with your code */
        (void)blocking; 

        double time;
        FeqEventType type;
        uint32_t xid;

        // obter proximo evento da FEQ
        bool hasEvent = feqRetrieve(&time, &type, &xid, false);
        if (!hasEvent) {
            // se a feq esta vazia entao a simulacao terminou
            return false;
        }

        // avancar o tempo de simulacao
        simTime = time;

        // despachar pelo tipo de evento
        switch (type) {
            case SUBMIT:
                simStepSubmit(xid);
                break;

            case ADMIT:
                simStepAdmit((uint16_t)xid);
                break;

            case DISPATCH:
                simStepDispatch();
                break;

            case TIMEOUT:
                simStepPreempt((uint16_t)xid);
                break;

            case PREEMPT:
                simStepPreempt((uint16_t)xid);
                break;

            case WAIT_EVENT:
                simStepWaitEvent((uint16_t)xid);
                break;

            case EVENT_OCCURS:
                simStepEventOccurs((uint16_t)xid);
                break;

            case SUSPEND:
                simStepSuspend((uint16_t)xid);
                break;

            case ACTIVATE:
                simStepActivate();
                break;

            case EXIT:
                simStepExit((uint16_t)xid);
                break;

            case DELETE:
                simStepDelete((uint16_t)xid);
                break;

            default:
                throw Exception(EPERM, __func__);
        }
        
        return true;
    }
} // end of namespace group

