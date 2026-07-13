# Expected if BYTE-RANGE RUNTIME ENFORCEMENT exists: RUNTIME FAIL
# Expected in your current implementation: probably PASSES, because Rational/IntegerType does not truly enforce bit ranges yet.

type Int8 : integer[8];

x := 999999 : Int8;

writeln "If this prints, Int8 overflow is not being enforced: ", string(x);
