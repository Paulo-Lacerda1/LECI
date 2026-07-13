#STUDENT NAME: Paulo Miguel Inácio Lacerda
#STUDENT NUMBER: 120202

#DISCUSSED TPI-1 WITH: (names and numbers):

from tree_search import *
from strips import *
from blocksworld2 import *
from collections import defaultdict


class MyNode(SearchNode):

    def __init__(self, state, parent, domain):
        super().__init__(state, parent)
        # ADD HERE ANY CODE YOU NEED
        self.depth = 0 if parent is None else parent.depth + 1
        self.cost = 0 if parent is None else parent.cost + domain.cost(parent.state, (parent.state, state))
        self.heuristic = None   

class MyTree(SearchTree):

    def __init__(self, problem, strategy='breadth'):
        super().__init__(problem, strategy)
        # ADD HERE ANY CODE YOU NEED
        self.reset()

        root = MyNode(problem.initial, None, self.problem.domain)
        root.heuristic = self.problem.domain.heuristic(root.state, self.problem.goal)
        self.open_nodes = [root]
        # estratégia
        self.greedy_mode = False                    # modo A*
        self.root_heuristic = root.heuristic
        self.threshold = self.root_heuristic / 2    # limite para alternar para greedy

    def hybrid_add_to_open(self, lnewnodes):
        # IMPLEMENT HERE
        if not self.greedy_mode:
            for node in lnewnodes:
                if node.cost > self.threshold:  # custo ultrapassa threshold
                    self.greedy_mode = True     # ativa modo greedy
                    break
        self.open_nodes.extend(lnewnodes)
        # ordena por heurística (greedy) ou custo (A*)
        if self.greedy_mode:
            key_final = lambda n: (self.problem.domain.heuristic(n.state, self.problem.goal), n.depth, str(n.state))
        else:
            key_final = lambda n: (n.cost, n.depth, str(n.state))

        self.open_nodes.sort(key=key_final)

    def search2(self):
        # IMPLEMENT HERE
        self.reset()
        while self.open_nodes:
            node = self.open_nodes.pop(0)

            if self.problem.goal_test(node.state):  # é solucao
                self.solution = node
                self.solution_cost = node.cost
                self.terminal += 1
                break

            lnewnodes = []
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state, a)
                if newstate not in self.get_path(node):  # sem ciclos
                    child = MyNode(newstate, node, self.problem.domain)
                    child.heuristic = self.problem.domain.heuristic(newstate, self.problem.goal)
                    lnewnodes.append(child)
            # len(terminais ) e len (nao terminais )
            if self.problem.domain.actions(node.state):
                self.non_terminal += 1
                if lnewnodes:
                    self.add_to_open(lnewnodes)
            else:
                self.terminal += 1

        self.terminal += len(self.open_nodes)

        return self.get_path(self.solution) if self.solution else None

    def bipolar_search(self):
        self.reset()
        domain = self.problem.domain
        # cria nos inicial e final
        ni, nf = MyNode(self.problem.initial, None, domain), MyNode(self.problem.goal, None, domain)
    
        open_i, open_f = [ni], [nf]                          #nós abertos
        seen_i, seen_f = {ni.state: [ni]}, {nf.state: [nf]}  #nós visitados
    
        while open_i and open_f:
            for open_list, seen_self, seen_other, forward in [
                (open_i, seen_i, seen_f, True),             # sentido início-fim
                (open_f, seen_f, seen_i, False),            # sentido fim-início
            ]:      
                node = self._pop_node(open_list)
    
                if node.state in seen_other:  # encontrou interseção
                    return self._build_solution(node, seen_other[node.state], open_i, open_f, forward, seen_self, seen_other)
    
                self.non_terminal += 1
                # estados sucessores no dicionário
                for a in self.problem.domain.actions(node.state):
                    s = self.problem.domain.result(node.state, a)
                    if s not in seen_self:
                        seen_self[s] = []
    
                self._push_children(open_list, node.state, node, seen_self, open_i, open_f, forward)    # adiciona filhos
    
        self.terminal += len(open_i) + len(open_f)  # nós não expandidos
        return None
    

    #funcoes auxiliares

    def reset(self):
        self.terminal = self.non_terminal = self.solution_cost = 0
        self.solution = None

    def _is_blind(self):
        return self.strategy in ('breadth', 'depth')  # se nao tiver estratégia

    def _choose_best(self, nodes):  # escolhe melhor nó (profundidade ou custo)
        if self._is_blind():
            return min(nodes, key=lambda n: (n.depth, str(n.state)))
        return min(nodes, key=lambda n: (n.cost, n.depth, str(n.state)))

    def _pop_node(self, lst):
        return lst.pop() if self.strategy == 'depth' else lst.pop(0)  # LIFO para depth, FIFO para outros

    def _ordered_actions(self, domain, state):
        acts = domain.actions(state)
        acts.sort(key=lambda a: str(domain.result(state, a)))
        return acts

    def _push_children(self, lst, state, parent, seen, open_front, open_back, forward=True):
        domain = self.problem.domain
        acts = self._ordered_actions(domain, state)
        children = []
        for a in acts:
            ns = domain.result(state, a)
            if ns in self.get_path(parent):  #sem ciclos
                continue
            child = MyNode(ns, parent, domain)
            # calcular heurística baseada na direção
            if forward:
                child.heuristic = domain.heuristic(child.state, self.problem.goal)
            else:
                child.heuristic = domain.heuristic(child.state, self.problem.initial)
            children.append(child)
            seen[ns].append(child)
        if self.strategy == 'depth':
            for c in reversed(children):  # ordem reversa para depth
                lst.append(c)
        else:
            lst.extend(children)
            if self.strategy == 'hybrid':  # ordena por heurística para hybrid
                lst.sort(key=lambda n: (n.heuristic, n.depth, str(n.state)))

    def _build_solution(self, node, others, open_front, open_back, forward=True, seen_self=None, seen_other=None):
        other = self._choose_best(others)            # melhor nó da direcao contraria
        self.solution_cost = node.cost + other.cost  # custo total
        self.solution = node if forward else other

        pf = self.get_path(node if forward else other)
        pb = self.get_path(other if forward else node)
        pb.reverse()

        # contar o nó de interseção e nós restantes
    
        if self.strategy == 'hybrid' and seen_self and seen_other:
            # Para hybrid, contar todos os nós em seen
            total_seen_self = sum(len(nodes) for nodes in seen_self.values())
            total_seen_other = sum(len(nodes) for nodes in seen_other.values())
            nodes_nao_expandidos = (total_seen_self + total_seen_other) - self.non_terminal
            # Adicionar metade dos nós em open_front a ambos os contadores
            self.terminal += nodes_nao_expandidos + len(open_front) // 2
            self.non_terminal += len(open_front) // 2
        else:
            # Para depth e breadth, nós restantes são terminais
            self.terminal += 1
            self.terminal += len(open_front) + len(open_back)

        return pf + pb[1:]  # caminho final  

class MySTRIPS(STRIPS):

    def get_instanciations(self, op, state):
        # IMPLEMENT HERE
        state_set = state if isinstance(state, set) else set(state)
        
        # Indexar predicados por tipo
        pred_by_type = defaultdict(list)
        for pred in state_set:
            pred_by_type[type(pred)].append(pred)
        # Coletar candidatos
        var_values = defaultdict(set)
        for pc_pred in op.pc:
            pred_type = type(pc_pred)
            matching_preds = pred_by_type.get(pred_type, [])
            if not matching_preds:
                return []       
            for pred in matching_preds:
                if len(pred.args) == len(pc_pred.args):
                    for i, var in enumerate(pc_pred.args):
                        if var in op.args:
                            var_values[var].add(pred.args[i])
        
        # Obter constantes
        all_constants = list(state_constants(state_set))
        
        # Expandir domínio com constantes extra
        values_lists = []
        for v in op.args:
            if var_values[v]:
                candidates = list(var_values[v])
                extra_count = min(2, len(all_constants))
                for const in all_constants[:extra_count]:
                    if const not in candidates:
                        candidates.append(const)
                candidates.sort()
                values_lists.append(candidates)
            else:
                values_lists.append(sorted(all_constants))
        
        # Gerar e validar
        actions = []
        for values in product(*values_lists):
            assign = dict(zip(op.args, values))
            argvalues = [assign[a] for a in op.args]
            action = op.instanciate(argvalues)
            if all(c in state_set for c in action.pc):
                actions.append(action)
        return actions