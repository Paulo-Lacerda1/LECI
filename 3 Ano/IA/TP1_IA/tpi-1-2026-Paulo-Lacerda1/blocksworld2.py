#
#  Module: blocksworld2
# 
#  Based on the imported "strips" module, the "blocksworld2" module
#  defines a set of predicates and operators for representing
#  a variant of the "Blocks World" planning domain, where
#  specific blocks must be handled with different hands/tools
#


from strips import *

# Blocks world predicates (version with hands/tools)

class Floor(Predicate):
    def __init__(self,block):
        self.args = [block]

class On(Predicate):
    def __init__(self,b1,b2):
        self.args = [b1,b2]

class Free(Predicate):
    def __init__(self,block):
        self.args = [block]

class Holds(Predicate):
    def __init__(self,hand,block):
        self.args = [hand,block]

class HandFree(Predicate):   # attached hand has no block
    def __init__(self,hand):
        self.args = [hand]

class HandleWith(Predicate): # block must be handled by the specified hand
    def __init__(self,block,hand):
        self.args = [block,hand]

class NoHand(Predicate):  # robotic arm has no hand attached
    def __init__(self):
        self.args = []

class Available(Predicate): # the specified hand is available
    def __init__(self,hand):
        self.args = [hand]

# Blocks world operators

X='X'
Y='Y'
Z='Z'
H='H'

class Attach(Operator):  # attach a hand (tool) to the robotic arm
    args = [H]
    pc   = [NoHand(),Available(H)] # arm has no hand attached, 
    neg  = [NoHand(),Available(H)] # and there is a hand available
    pos  = [HandFree(H)]  # hand is attached and free

class Detach(Operator):  # detach the hand
    args = [H]
    pc   = [HandFree(H)]
    neg  = [HandFree(H)]
    pos  = [NoHand(),Available(H)]

class Stack(Operator):
    args = [H,X,Y]
    pc   = [Holds(H,X),Free(Y)]
    neg  = [Holds(H,X),Free(Y)]
    pos  = [On(X,Y),HandFree(H),Free(X)]

class Unstack(Operator):
    args = [H,X,Y]
    pc   = [On(X,Y),HandFree(H),Free(X),HandleWith(X,H)]
    neg  = [On(X,Y),HandFree(H),Free(X)]
    pos  = [Holds(H,X),Free(Y)]

class Putdown(Operator):
    args = [H,X]
    pc   = [Holds(H,X)]
    neg  = [Holds(H,X)]
    pos  = [Floor(X),HandFree(H),Free(X)]
 
class Pickup(Operator):
    args = [H,X]
    pc   = [Floor(X),HandFree(H),Free(X),HandleWith(X,H)]
    neg  = [Floor(X),HandFree(H),Free(X)]
    pos  = [Holds(H,X)]


