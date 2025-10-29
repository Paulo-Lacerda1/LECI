//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphAllPairsShortestDistances
//

// Student Name : Rodrigo Conroy Nunes
// Student Number : 119527
// Student Name :Paulo Lacerda
// Student Number :120202

/*** COMPLETE THE GraphAllPairsShortestDistancesExecute FUNCTION ***/


#include <time.h> // Para medir o tempo de execução
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#include "Sortedlist.h"
#include "Graph.h"
#include "GraphBellmanFordAlg.h"
#include "GraphAllPairsShortestDistances.h"

struct _GraphAllPairsShortestDistances {
  int** distance;  // The 2D matrix storing the all-pairs shortest distances
                   // It is stored as an array of pointers to 1D rows
                   // Idea: an INDEFINITE distance value is stored as -1
  Graph* graph;
};

// Allocate memory and initialize the distance matrix
GraphAllPairsShortestDistances* GraphAllPairsShortestDistancesExecute(Graph* g) {
    assert(g != NULL);

    unsigned int numVertices = GraphGetNumVertices(g);

    // Alocar a estrutura para armazenar as distâncias
    GraphAllPairsShortestDistances* result = (GraphAllPairsShortestDistances*)malloc(sizeof(GraphAllPairsShortestDistances));
    if (result == NULL) return NULL;

    // Associar o grafo
    result->graph = g;

    // Alocar a matriz de distâncias
    result->distance = (int**)malloc(numVertices * sizeof(int*));
    if (result->distance == NULL) {
        free(result);
        return NULL;
    }

    for (unsigned int i = 0; i < numVertices; i++) {
        result->distance[i] = (int*)malloc(numVertices * sizeof(int));
        if (result->distance[i] == NULL) {
            // Liberação em caso de falha de alocação
            for (unsigned int j = 0; j < i; j++) {
                free(result->distance[j]);
            }
            free(result->distance);
            free(result);
            return NULL;
        }
    }

    // Inicializar a matriz de distâncias com valores "indefinidos" (-1)
    for (unsigned int i = 0; i < numVertices; i++) {
        for (unsigned int j = 0; j < numVertices; j++) {
            result->distance[i][j] = -1; // Nenhum caminho conhecido inicialmente
        }
    }

    // Iniciar a medição de tempo
    clock_t startTime = clock();

    // Calcular as distâncias usando Bellman-Ford para cada vértice como origem
    for (unsigned int i = 0; i < numVertices; i++) {
        // Executar Bellman-Ford a partir do vértice `i`
        GraphBellmanFordAlg* bfResult = GraphBellmanFordAlgExecute(g, i);
        if (bfResult == NULL) {
            // Liberação em caso de falha na execução do Bellman-Ford
            for (unsigned int j = 0; j < numVertices; j++) {
                free(result->distance[j]);
            }
            free(result->distance);
            free(result);
            return NULL;
        }

        // Preencher a linha `i` da matriz de distâncias
        for (unsigned int j = 0; j < numVertices; j++) {
            result->distance[i][j] = GraphBellmanFordAlgDistance(bfResult, j);
        }

        // Destruir o resultado intermediário do Bellman-Ford
        GraphBellmanFordAlgDestroy(&bfResult);
    }

    // Parar a medição de tempo
    clock_t endTime = clock();

    // Calcular o tempo total em segundos
    double elapsedTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    // Exibir o tempo de execução
    printf("Tempo de execução de GraphAllPairsShortestDistancesExecute: %.4f segundos\n", elapsedTime);

    return result;
}

void GraphAllPairsShortestDistancesDestroy(GraphAllPairsShortestDistances** p) {
  assert(*p != NULL);

  GraphAllPairsShortestDistances* aux = *p;
  unsigned int numVertices = GraphGetNumVertices(aux->graph);

  for (unsigned int i = 0; i < numVertices; i++) {
    free(aux->distance[i]);
  }

  free(aux->distance);

  free(*p);
  *p = NULL;
}

// Getting the result

int GraphGetDistanceVW(const GraphAllPairsShortestDistances* p, unsigned int v,
                       unsigned int w) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));
  assert(w < GraphGetNumVertices(p->graph));

  return p->distance[v][w];
}

// DISPLAYING on the console

void GraphAllPairsShortestDistancesPrint(
    const GraphAllPairsShortestDistances* p) {
  assert(p != NULL);

  unsigned int numVertices = GraphGetNumVertices(p->graph);
  printf("Graph distance matrix - %u vertices\n", numVertices);

  for (unsigned int i = 0; i < numVertices; i++) {
    for (unsigned int j = 0; j < numVertices; j++) {
      int distanceIJ = p->distance[i][j];
      if (distanceIJ == -1) {
        // INFINITY - j was not reached from i
        printf(" INF");
      } else {
        printf(" %3d", distanceIJ);
      }
    }
    printf("\n");
  }
}
