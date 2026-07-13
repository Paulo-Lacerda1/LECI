
import time

from tpi1 import *
from cidades import *

city_connections = [ # Ligacoes por estrada
            ('Coimbra', 'Leiria', 73),
            ('Aveiro', 'Agueda', 35),
            ('Porto', 'Agueda', 79),
            ('Agueda', 'Coimbra', 45),
            ('Viseu', 'Agueda', 78),
            ('Aveiro', 'Porto', 78),
            ('Aveiro', 'Coimbra', 65),
            ('Figueira', 'Aveiro', 77),
            ('Braga', 'Porto', 57),
            ('Viseu', 'Guarda', 75),
            ('Viseu', 'Coimbra', 91),
            ('Figueira', 'Coimbra', 52),
            ('Leiria', 'Castelo Branco', 169),
            ('Figueira', 'Leiria', 62),
            ('Leiria', 'Santarem', 78),
            ('Santarem', 'Lisboa', 82),
            ('Santarem', 'Castelo Branco', 160),
            ('Castelo Branco', 'Viseu', 174),
            ('Santarem', 'Evora', 122),
            ('Lisboa', 'Evora', 132),
            ('Evora', 'Beja', 105),
            ('Lisboa', 'Beja', 178),
            ('Faro', 'Beja', 147),
            ('Braga', 'Guimaraes', 25),
            ('Braganca', 'Guimaraes', 200),
            ('Braganca', 'Guarda', 180),
            ('Braganca', 'Lamego', 150),
            ('Porto', 'Guimaraes', 44),
            ('Guarda', 'Covilha', 46),
            ('Viseu', 'Covilha', 57),
            ('Castelo Branco', 'Covilha', 62),
            ('Guarda', 'Castelo Branco', 96),
            ('Lamego','Guimaraes', 88),
            ('Lamego','Viseu', 47),
            ('Lamego','Guarda', 64),
            ('Portalegre','Castelo Branco', 64),
            ('Portalegre','Santarem', 157),
            ('Portalegre','Evora', 194) 
]

city_coordinates = {  # City coordinates
             'Aveiro': (41,215),
             'Figueira': ( 24, 161),
             'Coimbra': ( 60, 167),
             'Agueda': ( 58, 208),
             'Viseu': ( 104, 217),
             'Braga': ( 61, 317),
             'Porto': ( 45, 272),
             'Lisboa': ( 0, 0),
             'Santarem': ( 38, 59),
             'Leiria': ( 28, 115),
             'Castelo Branco': ( 140, 124),
             'Guarda': ( 159, 204),
             'Evora': (120, -10),
             'Beja': (125, -110),
             'Faro': (120, -250),
             'Braganca': (180,320),
             'Guimaraes': ( 71, 300),
             'Covilha': ( 130, 175),
             'Lamego' : (125,250),
             'Portalegre': (130,170) 
}

cidades_portugal = Cidades(city_connections,city_coordinates)

p = SearchProblem(cidades_portugal,'Braga','Faro')
q = SearchProblem(cidades_portugal,'Braganca','Beja')

# --------------------------------------------------------------
print('\n>> Ex. 1 ----------------------------------------------')
# --------------------------------------------------------------

t = MyTree(p)
print('(breadth)',t.search2())
print(t.terminal, t.non_terminal,'|',t.solution.depth,t.solution_cost)

t = MyTree(p,'depth')
print('(depth)',t.search2())
print(t.terminal, t.non_terminal,'|',t.solution.depth,t.solution_cost)

# --------------------------------------------------------------
print('\n>> Ex. 2 ----------------------------------------------')
# --------------------------------------------------------------

t = MyTree(p,'hybrid')
print('(hybrid)',t.search2())
print(t.terminal, t.non_terminal,'|',t.solution.depth, t.solution_cost)

t = MyTree(q,'hybrid')
print('(hybrid)',t.search2())
print(t.terminal, t.non_terminal,'|',t.solution.depth, t.solution_cost)

# --------------------------------------------------------------
print('\n>> Ex. 3 ----------------------------------------------')
# --------------------------------------------------------------

t = MyTree(p,'depth')
print('(depth/bipolar)',t.bipolar_search())
print(t.terminal, t.non_terminal,'|',t.solution_cost)

t = MyTree(q,'depth')
print('(depth/bipolar)',t.bipolar_search())
print(t.terminal, t.non_terminal,'|',t.solution_cost)

t = MyTree(p,'breadth')
print('(breadth/bipolar)',t.bipolar_search())
print(t.terminal, t.non_terminal,'|',t.solution_cost)

t = MyTree(p,'hybrid')
print('(hybrid/bipolar)',t.bipolar_search())
print(t.terminal, t.non_terminal,'|',t.solution_cost)


# --------------------------------------------------------------
print('\n>> Ex. 4 ----------------------------------------------')
# --------------------------------------------------------------

bw = MySTRIPS()

initial_state = { Floor('a'), Floor('b'),      #    _
    Floor('d'), Holds('t1','e'), On('c','d'),  #   / \               |h|
    Free('a'), Free('b'), On('f','c'),         #  |  (e)             |g|
    On('g','f'), On('h','g'), Free('h'),       #  |                  |f|
    HandleWith('a','t1'),                      #  |                  |c|
    HandleWith('b','t1'),                      # _|___|a|____|b|_____|d|_    
    HandleWith('c','t1'),                      # 
    HandleWith('d','t1'), HandleWith('e','t1'),
    HandleWith('f','t1'), HandleWith('g','t1'), HandleWith('h','t2') }

t0 = time.process_time()
lst = bw.actions(initial_state)
t1 = time.process_time()
print('actions():',lst,t1-t0)
lst = bw.actions2(initial_state)
t2 = time.process_time()
print('actions2():',lst,t2-t1,'\n')


initial_state = { Floor('a'), Floor('b'),      #    _
    Floor('d'), Holds('t1','e'), On('c','d'),  #   / \               |h|
    Free('i'), Free('k'), On('f','c'),         #  |  (e)             |g|
    On('g','f'), On('h','g'), Free('h'),       #  |          |k|     |f|
    HandleWith('a','t1'),                      #  |   |i|    |j|     |c|
    HandleWith('b','t1'),                      # _|___|a|____|b|_____|d|_    
    HandleWith('c','t1'),                      # 
    HandleWith('d','t1'), HandleWith('e','t1'),
    HandleWith('f','t1'), HandleWith('g','t1'), HandleWith('h','t2'),
    HandleWith('i','t1'), HandleWith('j','t1'), HandleWith('k','t2'),
    On('i','a'), On('j','b'), On('k','j') }

t0 = time.process_time()
lst = bw.actions(initial_state)
t1 = time.process_time()
print('actions():',lst,t1-t0)
lst = bw.actions2(initial_state)
t2 = time.process_time()
print('actions2():',lst,t2-t1,'\n')
                                               #                     |x|
                                               #                     |y|
initial_state = { Floor('a'), Floor('b'),      #    _                |z|
    Floor('d'), Holds('t1','e'), On('c','d'),  #   / \               |h|
    Free('i'), Free('k'), On('f','c'),         #  |  (e)             |g|
    On('g','f'), On('h','g'), Free('z'),       #  |          |k|     |f|
    HandleWith('a','t1'),                      #  |   |i|    |j|     |c|
    HandleWith('b','t1'),                      # _|___|a|____|b|_____|d|_    
    HandleWith('c','t1'),                      # 
    HandleWith('d','t1'), HandleWith('e','t1'),
    HandleWith('f','t1'), HandleWith('g','t1'), HandleWith('h','t2'),
    HandleWith('i','t1'), HandleWith('j','t1'), HandleWith('k','t2'),
    On('i','a'), On('j','b'), On('k','j'),
    HandleWith('i','t3'), HandleWith('j','t3'), HandleWith('k','t3'),
    On('x','y'), On('y','z'), On('z','h') }

t0 = time.process_time()
lst = bw.actions(initial_state)
t1 = time.process_time()
print('actions():',lst,t1-t0)
lst = bw.actions2(initial_state)
t2 = time.process_time()
print('actions2():',lst,t2-t1,'\n')
 

