//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphEccentricityMeasures
//

// Student Name : Rodrigo Conroy Nunes
// Student Number : 119527
// Student Name : Paulo Lacerda
// Student Number :120202

/*** COMPLETE THE GraphEccentricityMeasuresCompute FUNCTION ***/
/*** COMPLETE THE GraphGetCentralVertices FUNCTION ***/
/*** COMPLETE THE GraphEccentricityMeasuresPrint FUNCTION ***/

#include "GraphEccentricityMeasures.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>


#include "Graph.h"
#include "GraphAllPairsShortestDistances.h"

struct _GraphEccentricityMeasures {
  unsigned int*
      centralVertices;  // centralVertices[0] = number of central vertices
                        // array size is (number of central vertices + 1)
  int* eccentricity;    
  Graph* graph;         
  int graphRadius;      
  int graphDiameter;    
};

// Allocate memory
// Compute the vertex eccentricity values
// Compute graph radius and graph diameter
// Compute the set of central vertices
GraphEccentricityMeasures* GraphEccentricityMeasuresCompute(Graph* g) {
  assert(g != NULL);

  unsigned int numVertices = GraphGetNumVertices(g);

  // Criar a estrutura
  GraphEccentricityMeasures* measures = 
      (GraphEccentricityMeasures*)malloc(sizeof(GraphEccentricityMeasures));
  if (measures == NULL) return NULL;

  measures->graph = g;
  measures->eccentricity = (int*)malloc(numVertices * sizeof(int));
  if (measures->eccentricity == NULL) {
    free(measures);
    return NULL;
  }

  // Obter as distâncias entre todos os pares
  GraphAllPairsShortestDistances* apsd = GraphAllPairsShortestDistancesExecute(g);
  if (apsd == NULL) {
    free(measures->eccentricity);
    free(measures);
    return NULL;
  }

  // Inicializar valores
  measures->graphRadius = INT_MAX;
  measures->graphDiameter = INT_MIN;

  // Calcular excentricidade de cada vértice
  for (unsigned int v = 0; v < numVertices; v++) {
    int maxDistance = INT_MIN;
    for (unsigned int w = 0; w < numVertices; w++) {
      int distance = GraphGetDistanceVW(apsd, v, w);
      if (distance != -1 && distance > maxDistance) {
        maxDistance = distance;
      }
    }

    measures->eccentricity[v] = maxDistance;
    if (maxDistance < measures->graphRadius) {
      measures->graphRadius = maxDistance;
    }
    if (maxDistance > measures->graphDiameter) {
      measures->graphDiameter = maxDistance;
    }
  }

  // Determinar os vértices centrais
  unsigned int centralCount = 0;
  for (unsigned int v = 0; v < numVertices; v++) {
    if (measures->eccentricity[v] == measures->graphRadius) {
      centralCount++;
    }
  }

  measures->centralVertices = (unsigned int*)malloc((centralCount + 1) * sizeof(unsigned int));
  if (measures->centralVertices == NULL) {
    free(measures->eccentricity);
    GraphAllPairsShortestDistancesDestroy(&apsd);
    free(measures);
    return NULL;
  }

  measures->centralVertices[0] = centralCount;
  unsigned int index = 1;
  for (unsigned int v = 0; v < numVertices; v++) {
    if (measures->eccentricity[v] == measures->graphRadius) {
      measures->centralVertices[index++] = v;
    }
  }

  GraphAllPairsShortestDistancesDestroy(&apsd);

  return measures;
}

void GraphEccentricityMeasuresDestroy(GraphEccentricityMeasures** p) {
  assert(*p != NULL);

  GraphEccentricityMeasures* aux = *p;

  free(aux->centralVertices);
  free(aux->eccentricity);
  // freeGraph(aux->graph); // Tirar o comentario caso seja dar free ao grafo

  free(*p);
  *p = NULL;
}

// Getting the computed measures

int GraphGetRadius(const GraphEccentricityMeasures* p) {
  assert(p != NULL);

  return p->graphRadius;
}

int GraphGetDiameter(const GraphEccentricityMeasures* p) {
  assert(p != NULL);

  return p->graphDiameter;
}

int GraphGetVertexEccentricity(const GraphEccentricityMeasures* p,
                               unsigned int v) {
  assert(p != NULL);
  assert(v < GraphGetNumVertices(p->graph));
  assert(p->eccentricity != NULL);

  return p->eccentricity[v];
}

// Getting a copy of the set of central vertices
// centralVertices[0] = number of central vertices in the set
unsigned int* GraphGetCentralVertices(const GraphEccentricityMeasures* p) {
  assert(p != NULL);
  assert(p->centralVertices != NULL);

  unsigned int count = p->centralVertices[0] + 1;
  unsigned int* centralVerticesCopy = (unsigned int*)malloc(count * sizeof(unsigned int));
  if (centralVerticesCopy == NULL) return NULL;

  for (unsigned int i = 0; i < count; i++) {
    centralVerticesCopy[i] = p->centralVertices[i];
  }

  return centralVerticesCopy;
}

// Print the graph radius and diameter
// Print the vertex eccentricity values
// Print the set of central vertices
void GraphEccentricityMeasuresPrint(const GraphEccentricityMeasures* p) {
  assert(p != NULL);

  printf("Graph Radius: %d\n", p->graphRadius);
  printf("Graph Diameter: %d\n", p->graphDiameter);

  printf("Vertex Eccentricities:\n");
  unsigned int numVertices = GraphGetNumVertices(p->graph);
  for (unsigned int v = 0; v < numVertices; v++) {
    printf("Vertex %u: %d\n", v, p->eccentricity[v]);
  }

  printf("Central Vertices (Radius = %d):\n", p->graphRadius);
  for (unsigned int i = 1; i <= p->centralVertices[0]; i++) {
    printf("%u ", p->centralVertices[i]);
  }
  printf("\n");
}

void freeGraphEccentricityMeasures(struct _GraphEccentricityMeasures* measures) {
    if (measures != NULL) {
        free(measures->centralVertices);
        free(measures->eccentricity);
        // Free do grafo
        freeGraph(measures->graph); 
        free(measures);
    }
}