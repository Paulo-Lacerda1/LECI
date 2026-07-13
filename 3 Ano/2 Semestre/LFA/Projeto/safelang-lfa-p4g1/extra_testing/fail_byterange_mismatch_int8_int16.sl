# Expected: TYPECHECK FAIL
# Int8 and Int16 are both integer-based, but their bit ranges differ.
type Int8 : integer[8];
type Int16 : integer[16];

small := 5 : Int8;
bigger := small : Int16;
