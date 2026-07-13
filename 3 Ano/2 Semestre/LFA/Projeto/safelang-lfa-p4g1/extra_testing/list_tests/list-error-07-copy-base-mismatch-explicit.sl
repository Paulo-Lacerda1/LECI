# Esperado: erro semantico, list[integer] nao pode ser declarada como list[string]
nums := new list[integer] : list[integer];
texts := nums : list[string];
