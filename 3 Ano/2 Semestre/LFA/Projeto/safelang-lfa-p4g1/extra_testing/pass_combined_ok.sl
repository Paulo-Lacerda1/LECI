# Combined test: for + boolean literals + boolean lists + assert
flags := new list[boolean] : list[boolean];

true >> flags;
false >> flags;
true >> flags;

true_count := 0: integer;

for i := 1 to 4 do
   if flags[i] then
      true_count := true_count + 1;
   end;
end;

assert true_count >= 2;
writeln "true count = ", string(true_count);
