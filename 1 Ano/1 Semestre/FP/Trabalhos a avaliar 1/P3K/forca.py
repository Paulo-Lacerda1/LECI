# Preencha a lista com os números mecanográficos dos autores.
AUTORES = [118536] # Grupo P3K.
   
import random
import os

# Defina funções aqui.

def clearScreen():
    # Limpa a informação no ecrã, 'clear' para linux e mac (Posix), 'cls' para windows (NT).
    command = "clear"
    if os.name == "nt":
        command = "cls"
    os.system(command)


def printSeparator(char, ident, length):
    # Imprime o separador.
    print(ident + char * length)
    

def printTextCenter(ident, text, length):
    # Imprime o texto centrado.
    left = ""
    if length > len(text):
       left = " " * int((length - len(text)) // 2)
    print(ident + left + text)
    

def printTitle(ident, title, length, char = "="):
    # Imprime o título.
    printSeparator(char, ident, length)
    printTextCenter(ident, title, length)
    printSeparator(char, ident, length)


def printFooter(ident, length, char = "="):
    # Imprime o fundo.
    printSeparator(char, ident, length)
    print(ident)
    
    
def printMessage(msg, ident):
    # Imprime a mensagem de aviso.
    if len(msg) > 0: 
        print(ident + "* {}".format(msg), end="\n\n")
            

def printRules(ident, title, length, letterCheck):
    # Imprime as regras.
    clearScreen()
    printTitle(ident, title, length)
    printTextCenter(ident, "REGRAS", length)
    printSeparator("-", ident, length)
    print(ident)
    print(ident + "As apostas podem ser singulares ou ser a palavra completa.")
    print(ident + "As apostas válidas estão contidas nesta lista:", end="\n\n")
    apostas = "".join(letterCheck)
    print(ident + "{:>9}:".format("Apostas"), "[{}]".format(apostas.lower()))
    print(ident + "{:>9}:".format(""), "[{}]".format(apostas), end="\n\n")
    print(ident + "Se a aposta singular for válida mas não existir na palavra,")
    print(ident + "conta como erro.", end="\n\n")
    print(ident + "No caso de tentar advinhar a palavra completa,")
    print(ident + "apenas será válida se as letras estarem contidas na lista.")
    print(ident + "Se for válida mas ser a palavra errada, conta como erro.", end="\n\n")
    print(ident + "Se errar 7 vezes, o jogo acaba. Boa sorte!", end="\n\n")
    printFooter(ident, length)
    

def printExitMessage(ident, title, length):
    # Imprime a mensagem de saída.
    clearScreen()
    printTitle(ident, title, length)
    print(ident)
    printTextCenter(ident, "Obrigado por jogar! Espero que tenha gostado!", length)
    print(ident)
    printFooter(ident, length)
    
    
def printHangMan(errors, ident):
    # Imprime o topo da forca.
    print(ident + "_" * 5)
    
    if errors == 7:
        # Se o jogador cometer 7 erros, imprime a corda da forca.
        print(ident + "|/" + " " * 2 + "|")
    else:
        print(ident + "|/")
    
    if errors >= 1:
        # Se o jogador cometer 1 ou mais erros, imprime a cabeça.
        print(ident + "|" + " " * 3 + "O")
    else:
        print(ident + "|")
    
    if errors >= 4:
         # Se o jogador cometer 4 ou mais erros, imprime o corpo e ambos braços (esquerdo e direito).
        print(ident + "|" + " " * 2 + "/|\\")
    elif errors == 3:
        # Se o jogador cometer 3 erros, imprime o corpo e o braço esquerdo.
       print(ident + "|" + " " * 2 + "/|")
    elif errors == 2:
        # Se o jogador cometer 2 erros, imprime o corpo.
        print(ident + "|" + " " * 3 + "|")
    else:
        print(ident + "|")
    
    if errors >= 6:
         # Se o jogador cometer 6 ou mais erros, imprime ambas pernas (esquerda e direita).
        print(ident + "|" + " " * 2 + "/ \\")
    elif errors == 5:
        # Se o jogador cometer 5 erros, imprime a perna esquerda.
        print(ident + "|" + " " * 2 + "/")
    else:
        print(ident + "|")
    
    # Imprime a base da forca e o números de erros já feitos.
    print(ident + "|" +  "_" * 6,"ERROS: {}".format(errors), sep="\t", end="\n\n")


def printLettersShow(lettersShow, ident):
    # Imprime a palavra com um espaço entre cada letra.
    print(ident + " ".join(lettersShow), end="\n\n")
     

def addStringToList(string):
    # Adiciona os carateres à lista.
    result = []
    for char in string:
        result.append(char)
    return result


def getLetterIndex(letter, letterCheck):
    # Verifica se a letra apostada existe na lista, retorna o índice -1 caso não exista.
    for index, list in enumerate(letterCheck):
        if letter in list:
            return index
    return -1


def getLetterCount(secret, letterValues, lettersMatch, lettersShow):
    # Verifica se a letra apostada existe na palavra secreta, retorna a contagem.
    count = 0
    for value in letterValues:
        for index, letter in enumerate(secret):
            if value == letter:
                lettersShow[index] = letter
                if value not in lettersMatch:
                    lettersMatch.append(value)
                    count += 1
    return count


def isGuessValid(guess, letterCheck):
    # Verifica se a palavra apostada contêm as letras da lista de validação.
    result = True
    letters = "".join(letterCheck)
    for letter in guess:
        if letter in letters:
            continue
        else:
            result = False
            break
    return result


def isGuessMatch(guess, secret, letterCheck):
    # Verifica se a palavra apostada é a palavra secreta.
    result = True
    for index, letter in enumerate(guess):
        if letter == secret[index]:
            continue
        else:
            indexGuess = getLetterIndex(letter, letterCheck)
            indexSecret = getLetterIndex(secret[index], letterCheck)
            if indexGuess == indexSecret:
                continue
            else:
                result = False
                break
    return result

   
def menuGame(words, ident, title, length, letterCheck):
    # Escolhe palavra aleatoriamente.
    secret = random.choice(words).upper()

    # Adiciona à lista de apostas apenas as primeiras letras de validação.
    letterGuess = []
    for letter in letterCheck:
        letterGuess.append(letter[0])

    # Substitui todas as letras da palavra por '_' e adiciona à lista.
    lettersShow = addStringToList("_" * len(secret))
    
    # Inicializa as variáveis de controlo.
    lettersMatch = []
    lettersFail = []
    errors = 0
    msg = ""
    
    # O jogador continua a apostar desde que tenha cometido menos de 7 erros e exista letras da palavra por advinhar.
    while (errors < 7 and "_" in lettersShow):
        # Limpa a informação no ecrã e imprime o progresso das apostas.
        clearScreen()
        printTitle(ident, title, length)
        printHangMan(errors, ident)
        printLettersShow(lettersShow, ident)
        printFooter(ident, length)
        printMessage(msg, ident)
        
        msg = ""
        guess = input(ident + "Aposta [{}]? ".format("".join(letterGuess)))
    
        if len(guess) == 1:
            if guess.isalpha():
                guess = guess.upper()
                # Verifica se a letra apostada existe na lista.
                index = getLetterIndex(guess, letterCheck)
                if index >= 0:
                    if letterCheck[index][0] not in lettersMatch and letterCheck[index][0] not in lettersFail:
                        # Verifica se a letra apostada existe na palavra.
                        match = getLetterCount(secret, letterCheck[index], lettersMatch, lettersShow)
                        if match == 0:
                            lettersFail.append(letterCheck[index][0])
                            letterGuess[index] = "-"
                            errors += 1
                            msg = "Errou, a palavra não tem {}.".format(guess)
                        else:
                            letterGuess[index] = "+"
                            msg = "Acertou em {}!".format(" e ".join(lettersMatch[-match:]))
                    else:
                         msg = "Aposta repetida em {}, tente novamente.".format(letterCheck[index][0])
                else:
                    # A aposta é uma letra mas não é válida, isto é, não existe na lista de validação.
                    msg = "{} não é uma aposta válida, tente novamente".format(guess)
            else:
                # A aposta não é uma letra.
                msg = "{} não é uma aposta válida, tente novamente.".format(guess)
        else:
            guess = guess.upper()
            if len(guess) == len(secret) and isGuessValid(guess, letterCheck):
                if isGuessMatch(guess, secret, letterCheck):
                    # O jogador advinhou a palavra, o jogo acaba.
                    lettersShow = addStringToList(secret)
                    break
                else:
                    errors += 1
                    msg = "{} não é a palavra certa, tente novamente.".format(guess)
            else:
                msg = "{} não é uma aposta válida, tente novamente.".format(guess)
    
    msg = ""

    # Imprime a mensagem de fim do jogo, pergunta se deseja volta a jogar.
    while True:
        # Resultado final do jogo.
        result = "GANHOU!"
        if errors == 7:
            result = "PERDEU!"
        
        # Revela a palavra secreta.
        if "_" in lettersShow:
            lettersShow = addStringToList(secret)
        
        clearScreen()
        printTitle(ident, title, length)
        printHangMan(errors, ident)
        printLettersShow(lettersShow, ident)
        printSeparator("=", ident, length)
        printTextCenter(ident, result, length)
        printSeparator("-", ident, length)

        # Imprime o resultado das apostas certas e erradas.
        if len(lettersMatch) > 0:
            print(ident + "{:>10}:".format("Acertou"), ", ".join(lettersMatch), end=";\n")
        if len(lettersFail) > 0:
            print(ident + "{:>10}:".format("Errou"), ", ".join(lettersFail), end=";\n")
        
        printFooter(ident, length)
        printMessage(msg, ident)
        
        option = input(ident + "Deseja voltar a jogar [s/n]? ")

        if option.isalpha() and option.lower() == "n":
            # Volta para o menu principal.
            break
        elif option.isalpha() and option.lower() == "s":
            # Recomeça um novo jogo, com o mesmo tipo de palavra escolhido.
            menuGame(words, ident, title, length, letterCheck)
            break
        else:
            # A opção é inválida.
            msg = "Opção inválida! Tente novamente."
    
    
def menuOptions(msg, ident, title, length):
    # Apresenta o menu inicial com as opções do jogo da Forca.
    printTitle(ident, title, length)
    print(ident + "[ 1 ] - Jogar ( Palavras sem acentos nem cedilhas )")
    print(ident + "[ 2 ] - Jogar ( Palavras com acentos ou cedilhas )")
    print(ident + "[ 3 ] - Jogar ( Palavras de ambos os tipos )")
    printSeparator("-", ident, length)
    print(ident + "[ 9 ] - Regras")
    printSeparator("-", ident, length)
    print(ident + "[ 0 ] - Sair")
    printFooter(ident, length)
    printMessage(msg, ident)
    option = input(ident + "Opção? ")
    return option


def main():
    from wordlist import words1, words2
   
    # Lista com a letras para validar as apostas.
    letterCheck = ["AÁÃÂÀ", "B", "CÇ", "D", "EÉÊÈ", "F", "G", "H", "IÍÎÌ", "J", "K", "L", "M",
                   "N", "OÓÔÒÕ", "P", "Q", "R", "S", "T", "UÚÛÙ", "V", "W", "X", "Y", "Z"]
    
    # Variáveis que modificam o aspeto do jogo.
    title = "F O R C A"
    ident = " " * 2
    length  = 60
    
    # Para correr o programa com palavras dadas.
    import sys
    if len(sys.argv) > 1:
         words = sys.argv[1:]
         menuGame(words, ident, title, length, letterCheck)
         printExitMessage(ident, title, length)
         return
    
    # Variável com a mensagem de aviso para o jogador.
    msg = ""
    
    while True:
        # Limpa a informação no ecrã.
        clearScreen()
        # Escolha da opção do jogo.
        option = menuOptions(msg, ident, title, length) 
        # Limpa a mensagem de aviso para o jogador.
        msg = ""
        if option == "0":
            # Sai do jogo.
            break
        elif option == "1":
            # Palavras sem acentos nem cedilhas
            menuGame(words1, ident, title, length, letterCheck)
        elif option == "2":
            # Palavras com acentos ou cedilhas
            menuGame(words2, ident, title, length, letterCheck)
        elif option == "3":
            # Palavras de ambos os tipos.
            menuGame(words1 + words2, ident, title, length, letterCheck)
        elif option == "9":
            # Imprime as regras do jogo.
            printRules(ident, title, length, letterCheck)
            input(ident + "Pressione a tecla [Enter] para voltar ao menu de opções.")
        else:
            # A opção é inválida.
            msg = "Opção inválida! Tente novamente."
    
    # Imprime a mensagem de saída.
    printExitMessage(ident, title, length)
    
    
if __name__ == "__main__":
    main()

