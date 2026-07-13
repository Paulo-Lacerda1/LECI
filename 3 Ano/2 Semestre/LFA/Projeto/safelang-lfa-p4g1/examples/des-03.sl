# line comment
N := integer(read "Number of primes: ") : integer;

try
   if N <= 1 then
      fail # goes to rescue (or fails if none)
      # alternatively, if inside a try, a retry instruction could be used
   end
rescue
   writeln "ERROR: invalid number";
   retry
end;

assert N >= 2; # fails if predicate is false

# all variables have a default value (integer: 0; real: 0/1; string: ""; boolean: false; ...)

i := N: integer;
is_prime := i = 2: boolean; # operators = (equal), <> (not-equal)
until is_prime do
   # is i (> 2) a prime number?
   d := 3: integer;
   is_prime := i \\ 2 <> 0; # not an even number
   while d*d <= i and is_prime do
      is_prime := i \\ d <> 0;
      d := d + 2;
   end;
   if not is_prime then
      i := i + 1; # try next number
   end
end;

writeln "The first prime number >= ", string(N), " is ", string(i); 

