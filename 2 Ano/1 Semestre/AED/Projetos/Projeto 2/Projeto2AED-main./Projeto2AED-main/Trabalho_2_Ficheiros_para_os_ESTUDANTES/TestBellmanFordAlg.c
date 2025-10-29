//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira, Joao Manuel Rodrigues - Dec 2024
//
// Testing the Bellman-Ford algorithm
//

#include <assert.h>

#include "Graph.h"
#include "GraphBellmanFordAlg.h"

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

  // Bellman-Ford Algorithm

  // Consider each vertex as a start vertex
  for (unsigned int i = 0; i < 6; i++) {
    GraphBellmanFordAlg* BF_result = GraphBellmanFordAlgExecute(dig01, i);

    printf("The shortest path tree rooted at %u\n", i);
    GraphBellmanFordAlgDisplayDOT(BF_result);
    printf("\n");

    GraphBellmanFordAlgDestroy(&BF_result);
  }

  // What kind of graph is g01?
  Graph* g01 = GraphCreate(6, 0, 0);
  GraphAddEdge(g01, 0, 5);
  GraphAddEdge(g01, 2, 4);
  GraphAddEdge(g01, 2, 3);
  GraphAddEdge(g01, 1, 2);
  GraphAddEdge(g01, 0, 1);
  GraphAddEdge(g01, 3, 4);
  GraphAddEdge(g01, 3, 5);
  GraphAddEdge(g01, 0, 2);
  printf("The graph:\n");
  // Displaying in DOT format
  GraphDisplayDOT(g01);
  printf("\n");

  GraphCheckInvariants(g01);

  // Bellman-Ford Algorithm

  // Consider each vertex as a start vertex
  for (unsigned int i = 0; i < 6; i++) {
    GraphBellmanFordAlg* BF_result = GraphBellmanFordAlgExecute(g01, i);

    printf("The shortest path tree rooted at %u\n", i);
    GraphBellmanFordAlgDisplayDOT(BF_result);
    printf("\n");

    GraphBellmanFordAlgDestroy(&BF_result);
  }

  // Reading a directed graph from file
  FILE* file = fopen("DG_2.txt", "r");
  Graph* dig03 = GraphFromFile(file);
  fclose(file);
  // Displaying in DOT format
  GraphDisplayDOT(dig03);
  printf("\n");

  GraphCheckInvariants(dig03);

  // Bellman-Ford Algorithm

  // Consider each vertex as a start vertex
  for (unsigned int i = 0; i < GraphGetNumVertices(dig03); i++) {
    GraphBellmanFordAlg* BF_result = GraphBellmanFordAlgExecute(dig03, i);

    printf("The shortest path tree rooted at %u\n", i);
    GraphBellmanFordAlgDisplayDOT(BF_result);
    printf("\n");

    GraphBellmanFordAlgDestroy(&BF_result);
  }

  GraphDestroy(&g01);
  GraphDestroy(&dig01);
  GraphDestroy(&dig03);

  return 0;
}
