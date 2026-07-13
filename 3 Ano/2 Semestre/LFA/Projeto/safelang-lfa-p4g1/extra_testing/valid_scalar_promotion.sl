##
Success Case: Implicit base promotion inside a dimension.
Professor Rule 2: "Sim, um inteiro é um real (com denominador 1)."
##

type Length [meter, m]: real;
type Time [second, s]: real;
type Velocity := Length/Time: real;

l1: Length;
l2: Length;
t1: Time;
t2: Time;
v: Velocity;
scalar: integer;

t1 := 5s;
t2 := 7s;

l1 := 4.5 * meter;
scalar := 2;

# 1. Multiplication by a literal integer (must promote to real under the hood)
l2 := l1 * 2;
writeln "Expected 9m: ", string(l2);

# 2. Multiplication by an integer variable 
l2 := l1 * scalar;
writeln "Expected 9m: ", string(l2);

# 3. Division by a literal integer
l2 := l1 / 2;
writeln "Expected 2.25m: ", string(l2);

v := l1/t1;

writeln "velocity testing: ", string((v*v) + ((l1*l1)/(t1*t1)));