def load_nems(filename):
    lista_apelidos ={}
    with open( "C:\\Users\\paulo\\OneDrive\\Ambiente de Trabalho\\UA\\FP\aula08\\names.txt", "r") as file:
        for line in file:
            line = line.strip()
            name, apelido = line.strip().split()
            name_dict[name] = alias
        return name_dict

