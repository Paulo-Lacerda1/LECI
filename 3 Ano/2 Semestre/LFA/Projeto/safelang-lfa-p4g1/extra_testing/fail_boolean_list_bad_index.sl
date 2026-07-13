# Expected TypeChecker error: list index must be plain integer
flags := new list[boolean] : list[boolean];

true >> flags;

idx := 1.2: real;

if flags[idx] then
   writeln "bad";
end;
