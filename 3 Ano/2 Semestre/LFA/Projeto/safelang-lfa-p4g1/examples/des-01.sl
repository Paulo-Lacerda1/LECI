## multi-line comment
- Integers have infinite precision (BigInteger representation in Java)
- Real numbers are always represented by a fraction of integers (no divide by zero error)
- The programmer may choose to represent integer (and reals) with a finite number of bits (not digits, bits!)
##

type Int8: integer[8]; # integer represented by 8 bits
type Int32: integer[32]; # integer represented by 32 bits

i0: integer; # infinite precision!
i0 := 3;

r0: real; # infinite precision!
r0 := 2/3;
r0 := 1.333; # same as 1333/1000

i1 := 10: Int8;
i2 := i1: Int32; # safe

# i1 := i2; # REJECTED at compile time because it may fail (overflow error)
try
   i1 := i2 # ACCEPTED by compiler (inside try), same as i1 :=? i2;
end;

try
   i1 := i2; # ACCEPTED by compiler (inside try)
rescue
   writeln("ERROR: overflow!");
   # fails propagated to possible enclosing try; if none, aborts program with a non-zero value
end;

##
Safe exception scheme, not possible to (ever) inadvertently ignore a failure
##
try
   i1 := i2;
rescue
   i2 := Int8(i2);
   retry # "jumps" to enclosing try; ideally it should only apply if the state of the program in rescue has changed (to avoid infinite cycle).  
end;

n: integer;
try
   n := integer(read "Number");
rescue
   writeln("ERROR: invalid integer number!");
   retry;
end;
writeln string(n);

type Real64: real[64];
r: real;
r := 12.5; # internally is represented by fraction 125/10;
r2: Real64;

r2 := 1.4e10; # internally is represented by fraction 14*10^9/1 # SAFE: requires 34 bits (trunc(log2(1.4*10^9)) + 1)!
r2 := 1.4e-10; # internally is represented by fraction 14/(10^11) # SAFE: required 37 bits (trunc(log2(10^11)) + 1)!

writeln string(r2);
