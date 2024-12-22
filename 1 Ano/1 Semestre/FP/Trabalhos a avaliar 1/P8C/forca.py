# Diana Abalon = [115684]
abecedario = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
              "V", "W", "X", "Y", "Z"]
por_testar = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
              "V", "W", "X", "Y", "Z"]
forca = [
    """
    ______
   |      |
   |
   |
   |
   |_________
    """,
    """
    ______
   |      |
   |      0
   |
   |
   |_________
    """,
    """
    ______
   |      |
   |      0
   |      |
   |
   |_________
    """,
    """
    ______
   |      |
   |      0
   |     /|
   |
   |_________
    """,
    """
    ______
   |      |
   |      0
   |     /|\\
   |
   |_________
    """,
    """
    ______
   |      |
   |      0
   |     /|\\
   |     /
   |_________
    """,
    """
    ______
   |      |
   |      0
   |     /|\\
   |     / \\
   |_________
    """]


def remover(entrada, por_testar):
    for n in reversed(range(len(por_testar))):
        if por_testar[n] == entrada:
            por_testar.pop(n)
        # pop(n) é o metodo para remover um elemento de uma lista,
        # onde n é o índice do elemento a ser removido

# ecrã inicial
def inicio(tracejados):
    print(forca[0])
    print("Erros: ", 0)
    print(" ".join(tracejados)) ####
def jogo(secret, tracejados, erros, por_testar):
    entrada = input("Insira uma letra: ")
    entrada = entrada.upper()
    contador = 0
    acentos = [["Á", "À", "Â", "Ã", "A"], ["É", "Ê", "E"], ["Í", "Î", "I"], ["Ó", "Ô", "Õ", "O"], ["Ú", "Û", "U"],
               ["C", "Ç"]]
    for x in range(len(acentos)):
        if entrada in acentos[x]:
            for n in range(len(secret)):
                if secret[n] in acentos[x]:
                    tracejados[n] = secret[n]
                    contador += 1
    remover(entrada, por_testar)
    if entrada not in acentos:
        for y in range(len(secret)):
            if secret[y] == entrada:
                tracejados[y] = secret[y]
                contador += 1
    if contador == 0:
        erros += 1
    remover(entrada, por_testar)
    print(forca[erros])
    print("Erros: ", erros)
    print(" ".join(tracejados))
    print(por_testar)
    return secret, tracejados, erros, por_testar
def repetir(secret, tracejados, por_testar):
    erros = 0
    while tracejados.count("_") > 0 and erros < 6:
        secret, tracejados, erros, por_testar = jogo(secret, tracejados, erros, por_testar)
    if tracejados.count("_") == 0: ####
        print("PARABÉNS, GANHASTE!")
    else:
        print("Perdeste! A palavra era: ", secret)

import random

# Defina funções aqui.
...
def main():
    from wordlist import words1, words2

    # Descomente a linha que interessar para testar
   # words = words1  # palavras sem acentos nem cedilhas.
    # words = words2             # palavras com acentos ou cedilhas.
    words = words1 + words2    # palavras de ambos os tipos

    # Escolhe palavra aleatoriamente
    secret = random.choice(words).upper()
    tracejados = "_" * len(secret)
    tracejados = list(tracejados)
    inicio(tracejados)
    print(por_testar)
    repetir(secret, tracejados, por_testar)


    # Complete o programa
    ...


if __name__ == "__main__":
    main()

"""""
PONTOS NEGATIVOS:

- tem codigo redundante (comentários a mais)
- se inseriemos uma letra ou mais  o programa nao percebe e regista como fosse um erro
- o ENTER é reconhecido como um erro
- nao está especificado as letras usadas e as letras por usai


"""