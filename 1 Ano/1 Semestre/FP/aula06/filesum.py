#from tkinter import filedialog

def main():
    # 1) Pedir nome do ficheiro (experimente cada alternativa):
    name = filedialog.askopenfilename(title="nums.txt")
    
    # 2) Calcular soma dos números no ficheiro:
    total = fileSum(name)
    
    # 3) Mostrar a soma:
    print("Sum:", total)


def fileSum(filename):
    with open("nums.txt", "r") as fileobj:
        for i in fileobj:
            soma= i[0]+i[len(filename)]
    print(soma)


if __name__ == "__main__":
    main()

