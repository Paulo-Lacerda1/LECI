def inputFloatList():
    lst = []
    introduzido = input("O que deseja adicionar?")
    while introduzido != "":
        lst.append(float(introduzido))
        introduzido = input("O que deseja adicionar?")
    print(lst)
    return lst

inputList = inputFloatList()
v = 10

def countLower(lst, v):
    lst2 = []
    for i in lst:
        if i < v:
            lst2.append(i)
    print("Esta é a lista dos números inferiores a 10: {}".format(lst2))
    print("O número de elementos dessa lista é: {}.".format(len(lst2)))

countLower(inputList, v)

def minmax(lst):
    if not lst:
        print("A lista está vazia.")
        return None, None

    min_val = max_val = lst[0]

    for num in lst:
        if num < min_val:
            min_val = num
        elif num > max_val:
            max_val = num

    return min_val, max_val

result = minmax(inputList)

if result is not None:
    min_val, max_val = result
    print("O valor máximo é {}".format(max_val))
    print("O valor mínimo é {}".format(min_val))
