//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira, Joao Manuel Rodrigues - Dec 2024
//
// Graph EXAMPLE : Creating and displaying graphs
//

#include <assert.h>

#include "Graph.h"

int main(void) {
  // What kind of graph is dig01?
  Graph* dig01 = GraphCreate(6, 1, 0);
  GraphAddEdge(dig01, 1, 2);
  GraphAddEdge(dig01, 1, 4);
  GraphAddEdge(dig01, 3, 4);
  printf("The graph:\n");
  // Displaying in DOT format
  GraphDisplayDOT(dig01);

  GraphCheckInvariants(dig01);

  // Create the transpose of dig01
  Graph* dig02 = GraphCreateTranspose(dig01);
  printf("The transpose graph:\n");
  // Displaying in DOT format
  GraphDisplayDOT(dig02);

  GraphCheckInvariants(dig02);

  // Reading a directed graph from file
  FILE* file = fopen("DG_2.txt", "r");
  Graph* dig03 = GraphFromFile(file);
  fclose(file);
  // Displaying in DOT format
  GraphDisplayDOT(dig03);

  GraphCheckInvariants(dig03);

  // Create the transpose of dig03
  Graph* dig04 = GraphCreateTranspose(dig03);
  printf("The transpose graph:\n");
  // Displaying in DOT format
  GraphDisplayDOT(dig04);

  GraphCheckInvariants(dig04);

  GraphDestroy(&dig01);
  GraphDestroy(&dig02);
  GraphDestroy(&dig03);
  GraphDestroy(&dig04);

  return 0;
}
