#encoding: utf8
#Paulo Lacerda | 120202
from semanticnetwork import *
from constraintsearch import *
from bayes_net import *
from collections import Counter, deque


class MySN(SemanticNetwork):
    def __init__(self):
        SemanticNetwork.__init__(self)
        self.assoc_type_count = {}
        self._rebuild_assoc_type_count()

    def insert(self,user,relation):
        SemanticNetwork.insert(self,user,relation)
        self._register_assoc_type(relation)

    def _rebuild_assoc_type_count(self):
        self.assoc_type_count.clear()
        for d in self.declarations:
            self._register_assoc_type(d.relation)

    def _register_assoc_type(self, relation):
        if isinstance(relation, (AssocOne, AssocSome)):
            rel_counter = self.assoc_type_count.setdefault(relation.name, Counter())
            rel_counter[type(relation).__name__] += 1

    def new_query_local(self,e1,relname=None,e2=None):
        from collections import OrderedDict
        
        groups = OrderedDict()  # preserve declaration order per relation
        
        for d in self.declarations:
            if d.relation.entity1 == e1:
                if relname is None or d.relation.name == relname:
                    if e2 is None or d.relation.entity2 == e2:
                        rel_name = d.relation.name
                        if rel_name not in groups:
                            groups[rel_name] = []
                        if d.relation.entity2 not in groups[rel_name]:
                            groups[rel_name].append(d.relation.entity2)
            elif d.relation.entity2 == e1 and d.relation.name in self.inverse:
                inverse_rel = self.inverse[d.relation.name]
                if relname is None or inverse_rel == relname:
                    if e2 is None or d.relation.entity1 == e2:
                        if inverse_rel not in groups:
                            groups[inverse_rel] = []
                        if d.relation.entity1 not in groups[inverse_rel]:
                            groups[inverse_rel].append(d.relation.entity1)
        
        result = []
        for rel_name, entities in groups.items():
            for entity in entities:
                result.append((rel_name, entity))
        
        return result

    def new_query(self,entity,relname):
        predecessors = self._get_predecessors(entity)
        assoc_counts = self._get_assoc_counts(relname)  # pick most common assoc type
        assoc_some = assoc_counts.get('AssocSome', 0)
        assoc_one = assoc_counts.get('AssocOne', 0)
        
        if assoc_some >= assoc_one and assoc_some > 0:
            return self._query_assocsome(entity, relname, predecessors)
        elif assoc_one > 0:
            return self._query_assocone(entity, relname, predecessors)
        else:
            return []

    def _get_predecessors(self, entity):
        """Get all predecessors of an entity through Member and Subtype relations"""
        predecessors = []
        to_visit = [entity]
        visited = set()
        
        while to_visit:
            current = to_visit.pop(0)
            if current in visited:
                continue
            visited.add(current)
            
            for d in self.declarations:
                pred = None
                if isinstance(d.relation, Member) and d.relation.entity1 == current:
                    pred = d.relation.entity2
                elif isinstance(d.relation, Subtype) and d.relation.entity1 == current:
                    pred = d.relation.entity2
                
                if pred and pred not in visited:
                    predecessors.append(pred)
                    to_visit.append(pred)
        
        return predecessors
    
    def _get_assoc_counts(self, relname):
        assoc_counts = self.assoc_type_count.get(relname)
        if assoc_counts is not None:
            return assoc_counts
        assoc_counts = Counter()
        for d in self.declarations:
            rel = d.relation
            if rel.name == relname and isinstance(rel, (AssocOne, AssocSome)):
                assoc_counts[type(rel).__name__] += 1
        self.assoc_type_count[relname] = assoc_counts
        return assoc_counts

    def _query_assocsome(self, entity, relname, predecessors):
        """Query for AssocSome relations - inheritance without cancelling"""
        values = []
        
        opposite_relname = self.opposite.get(relname)
        opposite_values = set()
        
        if opposite_relname:
            for d in self.declarations:
                if d.relation.entity1 == entity and d.relation.name == opposite_relname:
                    if isinstance(d.relation, (AssocOne, AssocSome)):
                        opposite_values.add(d.relation.entity2)
        
        entities_to_check = [entity] + predecessors
        
        for ent in entities_to_check:
            for d in self.declarations:
                if d.relation.entity1 == ent and d.relation.name == relname:
                    if isinstance(d.relation, AssocSome):
                        value = d.relation.entity2
                        if value not in opposite_values and value not in values:
                            values.append(value)
        
        return sorted(values)

    def _query_assocone(self, entity, relname, predecessors):
        """Query for AssocOne relations - inheritance with cancelling"""
        local_values = []
        
        for d in self.declarations:
            if d.relation.entity1 == entity and d.relation.name == relname:
                if isinstance(d.relation, AssocOne):
                    local_values.append(d.relation.entity2)
        
        if local_values:
            counter = Counter(local_values)
            most_common = counter.most_common(1)[0][0]
            return [most_common]
        
        opposite_relname = self.opposite.get(relname)
        if opposite_relname:
            for d in self.declarations:
                if d.relation.entity1 == entity and d.relation.name == opposite_relname:
                    return []
        
        for pred in predecessors:
            pred_values = []
            for d in self.declarations:
                if d.relation.entity1 == pred and d.relation.name == relname:
                    if isinstance(d.relation, AssocOne):
                        pred_values.append(d.relation.entity2)
            
            if pred_values:
                counter = Counter(pred_values)
                most_common = counter.most_common(1)[0][0]
                return [most_common]
        
        return []



class MyCS(ConstraintSearch):

    def __init__(self,domains,constraints):
        ConstraintSearch.__init__(self,domains,constraints)
        pass

    def search_all(self,domains=None):
        self.calls += 1
        
        if domains is None:
            domains = self.domains
        
        if any(lv == [] for lv in domains.values()):
            return []
        
        if all(len(lv) == 1 for lv in domains.values()):
            return [{ v: lv[0] for (v, lv) in domains.items() }]
        
        var = min(
            (v for v in domains.keys() if len(domains[v]) > 1),
            key=lambda v: (len(domains[v]), v)
        )
        
        all_solutions = []
        for val in domains[var]:
            newdomains = dict(domains)
            newdomains[var] = [val]
            self.propagate(newdomains, var)
            solutions = self.search_all(newdomains)
            all_solutions.extend(solutions)
        
        return all_solutions


class MyBN(BayesNet):

    def __init__(self):
        BayesNet.__init__(self)
        pass

    def independence_bag(self,v1,v2):
        parents_map = {}
        for var in self.dependencies:
            parents_set = set()
            for mothers in self.dependencies[var].keys():
                for item in mothers:
                    if isinstance(item, tuple):
                        parents_set.add(item[0])
                    else:
                        parents_set.add(item)
            parents_map[var] = list(parents_set)
        
        common_ancestors = self._find_common_ancestors(v1, v2, parents_map)
        
        collected_v1 = self._collect_paths_to_common(v1, common_ancestors, parents_map)
        collected_v2 = self._collect_paths_to_common(v2, common_ancestors, parents_map)
        
        step1_nodes = collected_v1 | collected_v2
        
        final_bag = set(step1_nodes)
        for node in step1_nodes:
            final_bag.update(parents_map.get(node, []))
        
        return sorted(final_bag)

    def _get_all_ancestors(self, node, parents_map):
        """Get all ancestors of a node including the node itself"""
        ancestors = set()
        to_visit = deque([node])
        while to_visit:
            current = to_visit.popleft()
            if current in ancestors:
                continue
            ancestors.add(current)
            for parent in parents_map.get(current, []):
                to_visit.append(parent)
        return ancestors
    
    def _find_common_ancestors(self, v1, v2, parents_map):
        """Find common ancestors excluding v1 and v2 themselves unless one is ancestor of other"""
        ancestors_v1 = self._get_all_ancestors(v1, parents_map)
        ancestors_v2 = self._get_all_ancestors(v2, parents_map)
        common = ancestors_v1 & ancestors_v2
        
        if v1 in ancestors_v2 or v2 in ancestors_v1:
            return common
        
        common.discard(v1)
        common.discard(v2)
        return common
    
    def _can_reach_any(self, node, targets, parents_map, cache=None):
        """Check if node can reach any target through its ancestors with early exit"""
        if cache is None:
            cache = {}
        if node in cache:
            return cache[node]
        
        visited = set()
        to_visit = deque([node])
        
        while to_visit:
            current = to_visit.popleft()
            if current in visited:
                continue
            visited.add(current)
            
            if current in targets:
                cache[node] = True
                return True
            
            for parent in parents_map.get(current, []):
                to_visit.append(parent)
        
        cache[node] = False
        return False
    
    def _collect_paths_to_common(self, node, common_ancestors, parents_map):
        """Collect nodes ONLY on paths that lead to common ancestors"""
        if not common_ancestors:
            return {node}
        
        reach_cache = {}
        
        collected = set()
        to_visit = deque([node])
        visited = set()
        
        while to_visit:
            current = to_visit.popleft()
            if current in visited:
                continue
            visited.add(current)
            collected.add(current)
            
            if current in common_ancestors:
                continue
            
            for parent in parents_map.get(current, []):
                if self._can_reach_any(parent, common_ancestors, parents_map, reach_cache):
                    to_visit.append(parent)
        
        return collected
