# Programa para organizar equipas de futebol 

import csv
import sys
def obter_tuplos():
    lista = []
    with open("ClubsRanking.csv", "r", encoding="utf-8", newline="") as arquivo:
        leitor_csv = csv.reader(arquivo)
        for elemento in leitor_csv:
            tuplo = tuple(elemento)
            lista.append(tuplo)       
        print(lista)
    return lista

lista = obter_tuplos()

def nome_pais(lista):
    pais = input("\nQual país deseja ver os clubes?")
    resultados = [] 
    for elemento in lista:
        if pais.lower() == elemento[2].lower():
            resultado = elemento[0], elemento[1], elemento[3]
            resultados.append(resultado)
    if not resultados:
        print("\nO país que inseriu não é válido!")
        nome_pais(lista)
    else:
        print("\n",resultados)
    return resultados


resultados = nome_pais(lista)

def escrever_resultado(resultados):
    nome_do_ficheiro = str(input("\nQual o nome que deseja guardar a informacao?"))
    print("O ficheiro foi guardado em {}".format(nome_do_ficheiro))
    resultados = str(resultados) 
    with open(nome_do_ficheiro,"w",encoding="utf-8", newline="") as documento:
        documento.write(resultados)

escrever_resultado(resultados)


def dicionario(lista):
    input("\nClique ENTER para ver o dicionário que contenha os paises e os clubes correspondentes!")
    dic={}
    for elemento in lista:
        pais = elemento[2]
        clube = elemento[1]
        if pais not in dic:
            dic[pais] = [clube]
        else:
            dic[pais].append(clube)
    print(dic)
    return dic

dic = dicionario(lista)
print(dic)

def subiu_ranking():
    with open("ClubsRanking.csv", "r", encoding="utf-8", newline="") as arquivo:
        leitor_csv = csv.reader(arquivo)
        cabecalhos = next(leitor_csv)
        dados = list(leitor_csv)
    
    maximo = max(float(elemento[4]) for elemento in dados)
    
    for elemento in dados:
        if maximo == float(elemento[4]):
            clube_maior_subida = elemento[1]
            print("\n")
            print("O clube que subiu mais no ranking foi", clube_maior_subida)
            break

subiu_ranking()

def pesquisar_clube():
    clube = input("\nQual é o clube que deseja ver o ranking?").lower()
    encontrado = False
    with open("ClubsRanking.csv", "r", encoding="utf-8", newline="") as arquivo:
        leitor_csv = csv.reader(arquivo)
        cabecalhos = next(leitor_csv)
        for elementos in leitor_csv:
            if clube == elementos[1].lower():
                print("\nO ranking do {}é:{}".format(clube,elementos))
                encontrado = True
                break
    if not encontrado:
        print("O clube que introduziu não pertence aos clubes disponíveis")
        pesquisar_clube()

pesquisar_clube()

def media_do_país(dic):
    input("\nAqui podes ver as médias dos países por ordem decrescente, clica ENTER para continuar")
    
    lista_medias = []

    for chave, clubes in dic.items():
        if chave.lower() == 'country':
            continue
        soma_rankings = sum(float(elemento[3]) for elemento in lista if elemento[1] in clubes and elemento[3].isdigit())
        media = soma_rankings / len(clubes) 
        lista_medias.append((chave, media))

    lista_medias = sorted(lista_medias, key=lambda x: x[1], reverse=True)
    
    for pais, media in lista_medias:
        print("{} : {}".format(pais, media))

    return lista_medias

lista_ordenada = media_do_país(dic)


def main():
    opcao = str(input("O que deseja fazer?"))
    if opcao == 1:
        nome_pais(lista)
    elif opcao == 2:


