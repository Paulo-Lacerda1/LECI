# Tests for-loop without explicit loop variable
count := 0: integer;

for 1 to 4 do
   count := count + 1;
end;

writeln "count = ", string(count);
