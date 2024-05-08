texto = "É um facto estabelecido de que um leitor é distraído pelo conteúdo legível de uma página quando analisa a sua mancha gráfica. Logo, o uso de Lorem Ipsum leva a uma distribuição mais ou menos normal de letras, ao contrário do uso de \"Conteúdo aqui, conteúdo aqui\", tornando-o texto legível. Muitas ferramentas de publicação electr"

# Dividir o texto em palavras
lista_de_palavras = texto.split()

# Exibir a lista de palavras
#print(lista_de_palavras)
lst=[]
for palavra in lista_de_palavras:
    if "a" in palavra:
        lst.append(palavra)
    if not lst:
        print("nada")

print(lst)
        
"""palavras_com_a = []

# Iterar sobre cada palavra na lista
for palavra in lista_de_palavras:
    if "a" in palavra:
        palavras_com_a.append(palavra)

# Exibir a lista de palavras que contêm "a"
print(palavras_com_a)
"""