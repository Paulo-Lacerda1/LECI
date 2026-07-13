# Expected: PASS
# Basic byte-ranged real type.
type Real32 : real[32];

r := 3.5 : Real32;

writeln "Real32 value = ", string(r);
