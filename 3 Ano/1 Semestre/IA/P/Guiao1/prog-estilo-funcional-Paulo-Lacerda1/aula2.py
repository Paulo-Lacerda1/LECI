import math

#Exercicio 4.1
impar = lambda x: x % 2 == 1

#Exercicio 4.2
positivo = lambda y : y > 0 

#Exercicio 4.3
comparar_modulo = lambda x,y : abs(x)<abs(y)                      #nota: abs() -> absoluto

#Exercicio 4.4
cart2pol = lambda x, y: (math.sqrt(x**2 + y**2), math.atan2(y, x))

#Exercicio 4.5
ex5 = lambda f, g, h: lambda x, y, z: h(f(x, y), g(y, z))

#Exercicio 4.6
def quantificador_universal(lista, f):
    if lista == []:
        return True
    primeiro_elemento = lista[0]
    booleano = quantificador_universal(lista[1:],f)
    if not f(primeiro_elemento):
        return False
    if not booleano:
        return False
    return True
    pass

#Exercicio 4.7
def quantificador_unitario(lista, f):
    if not lista:            
        return False
    if f(lista[0]):          # se o primeiro elemento satisfaz f → True
        return True
    return quantificador_unitario(lista[1:], f) 

#Exercicio 4.8
def subconjunto(lista1, lista2):
    if not lista1:            # caso base: lista1 vazia → todos os elementos foram encontrados
        return True
    elemento_l1 = lista1[0]
    if elemento_l1 not in lista2:
        return False
    return subconjunto(lista1[1:], lista2)  # verifica o resto da lista
    pass

#Exercicio 4.9
def menor_ordem(lista, f):
    if len(lista) == 1:
        return lista[0]

    resto = menor_ordem(lista[1:], f)

    # compara o primeiro com o menor do resto usando f
    if f(lista[0], resto):
        return lista[0]
    else:
        return resto


#Exercicio 4.10
def menor_e_resto_ordem(lista, f):
    if len(lista) == 1:
        return (lista[0], [])

    # divide a lista em primeiro e resto
    resultado = menor_e_resto_ordem(lista[1:], f)
    menor_do_resto = resultado[0]
    resto = resultado[1]                    # ([1],[2,3,4])


    # compara o primeiro com o menor encontrado no resto
    if f(lista[0], menor_do_resto):  
        # lista[0] é o menor
        return (lista[0], [menor_do_resto] + resto)
    else:

        return (menor_do_resto, [lista[0]] + resto)


#Exercicio 5.2
def ordenar_seleccao(lista, ordem):
    
    if len(lista) <= 1:
        return lista

    meio = len(lista) // 2
    esquerda = ordenar_seleccao(lista[:meio], ordem)
    direita = ordenar_seleccao(lista[meio:], ordem)
    
    resultado = []
    i, j = 0, 0
    while i < len(esquerda) and j < len(direita):
        if ordem(esquerda[i], direita[j]):
            resultado.append(esquerda[i])
            i += 1
        else:
            resultado.append(direita[j])
            j += 1

    resultado.extend(esquerda[i:])
    resultado.extend(direita[j:])

    return resultado
pass
