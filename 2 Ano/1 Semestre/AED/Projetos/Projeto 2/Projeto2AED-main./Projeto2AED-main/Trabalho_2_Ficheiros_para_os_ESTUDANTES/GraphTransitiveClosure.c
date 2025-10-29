//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphTransitiveClosure - Transitive Closure of a directed graph
//
// Student Name :Rodrigo Conroy Nunes
// Student Number : 119527
// Student Name :Paulo Lacerda
// Student Number :120202
/*** COMPLETE THE GraphComputeTransitiveClosure FUNCTION ***/
#include "GraphTransitiveClosure.h"
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "GraphBellmanFordAlg.h"
#include "instrumentation.h"

// Compute the transitive closure of a directed graph
// Return the computed transitive closure as a directed graph
// Use the Bellman-Ford algorithm
Graph* GraphComputeTransitiveClosure(Graph* g) {
  assert(g != NULL);
  assert(GraphIsDigraph(g));
  assert(GraphIsWeighted(g) == 0);
  // Obtém o número de vértices do grafo
    int numVertices = GraphGetNumVertices(g);

    // Cria um novo grafo para armazenar o fecho transitivo
    Graph* transitiveClosure = GraphCreate(numVertices, 1,1); // Os dois "1" indica que é grafo

    // Para cada vértice do grafo
    for (int u = 0; u < numVertices; ++u) {
        // Bellman-Ford a partir do vértice u
        int* reachableVertices = GraphBellmanFord(g, u);

        // Adiciona arestas ao fecho transitivo com base nos vértices alcançáveis
        for (int v = 0; v < numVertices; ++v) {
            if (reachableVertices[v] != 0) { // Se v é alcançável a partir de u
                GraphAddEdge(transitiveClosure, u, v); // Adiciona a aresta
            }
        }
        // free da memoria
        free(reachableVertices);
    }
    return transitiveClosure; // Retorna o grafo de fecho transitivo
}
