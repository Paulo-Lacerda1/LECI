# Expected: PASS
# Basic byte-ranged integer type.
# Note: this only checks the type system, not runtime overflow.
type Int8 : integer[8];

a := 42 : Int8;

writeln "Int8 value = ", string(a);
