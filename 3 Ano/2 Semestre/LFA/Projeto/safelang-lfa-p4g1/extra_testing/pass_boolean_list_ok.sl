# Tests boolean list indexing with literal and variable index
flags := new list[boolean] : list[boolean];

true >> flags;
false >> flags;

if flags[1] then
   writeln "first flag is true";
end;

idx := 2: integer;

if flags[idx] = false then
   writeln "second flag is false";
end;
