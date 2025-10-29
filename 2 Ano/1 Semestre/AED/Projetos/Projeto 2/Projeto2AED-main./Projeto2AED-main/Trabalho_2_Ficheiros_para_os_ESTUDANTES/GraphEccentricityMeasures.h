//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira - Dec 2024
//
// GraphEccentricityMeasures
//

#ifndef _GRAPH_ECCENTRICITY_MEASURES_
#define _GRAPH_ECCENTRICITY_MEASURES_

#include "Graph.h"

typedef struct _GraphEccentricityMeasures GraphEccentricityMeasures;

GraphEccentricityMeasures* GraphEccentricityMeasuresCompute(Graph* g);

void GraphEccentricityMeasuresDestroy(GraphEccentricityMeasures** p);

// Getting the computed measures

int GraphGetRadius(const GraphEccentricityMeasures* p);

int GraphGetDiameter(const GraphEccentricityMeasures* p);

int GraphGetVertexEccentricity(const GraphEccentricityMeasures* p,
                               unsigned int v);

// Getting a copy of the set of central vertices

unsigned int* GraphGetCentralVertices(const GraphEccentricityMeasures* p);

// Printing

void GraphEccentricityMeasuresPrint(const GraphEccentricityMeasures* p);

#endif  // _GRAPH_ECCENTRICITY_MEASURES_
