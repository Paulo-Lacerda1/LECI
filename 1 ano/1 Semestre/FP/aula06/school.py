# Complete o programa!
import csv

# a)
def loadFile(school1, dados):
    dados = []

    with open(school1, 'r') as arquivo_csv:
        leitor_csv = csv.reader(arquivo_csv)

        # Lê cada linha do arquivo e adiciona à lista de dados
        for linha in leitor_csv:
            dados.append(linha)

    # Organiza os dados, por exemplo, ordenando por uma coluna (índice 0 neste exemplo)
    dados_organizados = sorted(dados, key=lambda x: x[0])

    return dados_organizados
    
# b) Crie a função notaFinal aqui...
...

# c) Crie a função printPauta aqui...
...

# d)
def main():
    lst = []
    # ler os ficheiros
    loadFile("school1.csv", lst)
    loadFile("school2.csv", lst)
    loadFile("school3.csv", lst)
    
    # ordenar a lista
    ...
    
    # mostrar a pauta
    ...


# Call main function
if __name__ == "__main__":
    main()


