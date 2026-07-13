# Esperado: erro semantico, indice de lista tem de ser integer simples
nums := new list[integer] : list[integer];
1 >> nums;
idx := 1.5 : real;
writeln string(nums[idx]);
