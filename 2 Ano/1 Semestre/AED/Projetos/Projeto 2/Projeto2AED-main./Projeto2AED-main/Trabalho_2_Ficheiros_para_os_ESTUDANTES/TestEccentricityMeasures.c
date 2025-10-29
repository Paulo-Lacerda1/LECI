//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira, Joao Manuel Rodrigues - Dec 2024
//
// Computing some Eccentricity Measures
//

#include <assert.h>

#include "Graph.h"
#include "GraphEccentricityMeasures.h"

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

  // Computing and printing eccentricity measures
  GraphEccentricityMeasures* eccentricityMeasures =
      GraphEccentricityMeasuresCompute(dig01);

  GraphEccentricityMeasuresPrint(eccentricityMeasures);

  GraphEccentricityMeasuresDestroy(&eccentricityMeasures);

  // Reading a directed graph from file
  FILE* file = fopen("DG_2.txt", "r");
  Graph* dig03 = GraphFromFile(file);
  fclose(file);
  // Displaying in DOT format
  GraphDisplayDOT(dig03);
  printf("\n");

  GraphCheckInvariants(dig03);

  // Computing and printing eccentricity measures
  eccentricityMeasures = GraphEccentricityMeasuresCompute(dig03);

  GraphEccentricityMeasuresPrint(eccentricityMeasures);

  GraphEccentricityMeasuresDestroy(&eccentricityMeasures);

  GraphDestroy(&dig01);
  GraphDestroy(&dig03);

  return 0;
}
