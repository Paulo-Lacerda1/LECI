## multi-line comment
example 1
##

# variable declaration requires always an explicit type

i :=? integer(read "Integer: ") :integer; # reads a string, attempts to convert it to an integer, aborts program if it fails
# instruction :=? is an assignment attempt that fails if is not possible

write "Integers: ", string(i); # writes expressions to stdout without a newline at the end
write " ; ", string(i//2), " ; ", string(i\\2); # division quocient and remainder
write " ; ", string(i/2);                     # real devision
write " ; ", string(i*(2+i-5));
writeln; # writes a newline

r: real;

r := i; # converts integer to real fraction i/1 (never fails)

writeln "Reals: ", string(r), " ; ", string(r*i), " ; ", string(r*2+(i*r/2)); # operator / is real division (equivalent to multiplication to the inverse)

r := 2/3;
writeln string(r);
r := 1.333; # same as 1333/1000
writeln string(r);

r := 0.3e10; # same as 3/10 * 10000000000 = 30000000000/10 = 3000000000/1
writeln string(r) # ; is a command separator (not required in the last command)

