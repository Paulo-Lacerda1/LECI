# To compile all programs, run:
#   make
#
# AED, ua, 2024

CFLAGS += -g -Wall -Wextra

TARGETS = TestAllPairsShortestDistances TestBellmanFordAlg \
 TestCreateTranspose TestEccentricityMeasures TestTransitiveClosure

all: $(TARGETS)

TestAllPairsShortestDistances: TestAllPairsShortestDistances.o Graph.o GraphBellmanFordAlg.o \
 GraphAllPairsShortestDistances.o IntegersStack.o SortedList.o instrumentation.o

TestCreateTranspose: TestCreateTranspose.o Graph.o SortedList.o instrumentation.o

TestBellmanFordAlg: TestBellmanFordAlg.o Graph.o GraphBellmanFordAlg.o \
 IntegersStack.o SortedList.o instrumentation.o

TestEccentricityMeasures: TestEccentricityMeasures.o Graph.o GraphBellmanFordAlg.o \
 GraphAllPairsShortestDistances.o GraphEccentricityMeasures.o IntegersStack.o \
 SortedList.o instrumentation.o

TestTransitiveClosure: TestTransitiveClosure.o Graph.o GraphBellmanFordAlg.o \
 GraphTransitiveClosure.o IntegersStack.o SortedList.o instrumentation.o

# Dependencies of source files

Graph.o: Graph.c Graph.h SortedList.h instrumentation.h

GraphAllPairsShortestDistances.o: GraphAllPairsShortestDistances.c \
 GraphAllPairsShortestDistances.h Graph.h \
 GraphBellmanFordAlg.h instrumentation.h

GraphBellmanFord.o: GraphBellmanFordAlg.c GraphBellmanFordAlg.h \
 Graph.h IntegersStack.h instrumentation.h

GraphEccentricityMeasures.o: GraphEccentricityMeasures.c GraphEccentricityMeasures.h Graph.h \
 GraphAllPairsShortestDistances.h instrumentation.h

GraphTransitiveClosure.o: GraphTransitiveClosure.c GraphTransitiveClosure.h Graph.h \
 GraphBellmanFordAlg.h instrumentation.h

IntegersStack.o: IntegersStack.c IntegersStack.h instrumentation.h

SortedList.o: SortedList.c SortedList.h instrumentation.h

instrumentation.o: instrumentation.c instrumentation.h

TestAllPairsShortestDistances.o: TestAllPairsShortestDistances.c Graph.h \
 GraphAllPairsShortestDistances.h GraphBellmanFordAlg.h instrumentation.h

TestBellamnFordAlg.o: TestBellmanFordAlg.c Graph.h GraphBellmanFordAlg.h \
 instrumentation.h

TestEccentricityMeasures.o: TestEccentricityMeasures.c Graph.h \
 GraphAllPairsShortestDistances.h GraphBellmanFordAlg.h GraphEccentricityMeasures.h instrumentation.h

TestCreateTranspose.o: TestCreateTranspose.c Graph.h instrumentation.h

TestTransitiveClosure.o: TestTransitiveClosure.c Graph.h GraphBellmanFordAlg.h \
 GraphTransitiveClosure.h instrumentation.h

clean:
	rm -f *.o
	rm -f $(TARGETS)


