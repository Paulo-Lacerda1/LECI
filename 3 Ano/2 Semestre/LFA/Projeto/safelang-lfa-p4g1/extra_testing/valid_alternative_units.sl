##
Success Case: Alternative units handling and normalization.
##

type Length [meter, m]: real;

# Secondary unit mapping with a custom conversion literal
unit Length [inch, in] := 0.0254 * meter;

l: Length;

# The compiler normalizes this variable immediately to the primary base unit (meters)
# by running the conversion factor against the value.
l := 100 * inch;

# The target output prints out the fully accurate, normalized unit value
writeln "100 inches in meters (expected 2.54m): ", string(l);