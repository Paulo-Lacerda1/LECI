#Convertor de km para milhas#

km = float(input("Qual é a distancia em kilometros?"))

m = "Metros"

#1 milha corresponde a 1.609 km

q=float(0.62137)

m= km*q

print( km , "km corresponde a" , m , "milhas, sendo o quociente entre as duas distancias", q, )

#Conversor de temperatura#

temp = float(input("Qual é a temperatura em graus celsius?"))

far = "fahrenheit"

#Para calcular a temperatura em Fahrenheit
#multiplica se a temperatura em Cº por 1.8 e soma-se 32 ao resultado#

far = temp * 1.8 + 32

print(temp, "graus Celsius" "corresponde a", far, "Fahrenheit")

#Conversor de horas para segundos#
 
h = float(input("Quantas horas queres converter?"))

seg = "segundos"

seg = h * 60**2

print(h, "horas", "são" , seg , "segundos")

