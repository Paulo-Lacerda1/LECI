# Expected: TYPECHECK FAIL
# Cannot assign a string to a byte-ranged integer type.
type Int8 : integer[8];

bad := "hello" : Int8;
