//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphBellmanFord - Bellman-Ford Algorithm
//

// Student Name : Rodrigo Conroy Nunes
// Student Number : 119527
// Student Name :Paulo Lacerda 
// Student Number :120202

/*** COMPLETE THE GraphBellmanFordAlgExecute FUNCTION ***/

#include <time.h> // Para medir o tempo de execução
#include "GraphBellmanFordAlg.h"
#include "SortedList.h"
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#include "Graph.h"
#include "IntegersStack.h"
#include "instrumentation.h"

struct _GraphBellmanFordAlg {
  unsigned int* marked;  // To mark vertices when reached for the first time
  int* distance;  // The number of edges on the path from the start vertex
                  // distance[i]=-1, if no path found from the start vertex to i
  int* predecessor;  // The predecessor vertex in the shortest path
                     // predecessor[i]=-1, if no predecessor exists
  Graph* graph;
  unsigned int startVertex;  // The root of the shortest-paths tree
};

GraphBellmanFordAlg* GraphBellmanFordAlgExecute(Graph* g, unsigned int startVertex) {
    assert(g != NULL);
    assert(startVertex < GraphGetNumVertices(g));
    assert(GraphIsWeighted(g) == 0);

    unsigned int numVertices = GraphGetNumVertices(g);

    GraphBellmanFordAlg* result = (GraphBellmanFordAlg*)malloc(sizeof(struct _GraphBellmanFordAlg));
    if (!result) return NULL;

    result->graph = g;
    result->startVertex = startVertex;

    result->marked = (unsigned int*)malloc(numVertices * sizeof(unsigned int));
    result->distance = (int*)malloc(numVertices * sizeof(int));
    result->predecessor = (int*)malloc(numVertices * sizeof(int));
    if (!result->marked || !result->distance || !result->predecessor) {
        free(result->marked);
        free(result->distance);
        free(result->predecessor);
        free(result);
        return NULL;
    }

    for (unsigned int i = 0; i < numVertices; i++) {
        result->marked[i] = 0;
        result->distance[i] = (i == startVertex) ? 0 : -1;
        result->predecessor[i] = -1;
    }

    // Variáveis para medir a complexidade
    unsigned long edgeRelaxations = 0; // Contador de relaxamentos de arestas
    unsigned long vertexIterations = 0; // Contador de iterações sobre vértices

    // Iniciar a medição de tempo
    clock_t startTime = clock();

    // Implementação do algoritmo de Bellman-Ford
    for (unsigned int i = 1; i <= numVertices - 1; i++) {
       int updated = 0; // Flag para verificar se houve mudanças

        for (unsigned int u = 0; u < numVertices; u++) {
            vertexIterations++; // Contar cada iteração sobre um vértice

            unsigned int* adjacents = GraphGetAdjacentsTo(g, u);
            if (!adjacents) continue;

            unsigned int numAdjacents = adjacents[0];
            for (unsigned int j = 0; j < numAdjacents; j++) {
                edgeRelaxations++; // Contar cada relaxamento de aresta

                unsigned int v = adjacents[j + 1];
                double weight = 1.0; // Peso unitário

                if (result->distance[u] != -1 &&
                    (result->distance[v] == -1 || result->distance[u] + weight < result->distance[v])) {
                    result->distance[v] = result->distance[u] + weight;
                    result->predecessor[v] = u;
                    updated = 1; // Houve uma mudança no grafo
                }
            }

            free(adjacents);
        }

        if (!updated) {
            // Nenhuma atualização, podemos encerrar o algoritmo
            printf("Algoritmo encerrado antecipadamente após %u iterações.\n", i);
            break;
       }
    }

    // Parar a medição de tempo
    clock_t endTime = clock();

    // Calcular o tempo total em segundos
    double elapsedTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    // Exibir métricas de complexidade
    printf("Complexidade do Algoritmo Bellman-Ford:\n");
    printf("Total de iterações sobre vértices: %lu\n", vertexIterations);
    printf("Total de relaxamentos de arestas: %lu\n", edgeRelaxations);
    printf("Tempo de execução: %.5f segundos\n", elapsedTime);

    return result;
}

void GraphBellmanFordAlgDestroy(GraphBellmanFordAlg** p) {
  assert(*p != NULL);

  GraphBellmanFordAlg* aux = *p;

  free(aux->marked);
  free(aux->predecessor);
  free(aux->distance);

  free(*p);
  *p = NULL;
}

// Getting the paths information

int GraphBellmanFordAlgReached(const GraphBellmanFordAlg* p, unsigned int v) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));

  return p->marked[v];
}

int GraphBellmanFordAlgDistance(const GraphBellmanFordAlg* p, unsigned int v) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));

  return p->distance[v];
}
Stack* GraphBellmanFordAlgPathTo(const GraphBellmanFordAlg* p, unsigned int v) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));

  Stack* s = StackCreate(GraphGetNumVertices(p->graph));

  if (p->marked[v] == 0) {
    return s;
  }

  // Store the path
  for (unsigned int current = v; current != p->startVertex;
       current = p->predecessor[current]) {
    StackPush(s, current);
  }

  StackPush(s, p->startVertex);

  return s;
}

// DISPLAYING on the console

void GraphBellmanFordAlgShowPath(const GraphBellmanFordAlg* p, unsigned int v) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));

  Stack* s = GraphBellmanFordAlgPathTo(p, v);

  while (StackIsEmpty(s) == 0) {
    printf("%d ", StackPop(s));
  }

  StackDestroy(&s);
}

// Display the Shortest-Paths Tree in DOT format
void GraphBellmanFordAlgDisplayDOT(const GraphBellmanFordAlg* p) {
  assert(p != NULL);

  Graph* original_graph = p->graph;
  unsigned int num_vertices = GraphGetNumVertices(original_graph);

  // The paths tree is a digraph, with no edge weights
  Graph* paths_tree = GraphCreate(num_vertices, 1, 0);

  // Use the predecessors array to add the tree edges
  for (unsigned int w = 0; w < num_vertices; w++) {
    // Vertex w has a predecessor vertex v?
    int v = p->predecessor[w];
    if (v != -1) {
      GraphAddEdge(paths_tree, (unsigned int)v, w);
    }
  }

  // Display the tree in the DOT format
  GraphDisplayDOT(paths_tree);

  // Housekeeping
  GraphDestroy(&paths_tree);
}
