##
example 2
##

type NMEC [nmec]: integer; # declares a new dimensional integer type -- NMEC -- with nmec as its unity
type Grade [val]: real;    # declares a new dimensional real type -- Grade -- with val as its unity

n: NMEC;
name: string;
g: Grade;

n := integer(read "NMEC: ")*nmec; # multiplication with unity required for dimensional correctness
name := read "Name: ";
g := real(read "Grade: ")*val;
# string(val,len): creates a string with len length containing <val> with right alignment
writeln   format("NMEC:",10),       format("Name",30),  format("Grade:",10); # format(expression, minimum number of columns)
writeln format(string(n),10), format(string(name),30), format(string(g),10);

