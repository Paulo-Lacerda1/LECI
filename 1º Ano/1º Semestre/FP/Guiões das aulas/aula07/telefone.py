# Complete este programa como pedido no guião da aula.

def listContacts(dic):
    """Print the contents of the dictionary as a table, one item per row."""
    print("\n{:>12s} : {}".format("Numero", "Nome"))
    for num in dic:
        print("{:>12s} : {}".format(num, dic[num]))
    return dic


def adicionar_contacto(dic):
    numero = str(input("Qual o número de telefone? "))
    nome = str(input("Qual é o nome? "))
    dic[numero] = nome
    listContacts(dic)
    return dic


def remover_contacto(dic):
    numero = str(input("Qual o número que deseja remover?"))
    del dic[numero]
    listContacts(dic)
    return dic

def procurar_numero(dic):
        numero = str(input("Qual é o número que deseja ver?"))
        resultado = dic.get(numero,"Não existe nenhum número correspondente")
        if resultado == "Não existe nenhum número correspondente":
            print("Não existe nenhum nome associado ao {}".format(numero))
        else:
            print("\nO nome que corresponde ao número {} é: {}".format(numero,resultado))
            

def filterPartName(dic):
    dicionario_parcial = {}
    nome_parcial = str(input("Insira o nome parcial que deseja pesquisar: "))
    
    for chave, valor in dic.items():
        if nome_parcial in valor:
            dicionario_parcial[chave] = valor
    
    print(dicionario_parcial)
    return dicionario_parcial


def menu():
    """Shows the menu and gets user option."""
    print()
    print("(L)istar contactos")
    print("(A)dicionar contacto")
    print("(R)emover contacto")
    print("Procurar (N)úmero")
    print("Procurar (P)arte do nome")
    print("(T)erminar")
    op = input("opção? ").upper()
    return op


def main():
    """This is the main function containing the main loop."""

    contactos = {"234370200": "Universidade de Aveiro",
        "727392822": "Cristiano Aveiro",
        "387719992": "Maria Matos",
        "887555987": "Marta Maia",
        "876111333": "Carlos Martins",
        "433162999": "Ana Bacalhau"
        }

    op = ""
    while op != "T":
        op = menu()
        if op == "T":
            print("Fim")
        elif op == "L":
            print("Contactos:")
            listContacts(contactos)
        elif op == "A": 
            adicionar_contacto(contactos)
        elif op == "R":
            remover_contacto(contactos)
        elif op == "N":
            procurar_numero(contactos)
        elif op == "P":
            filterPartName(contactos)
        else:
            print("Por favor, insira uma letra válida!")
    

# O programa começa aqui
main()

