#Programa para processar chamadas
import csv
def menu():
    print("1) Registar chamada\n2) Ler ficheiro\n3) Listar clientes\n4) Fatura\n5) Terminar ")
    opcao = int(input("Opção?"))
    return opcao



def validar_numero_telefone():
    while True:
        numero = input("Por favor, insira o número de telefone: ")
        if numero[0] == "+":       
            if len(numero[1:]) < 3:
                print("Número de telefone inválido. Deve conter pelo menos 3 dígitos.")
                continue

            if numero[0] == '+':
                numero = numero[1:]

            if not numero.isdigit():
                print("Número de telefone inválido. Deve conter apenas dígitos.")
                continue

            return numero
        else:
            if len(numero) < 3:
                print("Número de telefone inválido. Deve conter pelo menos 3 dígitos.")
                continue

            if not numero.isdigit():
                print("Número de telefone inválido. Deve conter apenas dígitos.")
                continue
            return numero

def registar_chamada():
    origem = validar_numero_telefone()
    
    while True:
        destino = validar_numero_telefone()
        if destino != origem:
            break
        else:
            print("Número de destino não pode ser igual ao número de origem. Tente novamente.")
    
    duracao = int(input("Duração (s): "))
    if duracao < 0:
        print("Insira uma duração positiva.")
    
    return origem, destino, duracao

def ler_chamada():
    nome_ficheiro = input("Qual ficheiro deseja ler?")
    with open(nome_ficheiro,"r") as arquivo:
        for numero in arquivo:
            print(numero)


def clientes_origem():
    
    clientes = set()
    
    with open("chamadas1.txt", "r", encoding="UTF-8", newline="") as chamadas1:
        leitor_csv1 = csv.reader(chamadas1, delimiter='\t')
        for elementos in leitor_csv1:
            if elementos:  # Verifica se a lista de elementos não está vazia
                clientes.add(elementos[0])

    with open("chamadas2.txt", "r", encoding="UTF-8", newline="") as chamadas2:
        leitor_csv2 = csv.reader(chamadas2, delimiter='\t')
        for elementos in leitor_csv2:
            if elementos:  # Verifica se a lista de elementos não está vazia
                clientes.add(elementos[0])

    clientes_ordenados = sorted(clientes)

    print("Clientes:", " ".join(clientes_ordenados))



def main():
    opcao = menu()
    if opcao == 1:
        registar_chamada()
    elif opcao == 2:
        ler_chamada()
    elif opcao == 3 :
        clientes_origem()        


if __name__ == "__main__":
    main()