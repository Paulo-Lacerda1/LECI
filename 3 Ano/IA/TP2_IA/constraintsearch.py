
class ConstraintSearch:

    def __init__(self,domains,constraints):
        self.domains = domains
        self.constraints = constraints
        self.calls = 0

    def search(self,domains=None):

        self.calls += 1
        
        if domains==None:
            domains = self.domains

        if any(lv==[] for lv in domains.values()):
            return None

        if all(len(lv)==1 for lv in list(domains.values())):
            return { v:lv[0] for (v,lv) in domains.items() }
       
        for var in domains.keys():
            if len(domains[var])>1:
                for val in domains[var]:
                    newdomains = dict(domains)
                    newdomains[var] = [val]
                    self.propagate(newdomains,var)
                    solution = self.search(newdomains)
                    if solution != None:
                        return solution
        return None

    def propagate(self,domains,var):
        edges = [ e for e in self.constraints if e[1]==var ]
        while edges!=[]:
            (vj,vi) = edges.pop()
            constraint = self.constraints[vj,vi]
            newdomain = [ xj for xj in domains[vj] 
                             if any(constraint(vj,xj,vi,xi) for xi in domains[vi] ) ]
            if len(newdomain)<len(domains[vj]):
                domains[vj] = newdomain
                edges += [ e for e in self.constraints if e[1]==vj ]


