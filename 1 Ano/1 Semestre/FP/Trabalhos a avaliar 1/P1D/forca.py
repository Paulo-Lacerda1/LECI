AUTORES = [120188, 114251] #ruben miguel almeida coelho e Roberto Afonso Alves Mota

import re
import random
import unicodedata

# Função para remover acentos e cedilhas
def remover_acentos(letra):
    letra = unicodedata.normalize('NFD', letra)
    letra = ''.join(c for c in letra if not unicodedata.combining(c))
    return letra

# Função para normalizar uma palavra 
def palavra_sem_acentos(palavra):
    return ''.join(remover_acentos(letra) for letra in palavra)

# Função que substitui cada letra por _
def substituir_palavra(palavra):
    return re.sub(r'[a-zA-Z]', '_ ', ''.join(palavra_sem_acentos(letra) for letra in palavra))

# Função para mostrar a palavra atual
def mostrar_palavra(secret):
    return secret

def Vitoria():
    print('   ._=_=_=_=_.    ')
    print('  .-\\\:    ://-.  ')
    print(' | (|:.    .:|) | ')
    print('  - |:.    .:| -  ')
    print('    \\\     //     ')
    print('       ) (        ')
    print('      _| |_       ')
    print('     |_____|      ')                #desenho do vitória

def Derrota():
    print('    _____________    ')
    print('   /---       ---\   ')
    print(' /|  XXX     XXX  |\ ')
    print(' \|  XXX     XXX  |/ ')
    print('  |               |  ')
    print('  \      XXX      /  ')
    print('   \_   XXXXX   _/   ')
    print('    |\         /|    ')
    print('    ||  I-I-I  ||    ')
    print('     \_________/     ')            #desenho da derrota
 
# Função para verificar se a letra digitada está correta
def letra_correta(letra, palavra):
    return letra in palavra_sem_acentos(palavra)


# Função para mostrar as letras usadas
def mostrar_letras_usadas(letras):
    letras_usadas = "Letras usadas: "
    for letra in letras:
        letras_usadas += letra + " "
    return letras_usadas


# Função que mostra o enforcado da forca
def base_forca(erros):
    Enforcado = [
        '______',    #linha=0
        '||   |',    #linha=1
        '||',        #linha=2 e assim por diante...
        '||', 
        '||',
        '||',
        '||',
        '--',        #linha=8
    ]
    #A cada erro que é adicionado à função altera a linha individualmente para construir o enforcado
    if erros > 0:
        Enforcado[2] = '||   O'

    if erros > 1:
        Enforcado[3] = '||   |'

    if erros > 2:
        Enforcado[4] = '||   |'
    if erros > 3:
        Enforcado[3] = '||  /|'

    if erros > 4:
        Enforcado[3] = '||  /|\\'

    if erros > 5:
        Enforcado[5] = '||  /'

    if erros > 6:
        Enforcado[5] = '||  / \\'
    
    return '\n'.join(Enforcado)
#O join junta tudo em uma unica string neste caso o enforcado e o '\n' é para mudar de paragrafo 


# Função principal do jogo
def main():
    from wordlist import words1, words2
    
    # Descomente a linha que interessar para testar
    #words = words1                                  # palavras sem acentos nem cedilhas.
    words = words2                                 # palavras com acentos ou cedilhas.
    #words = words1 + words2                        # palavras de ambos os tipos

    import sys                                      # INCLUA estas 3 linhas para permitir
    if len(sys.argv) > 1:                           # correr o programa com palavras dadas:
         words = sys.argv[1:]                       # python3 forca.py duas palavras

    secret = (random.choice(words).upper())         # Escolhe uma palavra aleatoriamente
    palavra= substituir_palavra(secret)             # Chama a funçao para substituir a 'secret' por underlines
    erros = 0
    letras_usadas = []                              # Lista das letras já utilizadas
    print('----Jogo da Forca----')

    while True:
        print(base_forca(erros))
        print(mostrar_palavra(palavra))
        print(mostrar_letras_usadas(letras_usadas))
        print('Número de erros: ', erros)

        letra = input('Coloque uma letra: ').upper() # Função para o jogador colocar a letra e a mete em maiusculo
        letra_sem_acento = remover_acentos(letra)        # Chama a funçao para tirar os acentos da letra

#Código que vê se a letra é válida ou se colocou mais de uma letra ou colocou um número, o que manda repetir para colocar uma letra válida
        if not letra_sem_acento.isalpha() or len(letra_sem_acento) != 1 or letra_sem_acento in ('º', 'ª'):
            print('Insira uma única letra válida.')
            continue
#Ao repetir uma letra já usada o código invalida e pede para O jogador  outra letra.
        if letra_sem_acento in letras_usadas:
            print('Letra repetida. Tenta novamente!!!. TRY AGAIN!!!')
            continue
#Adiciona á lista de letras_usadas a ultima letra que o jogadador colocou
        letras_usadas.append(letra_sem_acento)
#A função ve se a letra pertence á palavra
        if letra_correta(letra_sem_acento, secret):
            nova_palavra = ''
            for i in range(len(secret)):
                if remover_acentos(secret[i]) == letra_sem_acento:
                    nova_palavra += secret[i] + ' '
                else:
                    nova_palavra += palavra[2 * i] + ' '
            palavra = nova_palavra
#A cada vez que erra é adicionado um erro
        else:
            erros += 1
#Quando a palavra nao tem mais underlines o jogador ganhou
        if '_' not in palavra:
            Vitoria()
            print('Parabéns! Acertaste! A palavra era ', secret, '.')
            break
#Quando o jogo tem 7 erros o jogador perdeu
        if erros == 7:
            Derrota()
            print(base_forca(erros))
            print('Numero de erros: 7')
            print('Game Over... A palavra era ', secret, '.')
            break

if __name__ == '__main__':
    main()