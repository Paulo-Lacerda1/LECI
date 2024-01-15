AUTORES = ['DAS ILHAS']
import requests
#import json

def apiKey():
    print("PROGRAMA PARA CONHECER PONTOS TURÍSTICOS\n ")
    print("Quer utilizar sua apiKey? ")
    while True:
        opcao = str(input("Insira (S) para Sim ou (N) para Não: "))
        opcao = opcao.upper()
        if opcao == "S":
            apiKey = input("Digite sua api: ")
            return apiKey
        elif opcao == "N":
            apikey = 'b7a8d980b3784fa79306383aee3ae2df'
            return apikey
        else:
            print("OPÇÃO INVÁLIDA.")

def obterAtracoes(latitude, longitude, raio, categorias, resultados, api):
    url = f"https://api.geoapify.com/v2/places?categories={categorias}&filter=circle:{longitude},{latitude},{raio}&bias=proximity:{longitude},{latitude}&limit={resultados}&apiKey={api}"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print("Erro ao obter os dados das atrações.")
        return None
    
def mostrarInformacoes(atracoesObtidas):
    if atracoesObtidas and len(atracoesObtidas['features']) > 0:
        for i in range(len(atracoesObtidas['features'])):
            nome = atracoesObtidas['features'][i]['properties'].get('name', 'Nome não disponível')
            pais = atracoesObtidas['features'][i]['properties'].get('country', 'Pais não disponível')
            coordenadas = atracoesObtidas['features'][i]['geometry']['coordinates']
            latitude = coordenadas[1]
            longitude = coordenadas[0]
            distancia = atracoesObtidas['features'][i]['properties']['distance']
            cidade = atracoesObtidas['features'][i]['properties'].get('city', 'Cidade não disponível')
            telefone = atracoesObtidas['features'][i]['properties'].get('phone', 'Telemóvel não disponível')
            codigoPostal = atracoesObtidas['features'][i]['properties'].get('postcode', 'Código Postal não disponível')

            print(f"Nome: {nome}")
            print(f"País: {pais}")
            print(f"Localização (Latitude, Longitude): ({latitude}, {longitude})")
            print(f"Distância á localização de partida: {distancia/1000} kilómetros")
            print(f"Cidade: {cidade}")
            print(f"Número de telemóvel: {telefone}")
            print(f"Código Postal: {codigoPostal}")
            print("\n")
    else:
        print("Nenhuma atração encontrada ou as informações fornecidas não retornaram resultados.")
        
#verifica se as categorias coincidem com as categorias do texto
def verificar_categorias_validas(categorias, categorias_disponiveis):
    categorias_inseridas = categorias.split(',')
    for categoria in categorias_inseridas:
        if categoria.strip() not in categorias_disponiveis:
            return False
    return True



def main():
    with open('categories.txt', 'r') as file:
        categorias_disponiveis = file.read().splitlines()

    api = apiKey()
    latitude = float(input("\nDigite a latitude de partida: "))
    longitude = float(input("Digite a longitude de partida: "))
    raio = int(input("Digite o raio de busca em kilómetros: "))
    raio = raio*1000

    while True:
        categorias = input("Digite as categorias desejadas separadas por vírgula: ")
        if verificar_categorias_validas(categorias, categorias_disponiveis):
            break
        else:
            print("Categorias inválidas. Por favor, insira categorias válidas.")

    resultados = int(input("Digite a quantidade de resultados desejados: "))
    print("\n")

    atracoesObtidas = obterAtracoes(latitude, longitude, raio, categorias, resultados, api)
    mostrarInformacoes(atracoesObtidas)

if __name__ == "__main__":
    main()