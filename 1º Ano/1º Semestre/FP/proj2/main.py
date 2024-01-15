# Grupo: P10G11

import requests
import os
import sys
import time

def limpar_a_tela():                    #Dependendo do sistema operativo o programa vai limpar a tela do terminal
    sistema_operativo = os.name
    if sistema_operativo == 'posix':
        os.system('clear')
    else:
        os.system('cls')

def mensagem_inicial():
    aviao = """
        ______                       .-~~~-.
            _\ _~-\___                                             .- ~ ~-(-------)_ _      
    =  = ==(____TAP____)                                          /                    ~ -.
                \_____\___________________,-~~~~~~~`-.._         |                          ',
                /     o O o o o o O O o o o o o o O o  |\_        \                         .'
     .-~~~-.   `~-.__        ___..----..                  )        ~- ._ ,. ,.,.,., ,.. -~
                    `---~~\___________/------------```````                 '       '
                    =  ===(_________D
                                                .-~~~-.
                    
                      .-~~~--
    .- ~ ~-(-------)_ _
   /                    ~ -.                         ╭━━━━━━━━━━━━━━━━━━━━━╮                      
  |                          ',                        ＢＥＭ  -  ＶＩＮＤＯ        
   \                         .'                      ╰━━━━━━━━━━━━━━━━━━━━━╯         
     ~- ._ ,. ,.,.,., ,.. -~
            '       '
    """
    print(aviao)
    while True:
        clique_ENTER = input("Pronto para programar a sua viagem de sonho?\n"
                            "Vamos fazer algumas perguntas sobre o seu destino e iremos mostrar alguns sítios para visitar.\n"
                            "Pressione ENTER para continuar...")
        if clique_ENTER == "":
            break
        else:
            print("""
Por favor, pressione apenas ENTER para continuar.
                  """)

def fim_do_programa():
    print("""  
__  _
\ `/ |
 \__`!
 / ,' `-.__________________
'-'\_____             TAP  `-.      ╭━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━╮
   <____()-=O=O=O=O=O=[]====--)       ＯＢＲＩＧＡＤＯ ＰＯＲ ＶＩＡＪＡＲ ＣＯＮＮＯＳＣＯ!
     `.___ ,-----,_______...-'      ╰━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━╯
          /    .'
         /   .'
        /  .'         
        `-'                     
                  """)
    
    while True:                                                             #Fazer com que o programa só acabe quando clique somente ENTER
        clique_ENTER2 = input("Pressione ENTER para encerrar o programa")
        if clique_ENTER2 == "":
            sys.exit()                                                      #Programa encerra
        else:
            print("Por favor, pressione apenas ENTER para encerrar o programa.")

def mensagem_enquanto_procura():
    mensagem = "\nPor favor aguarde, estamos a pesquisar atrações que possa achar interessantes"
    print(mensagem, end="", flush=True)
    
    for _ in range(6):
        for _ in range(4):  
            time.sleep(1)
            print(".", end="", flush=True)
        
        print("\b\b\b\b    \b\b\b\b", end="", flush=True)               # Apaga os quatro pontos
        
    print()                                                             # Adiciona uma linha vazia no final 


def obter_dados_localizacao():         #Verificar se os dados estão dentro dos parametros e se o utilizador escreve alguma coisa no input
    while True:
        try:
            latitude_input = input("Qual é a sua latitude ([-90º, 90º])? ").strip()     #O programa vai retirar os espaços em branco
            if not latitude_input:                                                      #Se o utilizador nao colocar a latitude, pede de novo
                print("Insira um valor para a latitude.")
                continue

            latitude = float(latitude_input)
            if not (-90 <= latitude <= 90):
                print("Insira uma latitude válida (entre -90 e 90 graus).")
                continue
        except ValueError:
            print("Insira um valor válido para a latitude.")                            #Caso o utilizador insira caracteres inválidos (a,+,#...)
            continue

        while True:
            try:
                longitude_input = input("Qual é a sua longitude ([-180º, 180º])? ").strip()
                if not longitude_input:
                    print("Insira um valor válido para a longitude.")
                    continue

                longitude = float(longitude_input)
                if not (-180 <= longitude <= 180):
                    print("Insira uma longitude válida (entre -180 e 180 graus).")
                    continue
                break
            except ValueError:
                print("Insira um valor válido para a longitude.")
                continue

        while True:
            try:
                raio = int(input('Quanto é o raio de procura (km)? ')) * 1000
                if raio < 0:
                    print("O raio não pode ser negativo. Insira novamente.")
                    continue
                break
            except ValueError:
                print("Insira um valor para o raio.")

        while True:
            try:
                n_de_atracoes = int(input('Qual é o número de atrações que pretende visitar? '))
                if n_de_atracoes < 0:
                    print("O número de atrações não pode ser negativo. Insira novamente.")
                    continue
                break
            except ValueError:                                  
                print("Insira um valor para o número de atrações.")

        return latitude, longitude, raio, n_de_atracoes

def obter_categorias():                    
    with open('categories.txt', 'r') as file:                                       # Vai ler o ficheiro 'categories.txt'
        allcat = [x.strip() for x in file]                                          # Cria uma lista chamada 'allcat' e vai posssuir as categorias "limpas"

    for p in allcat:
        if not ('.' in p):                                                         #Imprime as categotias que nao possuem o ponto ('.')
            print(p)    

    cat = input('\nCategoria: ').split(',')

    for p, j in enumerate(cat):
        while not (j in allcat):                                                    #Verifica se a categoria que o utilizador escreveu corresponde a alguma já predefenida
            print('A categoria inserida não pertence às categorias disponíveis.')
            j = input('Reescreva a categoria: ')
            cat[p] = j

    return ','.join(cat)

def obter_moeda_pais(name):                            
    response = requests.get(f'https://restcountries.com/v3.1/name/{name}')          #Vai obter a moeda do pais que corresponde as coordenadas 
    data = response.json()
    currency = data[0]['currencies']
    currency = currency[list(currency.keys())[0]]
    return currency['name'] + '(' + currency['symbol'] + ')'

def obter_fuso_horario(city, key):  
    key = 'ac90133019cc42d1badf5710399247ce'                                          # Vai obter o fuso horário do pais 
    response = requests.get(f"https://api.geoapify.com/v1/geocode/search?text={city}&format=json&apiKey={key}")
    data = response.json()
    return data.get('results', [{}])[0].get('timezone', {}).get('offset_STD')

def obter_recursos_web(lat, lon, cat, raio, lim, key):
    key = 'ac90133019cc42d1badf5710399247ce'
    mensagem_enquanto_procura()
    url = f"https://api.geoapify.com/v2/places?categories={cat}&filter=circle:{lon},{lat},{raio}&bias=proximity:{lon},{lat}&limit={lim}&apiKey={key}"

    response = requests.get(url)         # Faz uma solicitação com recurso ao '.get' para a URL já construídaa a cima
    return response.json()               # Devolve os dados da respota em formato 'json'

def organizar_recursos(recursos, dic, key):            
    for p in recursos.get('features', []):
        p = p['properties']
        name = p.get('name')
        if name is None:
            continue

        #Vai extrair as informações importantes

        distance = int(p.get('distance', 0)) / 1000     #Obter a distancia em km
        country = p.get('country')
        longitude = p.get('lon')
        latitude = p.get('lat')
        city = p.get('city')
        time = obter_fuso_horario(city, key) 
        moeda = obter_moeda_pais(country)

        #Vai adicionar as informações extraidas ao dicionário 

        dic['Nome'].append(name)
        dic['País'].append(country)
        dic['Cidade'].append(city)
        dic['Distancia'].append(distance)
        dic['Longitude'].append(longitude)
        dic['Latitude'].append(latitude)
        dic['GMT'].append(time)
        dic['Moeda'].append(moeda)
    return dic

def ordenar_destinos(destinos, criterio):           
    
    if criterio == 'alfabetico':
        return sorted(zip(destinos['Nome'], destinos['País'], destinos['Cidade'], destinos['Distancia'],
                          destinos['Longitude'], destinos['Latitude'], destinos['GMT'], destinos['Moeda']),
                      
                      key=lambda x: x[0])  
    elif criterio == 'distancia':
        return sorted(zip(destinos['Nome'], destinos['País'], destinos['Cidade'], destinos['Distancia'],
                          destinos['Longitude'], destinos['Latitude'], destinos['GMT'], destinos['Moeda']),
                      
                      key=lambda x: x[3])
    elif criterio == 'latitude':
        return sorted(zip(destinos['Nome'], destinos['País'], destinos['Cidade'], destinos['Distancia'],
                          destinos['Longitude'], destinos['Latitude'], destinos['GMT'], destinos['Moeda']),
                      
                      key=lambda x: x[5], reverse=True)
    elif criterio == 'longitude':
        return sorted(zip(destinos['Nome'], destinos['País'], destinos['Cidade'], destinos['Distancia'],
                          destinos['Longitude'], destinos['Latitude'], destinos['GMT'], destinos['Moeda']),
                      
                      key=lambda x: x[4], reverse=True)
    else:
        print("Critério de ordenação inválido.")
        return None

def imprimir_resultados_organizados(atracoes_ordenadas, criterio_ordenacao):
    print("\nResultados Organizados por", criterio_ordenacao, ":")

    for i in range(len(atracoes_ordenadas)):                        #Ordenar as atracoes na vertical 
        atracao = atracoes_ordenadas[i]

        print(f"\nDestino: {atracao[0]}")
        print(f"Pais: {atracao[1]}")
        print(f"Cidade: {atracao[2]}")
        print(f"Distância: {atracao[3]} km")
        print(f"Longitude: {atracao[4]}")
        print(f"Latitude: {atracao[5]}")
        print(f"Fuso Horario: {atracao[6]} GMT")
        print(f"Moeda: {atracao[7]}")
        print("------")

def main():
    while True:
        limpar_a_tela()  
        mensagem_inicial()
        

        lat, long, raio, lim = obter_dados_localizacao()

        recursos = obter_recursos_web(lat, long, obter_categorias(), raio, lim, key)

        destinos = organizar_recursos(recursos, {'Nome': [], 'País': [], 'Cidade': [], 'Distancia': [],
                                                 'Longitude': [], 'Latitude': [], 'GMT': [], 'Moeda': []}, key)

        if len(destinos['Nome']) == 0:                                          
            print('\nNão foram encontradas atrações com essas categorias na área selecionada.')
            procurar_novamente = input("\nDeseja procurar um novo destino? (s/n): ").lower()
            while procurar_novamente not in ['s', 'n']:
                print("Insira apenas 's' para sim ou 'n' para não.")
                procurar_novamente = input("\nDeseja procurar um novo destino? (s/n): ").lower()
            if procurar_novamente == 'n':
                fim_do_programa()
            else:
                main()  # Reinicia o programa

        while True:
            criterio_ordenacao = input("\nEscolha o critério de ordenação (alfabetico, distancia, latitude, longitude): ").lower()

            if criterio_ordenacao in ['alfabetico', 'distancia', 'latitude', 'longitude']:
                break
            else:
                print("Critério de ordenação inválido. Escolha entre 'alfabetico', 'distancia', 'latitude' ou 'longitude'.")
                continue                                    # Adicionado para continuar o loop até que um critério válido seja inserido 

        atracoes_ordenadas = ordenar_destinos(destinos, criterio_ordenacao)

        if atracoes_ordenadas is not None:
            imprimir_resultados_organizados(atracoes_ordenadas, criterio_ordenacao)

        procurar_novamente = input("\nDeseja procurar um novo destino? (s/n): ").lower()

        while procurar_novamente not in ['s', 'n']:                 #Verifica se o utilizador utiliza apenas 's' ou 'n'
            print("Insira apenas 's' para sim ou 'n' para não.")
            procurar_novamente = input("Deseja procurar um novo destino? (s/n): ").lower()
        if procurar_novamente == 'n':
            fim_do_programa()

if __name__ == "__main__":
    key = 'ac90133019cc42d1badf5710399247ce' 
    main()