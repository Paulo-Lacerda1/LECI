# Esperado: erro semantico, nao se pode atribuir list[string] a list[integer]
nums := new list[integer] : list[integer];
texts := new list[string] : list[string];
nums := texts;
