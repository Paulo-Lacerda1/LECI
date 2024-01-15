import requests
import csv

#Função para verificar se é um float
def is_float(s):
    try:
        float(s)
        return True
    except ValueError:
        return False

# Função para obter a localização de partida do utilizador
def obter_localizacao_inicial(): 
    while True:
        coordenadas=input("Insira a latitude e longitude separado por vírgulas: ").split(",")
        if len(coordenadas)!=2:
            print("Coordenadas inválidas")
            continue
        if is_float(coordenadas[0]) == False or is_float(coordenadas[1]) == False:
            print("Coordenadas inválidas")
            continue

        latitude=float(coordenadas[0])
        longitude=float(coordenadas[1])
        if ((-90.00<=latitude<=90.00) == False) or ((-180.00<=longitude<=180.00) == False):
            print("Coordenadas inválidas")
            continue
        break
    return latitude,longitude
    
    

# Função para obter informações básicas sobre a viagem
def obter_informacoes_viagem():
    while True:
        distancia_maxima = float(input("Insira a distância máxima de viagem (em km): "))
        if distancia_maxima<=0:
            print("Distancia inválida")
            continue
        break
    categorias_escolhidas=(input("Insira as categorias de atrações separadas por vírgulas: ")).split(",")

    return distancia_maxima, categorias_escolhidas

# Função para obter informações sobre atrações da API Geoapify
def obter_informacoes_atracoes(api_key, lat, lon, raio, categorias):
    atracoes = []

    categorias_string=",".join(categorias)
    
    url = f"https://api.geoapify.com/v2/places?categories={categorias_string}&lat={lat}&lon={lon}&radius={raio}&apiKey={api_key}"
    resposta = requests.get(url)
    dados = resposta.json()
    atracoes=dados.get("features", [])

    return atracoes

# Função para processar atrações e ordenar
def processar_informacoes(atracoes):
    resultado=[]
    for atracao in atracoes:        
        
        nome = atracao.get("properties", {}).get("name", "N/A")
        if (nome == "N/A"):
            continue
        
        pais = atracao.get("properties", {}).get("country", "N/A")
        localizacao = atracao.get("geometry", {}).get("coordinates", [])
        morada = atracao.get("properties", {}).get("address_line2", "N/A")
        nome_categoria = atracao.get("properties", {}).get("categories", "N/A")
        distancia = atracao.get("properties", {}).get("distance", "N/A")
        
        resultado.append({"nome":nome,
                          "pais":pais,
                          "localizacao":localizacao,
                          "morada":morada,
                          "categorias":nome_categoria,
                          "distancia":distancia})
        
    resultado_ordenado = sorted(resultado, key=lambda x: x["distancia"])
    return resultado_ordenado

# Função para mostrar os espaços da tabela
def mostrar_espacos(texto,n_caracteres):
    texto=str(texto)
    return ' ' * (n_caracteres-len(texto))

# Função para mostrar as informações no ecrã
def mostrar_informacoes(resultado):
    print(f"Nome {mostrar_espacos('Nome',30)} |\
            País {mostrar_espacos('País',10)} |\
            Localização {mostrar_espacos('Localização',20)} |\
            Distância(km) {mostrar_espacos('Distância',10)}|\
            Morada{mostrar_espacos('Morada',50)} |\
            Categorias")
    print("----------------------------------------------------------------")
    for item in resultado:

        print(f"{item['nome']}{mostrar_espacos(item['nome'],30)} |\
                {item['pais']}{mostrar_espacos(item['pais'],20)} |\
                {item['localizacao']}{mostrar_espacos(item['localizacao'],20)} |\
                {item['distancia']} km{mostrar_espacos(item['distancia'],10)} |\
                {item['morada']}{mostrar_espacos(item['morada'],50)} |\
                {(',').join(item['categorias'])}\n")
    
    print("Foram encontradas ",len(resultado),"!")
    if len(resultado)>0:
        print("A atração mais perto das coordenadas é",resultado[0]["nome"])

# Função para exportar dados para um ficheiro CSV
def exportar_para_csv(resultado):
    with open("atracoes.csv", "w", newline="", encoding="utf-8") as arquivo_csv:
        escritor_csv = csv.writer(arquivo_csv)
        escritor_csv.writerow(["Nome", "País", "Latitude" , "Longitude", "Distância à Localização de Partida"])

        for item in resultado:
            escritor_csv.writerow([item["nome"], item["pais"], item["localizacao"][0],item["localizacao"][1],f"{item['distancia']:.2f} km"])


# main
def main():
    api_key = "13ad4f2c5b62425ba8d667f3bda122f7"
    localizacao_partida = obter_localizacao_inicial()
    distancia_maxima, categorias_escolhidas = obter_informacoes_viagem()

    atracoes = obter_informacoes_atracoes(api_key, localizacao_partida[0], localizacao_partida[1], distancia_maxima, categorias_escolhidas)
    resultado=processar_informacoes(atracoes)
    mostrar_informacoes(resultado)

    exportar_para_csv(resultado)

if __name__ == "__main__":
    main()
