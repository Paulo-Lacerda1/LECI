##
Error Case: Additive operations between completely different dimensions.
##

type Length [meter, m]: real;
type Time [second, s]: real;

l: Length;
t: Time;
bad: Length;

l := 5 * meter;
t := 1 * second;

# Semantic Error: Addition/Subtraction requires strict structural dimension equivalence
bad := l + t;