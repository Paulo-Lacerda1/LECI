//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphAllPairsShortestDistances
//

#ifndef _GRAPH_ALL_PAIRS_SHORTEST_DISTANCES_
#define _GRAPH_ALL_PAIRS_SHORTEST_DISTANCES_

#include "Graph.h"

typedef struct _GraphAllPairsShortestDistances GraphAllPairsShortestDistances;

GraphAllPairsShortestDistances* GraphAllPairsShortestDistancesExecute(Graph* g);

void GraphAllPairsShortestDistancesDestroy(GraphAllPairsShortestDistances** p);

// Getting the result

int GraphGetDistanceVW(const GraphAllPairsShortestDistances* p, unsigned int v,
                       unsigned int w);

// DISPLAYING on the console

void GraphAllPairsShortestDistancesPrint(
    const GraphAllPairsShortestDistances* p);

#endif  // _GRAPH_ALL_PAIRS_SHORTEST_DISTANCES_
