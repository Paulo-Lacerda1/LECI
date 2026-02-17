#encoding: utf8

import time

from tpi2 import *

# -------------------------------------------------------------
# Semantic Networks
# -- example adapted from the practical classes
# -------------------------------------------------------------


z = MySN()

z.insert('descartes',Subtype('mammal','vertebrate'))
z.insert('darwin',Subtype('mammal','vertebrate'))
z.insert('darwin',AssocSome('mammal','likes','milk'))

z.insert('descartes',Subtype('man','mammal'))
z.insert('darwin',Subtype('man','mammal'))
z.insert('darwin',AssocSome('man','likes','meat'))
z.insert('bacon',AssocOne('man','likes','vegetables'))

z.insert('bacon',AssocSome('philosopher','likes','philosophy'))

z.insert('descartes',Member('socrates','man'))
z.insert('damasio',Member('socrates','philosopher'))
z.insert('descartes',AssocSome('socrates','professorOf','philosophy'))
z.insert('descartes',AssocSome('socrates','professorOf','mathematics'))
z.insert('simao',AssocSome('socrates','professorOf','mathematics'))
z.insert('simao',AssocOne('socrates','hasFather','sophroniscus'))
z.insert('nunes',AssocOne('socrates','hasFather','sophroniscus'))
z.insert('aristotle',AssocOne('socrates','hasFather','plato'))
z.insert('simao',AssocOne('socrates','hasMother','phaenarete'))
z.insert('socrates',AssocSome('socrates','likes','sophroniscus'))
z.insert('sophroniscus',AssocSome('socrates','likes','phaenarete'))
z.insert('bacon',AssocSome('socrates','likes','mathematics'))
z.insert('bacon',AssocSome('socrates','dislikes','meat'))


z.insert('descartes',Member('plato','man'))
z.insert('descartes',AssocSome('plato','professorOf','philosophy'))
z.insert('simao',AssocSome('plato','professorOf','philosophy'))
z.insert('simao',AssocSome('aristotle','hasFather','ariston'))

z.insert('descartes',Member('aristotle','man'))
z.insert('simao',AssocOne('aristotle','hasFather','nicomachus'))

z.addInverse('hasFather','fatherOf')
z.addOpposite('likes','dislikes')


print('\nz.new_query_local(aristotle,e2=nicomachus):')
print(z.new_query_local('aristotle',e2='nicomachus'))

print('\nz.new_query_local(aristotle):')
print(z.new_query_local('aristotle'))

print('\nz.new_query_local(plato):')
print(z.new_query_local('plato'))

print('\nz.new_query(socrates,likes):')
print(z.new_query('socrates','likes'))

print('\nz.new_query(socrates,hasFather):')
print(z.new_query('socrates','hasFather'))

print()

# -------------------------------------------------------------
# Constraint Search
# -- The TWO + TWO = FOUR puzzle
# -------------------------------------------------------------


variables = ['F','O','R','T','U','W']

digits = list(range(0,10))

domains = { v:digits for v in variables if v!='F' }
domains['F']  = [0,1]
domains['X1'] = [0,1]
domains['X2'] = [0,1]


domains['ORX1'] = [(0, 0, 0), (1, 2, 0), (2, 4, 0), (3, 6, 0), (4, 8, 0), (5, 0, 1), (6, 2, 1), (7, 4, 1), (8, 6, 1), (9, 8, 1)]

domains['WX1UX2'] = [(0, 0, 0, 0), (0, 1, 1, 0), (1, 0, 2, 0), (1, 1, 3, 0), (2, 0, 4, 0), (2, 1, 5, 0), (3, 0, 6, 0), (3, 1, 7, 0), (4, 0, 8, 0), (4, 1, 9, 0), (5, 0, 0, 1), (5, 1, 1, 1), (6, 0, 2, 1), (6, 1, 3, 1), (7, 0, 4, 1), (7, 1, 5, 1), (8, 0, 6, 1), (8, 1, 7, 1), (9, 0, 8, 1), (9, 1, 9, 1)]

domains['TX2OF'] = [(0, 0, 0, 0), (0, 1, 1, 0), (1, 0, 2, 0), (1, 1, 3, 0), (2, 0, 4, 0), (2, 1, 5, 0), (3, 0, 6, 0), (3, 1, 7, 0), (4, 0, 8, 0), (4, 1, 9, 0), (5, 0, 0, 1), (5, 1, 1, 1), (6, 0, 2, 1), (6, 1, 3, 1), (7, 0, 4, 1), (7, 1, 5, 1), (8, 0, 6, 1), (8, 1, 7, 1), (9, 0, 8, 1), (9, 1, 9, 1)]


constraints = [((u,w),(lambda v1,x1,v2,x2:x1!=x2)) for u in variables for w in variables if u!=w]

constraints += [ (edge,lambda var,val,auxvar,auxval : val==auxval[0]) 
    for edge in [('O','ORX1'),('W','WX1UX2'),('T','TX2OF')] ]
constraints += [ (edge,lambda auxvar,auxval,var,val : val==auxval[0]) 
    for edge in [('ORX1','O'),('WX1UX2','W'),('TX2OF','T')] ]

constraints += [ (edge,lambda var,val,auxvar,auxval : val==auxval[1]) 
    for edge in [('R','ORX1'),('X1','WX1UX2'),('X2','TX2OF')] ]
constraints += [ (edge,lambda auxvar,auxval,var,val : val==auxval[1]) 
    for edge in [('ORX1','R'),('WX1UX2','X1'),('TX2OF','X2')] ]

constraints += [ (edge,lambda var,val,auxvar,auxval : val==auxval[2]) 
    for edge in [('X1','ORX1'),('U','WX1UX2'),('O','TX2OF')] ]
constraints += [ (edge,lambda auxvar,auxval,var,val : val==auxval[2]) 
    for edge in [('ORX1','X1'),('WX1UX2','U'),('TX2OF','O')] ]

constraints += [ (edge,lambda var,val,auxvar,auxval : val==auxval[3]) 
    for edge in [('X2','WX1UX2'),('F','TX2OF')] ]
constraints += [ (edge,lambda auxvar,auxval,var,val : val==auxval[3]) 
    for edge in [('WX1UX2','X2'),('TX2OF','F')] ]

cs = MyCS(domains,dict(constraints))

print("--------------------")
print("Search one solution ")
print("--------------------")
t0 = time.process_time()
sol = cs.search() 
print("Solucao:",sol)
print(cs.calls,"calls; tempo =",time.process_time()-t0)


print("--------------------")
print("Search all solutions")
print("--------------------")
t0 = time.process_time()
lsols = cs.search_all()
print('Solutions:')
for s in lsols:
    print([(v,s[v]) for v in ['F','O','R','T','U','W']])
print(cs.calls,'calls; time =',time.process_time()-t0)
print(len(lsols),'solutions')

print()

# ----------------------------------------------------------------------
# Bayesian Networks
# -- Car diagnosis example
# ----------------------------------------------------------------------

bn = MyBN()

bn.add('a',[],0.003)

bn.add('b_a',[],0.002)

bn.add('c_s',[('a',True )],0.48)
bn.add('c_s',[('a',False)],0.08)

bn.add('d',[],0.01)

bn.add('m_f',[],0.01)

bn.add('b_v',[('c_s',True ),('b_a',True )],0.18)
bn.add('b_v',[('c_s',True ),('b_a',False)],0.02)
bn.add('b_v',[('c_s',False),('b_a',True )],0.90)
bn.add('b_v',[('c_s',False),('b_a',False)],0.68)

bn.add('s_m',[],0.05)

bn.add('s_p',[],0.3)

bn.add('v_p',[('m_f',True),('d',True ),('b_v',True )],0.003)
bn.add('v_p',[('m_f',True),('d',True ),('b_v',False )],0.12)
bn.add('v_p',[('m_f',True),('d',False ),('b_v',True)],0.08)
bn.add('v_p',[('m_f',True),('d',False),('b_v',False )],0.01)
bn.add('v_p',[('m_f',False),('d',True),('b_v',True)],0.04)
bn.add('v_p',[('m_f',False),('d',True ),('b_v',False)],0.07)
bn.add('v_p',[('m_f',False),('d',False),('b_v',True )],0.13)
bn.add('v_p',[('m_f',False),('d',False),('b_v',False)],0.09)

bn.add('h',[('b_v',True )],0.44)
bn.add('h',[('b_v',False)],0.89)

bn.add('s_s',[('s_m',True),('m_f',True ),('b_v',True )],0.3)
bn.add('s_s',[('s_m',True),('m_f',True ),('b_v',False )],0.21)
bn.add('s_s',[('s_m',True),('m_f',False ),('b_v',True)],0.34)
bn.add('s_s',[('s_m',True),('m_f',False),('b_v',False )],0.12)
bn.add('s_s',[('s_m',False),('m_f',True),('b_v',True)],0.15)
bn.add('s_s',[('s_m',False),('m_f',True ),('b_v',False)],0.14)
bn.add('s_s',[('s_m',False),('m_f',False),('b_v',True )],0.132)
bn.add('s_s',[('s_m',False),('m_f',False),('b_v',False)],0.44)

bn.add('s_t',[('d',True )],0.08)
bn.add('s_t',[('d',False)],0.002)

bn.add('s_q',[('s_p',True ),('v_p',True )],0.008)
bn.add('s_q',[('s_p',True ),('v_p',False)],0.4)
bn.add('s_q',[('s_p',False),('v_p',True )],0.51)
bn.add('s_q',[('s_p',False),('v_p',False)],0.13)

bn.add('f_s',[],0.1)

bn.add('c_c',[('s_s',True )],0.49)
bn.add('c_c',[('s_s',False)],0.023)

bn.add('car_s',[('c_c',True),('s_t',True),('s_q',True ),('f_s',True )],0.091)
bn.add('car_s',[('c_c',True),('s_t',True),('s_q',True ),('f_s',False )],0.081)
bn.add('car_s',[('c_c',True),('s_t',True),('s_q',False ),('f_s',True )],0.045)
bn.add('car_s',[('c_c',True),('s_t',True),('s_q',False ),('f_s',False )],0.065)
bn.add('car_s',[('c_c',True),('s_t',False),('s_q',True ),('f_s',True)],0.087)
bn.add('car_s',[('c_c',True),('s_t',False),('s_q',True),('f_s',False )],0.043)
bn.add('car_s',[('c_c',True),('s_t',False),('s_q',False ),('f_s',True)],0.035)
bn.add('car_s',[('c_c',True),('s_t',False),('s_q',False),('f_s',False )],0.067)
bn.add('car_s',[('c_c',False),('s_t',True),('s_q',True),('f_s',True)],0.052)
bn.add('car_s',[('c_c',False),('s_t',True),('s_q',True),('f_s',False)],0.054)
bn.add('car_s',[('c_c',False),('s_t',True),('s_q',False),('f_s',True)],0.056)
bn.add('car_s',[('c_c',False),('s_t',True),('s_q',False),('f_s',False)],0.078)
bn.add('car_s',[('c_c',False),('s_t',False),('s_q',True),('f_s',True )],0.045)
bn.add('car_s',[('c_c',False),('s_t',False),('s_q',True),('f_s',False)],0.031)
bn.add('car_s',[('c_c',False),('s_t',False),('s_q',False),('f_s',True )],0.034)
bn.add('car_s',[('c_c',False),('s_t',False),('s_q',False),('f_s',False)],0.023)


print('----------------------------------------------------')
print('c_s','car_s',bn.independence_bag('c_s','car_s'))
print('----------------------------------------------------')
print('s_t','c_c',bn.independence_bag('s_t','c_c'))
print('----------------------------------------------------')
print('s_t','s_q',bn.independence_bag('s_t','s_q'))
print('----------------------------------------------------')
print('s_q','c_c',bn.independence_bag('s_q','c_c'))
print('----------------------------------------------------')



