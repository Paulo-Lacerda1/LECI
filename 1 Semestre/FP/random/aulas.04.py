def fatorial(n):
    resultado = 1
    for i in range(1, n + 1):
        resultado *= i
    return resultado

# Obter entrada do usuário
numero = int(input("Digite um número para calcular o fatorial: "))

# Calcular e imprimir o fatorial
resultado_fatorial = fatorial(numero)
print(f'O fatorial de {numero} é {resultado_fatorial}')