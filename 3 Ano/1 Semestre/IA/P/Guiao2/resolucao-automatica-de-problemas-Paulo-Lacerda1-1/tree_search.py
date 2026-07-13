
# Module: tree_search
# 
# This module provides a set o classes for automated
# problem solving through tree search:
#    SearchDomain  - problem domains
#    SearchProblem - concrete problems to be solved
#    SearchNode    - search tree nodes
#    SearchTree    - search tree with the necessary methods for searhing
#
#  (c) Luis Seabra Lopes
#  Introducao a Inteligencia Artificial, 2012-2020,
#  Inteligência Artificial, 2014-2023

from abc import ABC, abstractmethod

# Dominios de pesquisa
# Permitem calcular
# as accoes possiveis em cada estado, etc
class SearchDomain(ABC):

    # construtor
    @abstractmethod
    def __init__(self):
        pass

    # lista de accoes possiveis num estado
    @abstractmethod
    def actions(self, state):
        pass

    # resultado de uma accao num estado, ou seja, o estado seguinte
    @abstractmethod
    def result(self, state, action):
        pass

    # custo de uma accao num estado
    @abstractmethod
    def cost(self, state, action):
        pass

    # custo estimado de chegar de um estado a outro
    @abstractmethod
    def heuristic(self, state, goal):
        pass

    # test if the given "goal" is satisfied in "state"
    @abstractmethod
    def satisfies(self, state, goal):
        pass


# Problemas concretos a resolver
# dentro de um determinado dominio
class SearchProblem:
    def __init__(self, domain, initial, goal):
        self.domain = domain
        self.initial = initial
        self.goal = goal
    def goal_test(self, state):
        return self.domain.satisfies(state,self.goal)

# Nos de uma arvore de pesquisa
class SearchNode:
    def __init__(self,state,parent,cost=0,h=0):
        self.state = state
        self.parent = parent
        self.depth = 0 if parent == None else parent.depth + 1
        self.cost = cost  # custo acumulado desde a raiz
        self.h = h        # heurística (estimativa até o objetivo)

    @property
    def heuristic(self):
        # alias para compatibilidade com testes (esperam attribute 'heuristic')
        return self.h

    def __str__(self):
        return "no(" + str(self.state) + "," + str(self.parent) + ")"
    def __repr__(self):
        return str(self)

# Arvores de pesquisa
class SearchTree:

    # construtor
    def __init__(self,problem, strategy='breadth'): 
        self.problem = problem
        # cria nó raiz com custo 0 e heurística calculada pelo domínio
        root_h = problem.domain.heuristic(problem.initial, problem.goal)
        root = SearchNode(problem.initial, None, 0, root_h)
        self.open_nodes = [root]
        self.strategy = strategy
        self.solution = None
        self.length = None
        self.terminals = 0
        self.non_terminals = 0
        self.avg = None
        # rastrear nós gerados e expandidos para métricas
        self.generated_nodes = [root]
        self.expanded_nodes = set()
        self.highest_cost_nodes = []

    # obter o caminho (sequencia de estados) da raiz ate um no
    def get_path(self,node):
        if node.parent == None:
            return [node.state]
        path = self.get_path(node.parent)
        path += [node.state]
        return(path)

    # procurar a solucao
    def search(self, limit=None):
        while self.open_nodes != []:
            node = self.open_nodes.pop(0)
            # marcar nó como expandido
            self.expanded_nodes.add(node)
            if self.problem.goal_test(node.state):
                self.solution = node
                self.length = node.depth
                self.terminals = len(self.open_nodes) + 1 #nós que ficaram por expandir em open_nodes são folhas
                # após encontrar a solução, calcular nós folha e nós de maior custo
                leaves = [n for n in self.generated_nodes if n not in self.expanded_nodes]
                # ordenar por custo desc e guardar os top 5
                self.highest_cost_nodes = sorted(leaves, key=lambda n: n.cost, reverse=True)[:5]
                return self.get_path(node)
            self.non_terminals += 1           #nao é solução, logo está a ser expandido
            lnewnodes = []
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state,a)
                if newstate not in self.get_path(node):
                    new_cost = node.cost + self.problem.domain.cost(node.state, a)
                    new_h = self.problem.domain.heuristic(newstate, self.problem.goal)
                    newnode = SearchNode(newstate, node, new_cost, new_h)
                    if limit is None or newnode.depth <= limit:
                        lnewnodes.append(newnode)
                        # registar nó gerado
                        self.generated_nodes.append(newnode)
            self.add_to_open(lnewnodes)
        return None
    
    @property
    def avg_branching(self):
        # O número de nós filhos é o total de nós gerados menos 1 (a raiz)
        # O número de nós pais é o número de nós expandidos (non_terminals)
        if self.non_terminals == 0:
            return 0
        total_nodes = self.non_terminals + self.terminals  #no final da busca
        filhos = total_nodes - 1 #menos a raiz
        pais = self.non_terminals  
        return filhos / pais if pais > 0 else 0
    
    @property
    def cost(self):
        if self.solution is not None:               #se ela for encontrada
            return self.solution.cost
        return None

    @property
    def average_depth(self):
        # média das profundidades de todos os nós gerados
        if not hasattr(self, 'generated_nodes') or len(self.generated_nodes) == 0:
            return 0
        total = sum(n.depth for n in self.generated_nodes)
        return total / len(self.generated_nodes)

    # juntar novos nos a lista de nos abertos de acordo com a estrategia
    def add_to_open(self,lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[:0] = lnewnodes
        elif self.strategy == 'uniform':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.cost)
        elif self.strategy == 'greedy':
            # ordena por heurística (menor h primeiro)
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.h)
        elif self.strategy == 'astar' or self.strategy == 'a*':
            # ordena por f = g + h
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.cost + node.h)

