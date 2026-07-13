# Expected runtime error if assert generates a RuntimeException check
x := 1: integer;
assert x >= 2;
writeln "this should not print";
