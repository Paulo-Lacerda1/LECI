//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira, Joao Manuel Rodrigues - Dec 2024
//
// Testing the Transitive Closure algorithm
//

#include <assert.h>

#include "Graph.h"
#include "GraphAllPairsShortestDistances.h"

int main(void) {
  // What kind of graph is dig01?
  Graph* dig01 = GraphCreate(6, 1, 0);
  GraphAddEdge(dig01, 1, 2);
  GraphAddEdge(dig01, 1, 4);
  GraphAddEdge(dig01, 3, 4);
  printf("The graph:\n");
  // Displaying in DOT format
  GraphDisplayDOT(dig01);
  printf("\n");

  GraphCheckInvariants(dig01);

  // Computing and printing the all-pairs shortest distances
  GraphAllPairsShortestDistances* distancesMatrix =
      GraphAllPairsShortestDistancesExecute(dig01);

  GraphAllPairsShortestDistancesPrint(distancesMatrix);

  GraphAllPairsShortestDistancesDestroy(&distancesMatrix);

  // Reading a directed graph from file
  FILE* file = fopen("DG_2.txt", "r");
  Graph* dig03 = GraphFromFile(file);
  fclose(file);
  // Displaying in DOT format
  GraphDisplayDOT(dig03);
  printf("\n");

  GraphCheckInvariants(dig03);

  // Computing and printing the all-pairs shortest distances
  distancesMatrix = GraphAllPairsShortestDistancesExecute(dig03);

  GraphAllPairsShortestDistancesPrint(distancesMatrix);

  GraphAllPairsShortestDistancesDestroy(&distancesMatrix);

  GraphDestroy(&dig01);
  GraphDestroy(&dig03);

  return 0;
}
