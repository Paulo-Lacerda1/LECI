# Expected: TYPECHECK PASS, RUNTIME FAIL
# Uses a byte-ranged integer type, then triggers runtime division by zero.

type Int8 : integer[8];

a := 10 : Int8;
b := 0 : Int8;

result := a // b : Int8;

writeln "This should not print: ", string(result);
