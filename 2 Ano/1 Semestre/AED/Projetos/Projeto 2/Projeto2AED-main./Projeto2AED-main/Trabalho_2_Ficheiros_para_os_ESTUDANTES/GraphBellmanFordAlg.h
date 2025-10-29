//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphBellmanFord - Bellman-Ford Algorithm
//

#ifndef _GRAPH_BELLMAN_FORD_ALG_
#define _GRAPH_BELLMAN_FORD_ALG_

#include "Graph.h"
#include "IntegersStack.h"

typedef struct _GraphBellmanFordAlg GraphBellmanFordAlg;

GraphBellmanFordAlg* GraphBellmanFordAlgExecute(Graph* g,
                                                unsigned int startVertex);

void GraphBellmanFordAlgDestroy(GraphBellmanFordAlg** p);

// Getting the result

int GraphBellmanFordAlgReached(const GraphBellmanFordAlg* p, unsigned int v);

int GraphBellmanFordAlgDistance(const GraphBellmanFordAlg* p, unsigned int v);

Stack* GraphBellmanFordAlgPathTo(const GraphBellmanFordAlg* p, unsigned int v);

// DISPLAYING on the console

void GraphBellmanFordAlgShowPath(const GraphBellmanFordAlg* p, unsigned int v);

void GraphBellmanFordAlgDisplayDOT(const GraphBellmanFordAlg* p);

#endif  // _GRAPH_BELLMAN_FORD_ALG_
