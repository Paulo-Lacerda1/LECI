## 
Error Case: Structural dimension assignment without matching nominal declaration.
Professor Rule 3: "não existindo uma dimensão definida para esse valor, tal operação não será possível."
##

type Length [meter, m]: real;
type Time [second, s]: real;

l: Length;
t: Time;
v: Length; # Intentionally declaring an incompatible type

l := 10 * meter;
t := 2 * second;

# l / t evaluates structurally to (Length / Time). 
# Since we haven't declared "type Velocity := Length/Time: real;", 
# storing it in a variable (even an existing dimensional one like Length) must be statically rejected.
v := l / t;