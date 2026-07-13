# Tests for-loop with explicit loop variable
sum := 0: integer;

for i := 1 to 4 do
   writeln "i = ", string(i);
   sum := sum + i;
end;

writeln "sum = ", string(sum);
