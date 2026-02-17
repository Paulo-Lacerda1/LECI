class Relation:
    def __init__(self,e1,rel,e2):
        self.entity1 = e1
        self.name = rel
        self.entity2 = e2
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
    def __repr__(self):
        return str(self)

class Subtype(Relation):
    def __init__(self,sub,super):
        Relation.__init__(self,sub,"subtype",super)

class Member(Relation):
    def __init__(self,obj,type):
        Relation.__init__(self,obj,"member",type)

class AssocOne(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

class AssocSome(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

class Declaration:
    def __init__(self,user,rel):
        self.user = user
        self.relation = rel
    def __str__(self):
        return "decl("+str(self.user)+","+str(self.relation)+")"
    def __repr__(self):
        return str(self)

class SemanticNetwork:
    def __init__(self):
        self.declarations = []
        self.opposite = {}
        self.inverse = {}
    def __str__(self):
        return str(self.declarations)
    def insert(self,user,relation):
        self.declarations.append(Declaration(user,relation))
    def addInverse(self,assoc,inverse):
        self.inverse[assoc] = inverse
        self.inverse[inverse] = assoc
    def addOpposite(self,assoc,opposite):
        self.opposite[assoc] = opposite
        self.opposite[opposite] = assoc
    def query_local(self,user=None,e1=None,relname=None,e2=None):
        self.query_result = \
            [ d for d in self.declarations
                if  (user == None or d.user==user)
                and (e1 == None or d.relation.entity1 == e1)
                and (relname == None or d.relation.name == relname)
                and (e2 == None or d.relation.entity2 == e2) ]
        return self.query_result
    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

