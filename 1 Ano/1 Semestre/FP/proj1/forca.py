AUTORES = ["119378", "120202"]

import random

def normalize(l):
    # Transforma caracteres especiais especificos em caracteres normais especificos
    letras = {
        'Á': 'A',
        'À': 'A',
        'Â': 'A',
        'Ã': 'A',
        'É': 'E',
        'Ê': 'E',
        'Í': 'I',
        'Î': 'I',
        'Ó': 'O',
        'Ô': 'O',
        'Õ': 'O',
        'Ú': 'U',
        'Û': 'U',
        'Ç': 'C'
    }
    if l in letras:
        return letras[l]
    return l
def normalize_word(word):
    # Normaliza uma palavra por inteiro
    normalized_word = ""
    for character in word:
        normalized_character = normalize(character)
        normalized_word += normalized_character 
    return normalized_word #Devolve a palavra já normalizada (sem caraceteres especiais)

def stage(n):
    # Devolve o estado do jogo consoante os erros que o utilizador já cometeu
    from hangmanart import stages
    print(f"{stages[n]} Erros = {n}\n")
 


def game(word):
    word_length = len(word)
    # Criar lista de blanks com o mesmo numero de letras da palavra selecionada
    display = []
    for _ in range(word_length):
        display += "_"

    end_of_game = False
    wrong_guesses = []
    erros = 0

    while not end_of_game:
        guess = input("Adivinha uma letra: ").upper()
        guess = normalize(guess) # No caso de o utilizador introduzir uma letra especial como 'Á' ou 'Ç' o programa assume como 'A' ou 'C' respetivamente
        
        # Se o user submeter um número ou ""
        if not guess.isalpha() or len(guess) != 1: 
            print("Por favor, insere uma única letra não numérica.")
            continue

        #Se o utilizador introduziu uma letra certa que já tinha acertado anteriormentes, mostra a letra e avisa
        if guess in display:
            print(f"Já tinhas acertado a letra '{guess}'. Tenta outra letra.")
    
        # Verificar a letra adivinhad
        # Se o user escolher letra "C" e a palavra tiver "Ç" também substitui
        for position in range(word_length):
            letter = word[position]
            letter_normalized = normalize(letter)
            if letter_normalized == guess:
                display[position] = letter

        #Caso o user esteja errado
        if guess in wrong_guesses:              #Se estiver certo, substitui em display. 
            # Se for a segunda vez que o user tentado essa letra, e a mesma esteja errada
            print(f"Já tentaste adivinhar '{guess}' e estava errada.")
        elif guess not in normalize_word(word):
            # Se for a primeira vez que o user tenta essa letra, e a mesma esteja errada
            print(f"A letra que adivinhaste '{guess}' não está na palavra.")
            wrong_guesses.append(guess)        
            
            #Contabilização do número de erros
            erros += 1
            if erros == 6:
                end_of_game = True
                print(f"Perdeste! A palavra era {word}")
        
        # Junta todos os elementos na lista e transforma-os numa String
        print(f"{' '.join(display)}")

        # Verifica se o user já acertou as letras todas
        if "_" not in display:
            end_of_game = True
            print("Ganhaste!")
           
        stage(erros)    

def main():
    from hangmanart import logo
    from wordlist import words1, words2
    print(f"{logo}\n")

    words = words1 + words2 
   
    # Escolhe palavra aleatoriamente
    secret = random.choice(words).upper()

    #print(f"A solução é {secret}")
    game(secret)

if __name__ == "__main__":
    main()