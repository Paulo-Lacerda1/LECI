    import requests
    import os

    def lerFicheiro():                                                  # Lê o ficheiro das categorias.
        categories = {}
        with open('categories.txt', 'r') as file:
            for line in file:
                category = line.strip()
                categories[category] = category.split('.')[-1]
        return categories

    def lat():                                                     # Recebe uma latitude válida:
        while True:                                                     
            try:
                latitude = float(input("Enter your latitude: "))
                if -90 <= latitude <= 90:
                    break
                else:
                    print('Error: Please enter a number between -180 and 180.')
            except ValueError:
                print('Error: Please enter a number')
        return latitude

    def lon():                                                    # Recebe uma Longitude válida:
        while True:                                                     
            try:
                longitude = float(input("Enter your longitude: "))
                if -180 <= longitude <= 180:
                    break
                else:
                    print('Error: Please enter a number between -90 and 90.')
            except ValueError:
                print('Error: Please enter a number')
        return longitude

    def distance():                                                     # Recebe uma Distancia válida:
        while True:                                                     
            try:
                distanceMax = float(input("Enter max travel distance (in kms): "))
                if distanceMax >= 0:                                    # Assegurar-se que a distância é um nº positivo
                    break
                else:
                    print('Error: Please enter a positive distance')
            except ValueError:
                print('Error: Please enter a number')
        return distanceMax

    def selectedCat(allCategories):
        Denied = True
        while Denied:                                                   
            selectedCategories = input("Enter attraction categories (comma-separated, no-spaces): ").lower().split(',') # Recebe a ou as categorias desejadas
            Denied = False                                              
            for i in selectedCategories:
                if i not in allCategories.keys():                     # Testa se é/são válidas
                    print(f'Error: {i} is not a valid attraction')
                    Denied=True
            if Denied == True:                                          # Se alguma não for, mostra a lista de categorias válidas (opcionalmente)
                openTxt = input('Want to know all the available attractions?\nType "Y" to open the text-file: ').upper()
                if openTxt == 'Y':                                      
                    try:                                                # Abre o ficheiro categories.txt no notepad(windows):
                        ficheiro = 'categories.txt'
                        os.system(f'open "{ficheiro}"' if os.name == 'posix' else f'start notepad "{ficheiro}"') # Verifica o OS do utilizador para saber qual código executar
                    except Exception as e:
                        print(f"An error occurred: {e}")
        return selectedCategories

    def userInput(allCategories):                                       # Recebe os dados do utilizador:
        latitude = lat()
        longitude = lon()
        distanceMax = distance()
        selectedCategories = selectedCat(allCategories)
        return (latitude, longitude, distanceMax, selectedCategories)

    def perguntaMoeda():                                    # Pergunta se o utilizador faz questão de saber a currency.
        print('Want to know the currency? (Y/N)')           # Escolher sim pode afetar ligeiramente o desempenho por ter de ir buscar dados a outra API.
        while True:
            resposta = input('Resposta:').upper()
            if resposta == 'Y':
                resp_bool = True
                break
            elif resposta == 'N':
                resp_bool = False
                break
            else:
                print('Error: Answer with "Y" or "N"')
        return resp_bool
        
    def implementData(userInput):                                       # Distribui todos os dados num dicionário
        dados = {}
        dados['lat'] = userInput[0]
        dados['lon'] = userInput[1]
        dados['radius'] = userInput[2]
        dados['categories'] = ','.join(userInput[3])
        dados['apiKey'] = 'cd2e618bc0784016b18df5a05e1c0de8'
        return (dados)

    def countryCurrency(nome_pais):                         #Indica qual a moeda usada no local
        try:
            resposta = requests.get(f'https://restcountries.com/v2/name/{nome_pais}?fullText=true') # Link para API com informações do país

            dadosCountry = resposta.json()                         # Cria uma variável com os dados recolhidos da API
            
            # Extraia a moeda do primeiro resultado
            moeda = dadosCountry[0]['currencies'][0]['code']       # Vai buscar o código da moeda à variável

            return moeda

        except requests.exceptions.RequestException as e:   # Exceção para se não houver dados sobre o país
            print(f"Error obtaining info: {e}")
            return None

    def endFeedback(soma, total):                                       # Devolve os detalhes (feedback) da pesquisa
        if total > 0:                                                
            media = (soma) / total
            print(f"\nTotal attractions: {total}\nAverage distance: {round(media, 3)} km")
        else:                                                         
            print("     Nowhere...\n-------------------------\nIt seems like there aren't any atractions that meet your criteria") 

    def attractionProperties(allAttractions, maxDist, querMoeda):       # Analisa todas as atrações
        tot_attrac = 0
        som_dist = 0
        print('-------------------------\n   You can go to:   \n-------------------------')
        for attraction in allAttractions:                               # 
            name = attraction['properties'].get('name', 'no info')
            distance = attraction['properties'].get('distance', 'no info')/1000
            if distance < maxDist and name != 'no info':                # Se atração corresponder com a pesquisa, ela é mostrada
                mostrarAttr(name, attraction, distance, querMoeda)      # Função para mostrar a atração
                tot_attrac +=1
                som_dist += distance
        return (som_dist, tot_attrac)  

    def mostrarAttr(name, attraction, distance, querMoeda):             # Apresenta as atrações correspondentes
        categories = attraction['properties'].get('categories', [])     # Lista com as categorias da atração
        subCategoriesList = []                                          # Recolhe todas as categorias e subcategorias correspondentes à atração
        for item in categories:                                         # Cria uma lista com a ultima palavra de cada categoria
            subCategory = item.split('.')[-1]
            if subCategory not in subCategoriesList:
                subCategoriesList.append(subCategory)
        country = attraction['properties'].get('country', 'no info')    # Recebe o nome do país
        city = attraction['properties'].get('city', 'no info')          # Recebe o nome da cidade
        location = attraction['geometry'].get('coordinates', [])        # Recebe as coordenadas
        street = attraction['properties'].get('street', 'no info')      # Recebe a rua
        print(f"Name: {name}\nCountry: {country}\nCity: {city}\nStreet: {street}\nLocation: {location}\nDistance from you: {distance} Km\nAttraction type(s): {subCategoriesList}")
        if querMoeda:
            moeda = countryCurrency(country)
            print(f"Currency: {moeda}")
        print('-------------------------')

    def main():
        allCategories = lerFicheiro()                                                   # Lê todas as categorias 

        user_input = userInput(allCategories)                                           # Pede os dados ao utilizador
        querMoeda = perguntaMoeda()                                                     # Confirma se saber a moeda é relevante?
    
        dados = implementData(user_input)                                               # Criação de um dicionário com todos os dados para organização
        maxDist = dados['radius']
    
        response = requests.get('https://api.geoapify.com/v2/places', params=dados)     # Chamamento da API        
        data = response.json()                                                          # Organiza a API na estrotura JSON para ser utilizada
        allAttractions = data.get('features', [])                                       # Busca todas as Atrações da API considerando as coordenadas e as categorias escolhidas
    
        (som_dist, tot_attrac) = attractionProperties(allAttractions, maxDist, querMoeda)                        # Extrai as propriedades das atrações que correspondem à pesquisa e apresenta-as.
        endFeedback(som_dist, tot_attrac)                                               # Apresenta um breve feedback no final da pesquisa

    main()
