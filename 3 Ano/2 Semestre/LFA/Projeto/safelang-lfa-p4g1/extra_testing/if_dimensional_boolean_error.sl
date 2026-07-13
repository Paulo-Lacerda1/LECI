# Test 3: Negative case - Dimensional safety breakdown inside conditional blocks

# Let's assume these dimensions are configured inside your TypeSystem
type Length [meter, m]: real;
type Time [second, s]: real;

len: Length;
duration: Time;

len := 5.5 * meter;
duration := 2.0 * second;

# SEMANTIC ERROR 1: Comparing completely incompatible physical dimensions (Length vs Time)
if len > duration then
    writeln "Error: Cannot compare meters to seconds!";
end;

# SEMANTIC ERROR 2: Comparing a dimensional unit directly to an adimensional raw primitive
if len = 5.5 then
    writeln "Error: Cannot compare 5.5 meters to an adimensional 5.5 scalar";
end;