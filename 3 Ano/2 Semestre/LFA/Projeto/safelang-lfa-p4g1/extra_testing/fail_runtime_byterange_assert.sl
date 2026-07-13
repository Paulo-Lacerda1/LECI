# Expected: TYPECHECK PASS, RUNTIME FAIL
# Uses a byte-ranged integer type, then fails an assertion at runtime.

type Int8 : integer[8];

x := 200 : Int8;

assert x <= 127;

writeln "This should not print: ", string(x);
