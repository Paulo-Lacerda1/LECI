##
Success Case: Anonymous compound structural units utilized inline.
Professor Rule 3: "Outro problema é como declarar uma variável desse tipo... já escrever o valor, não causa nenhum problema."
##

type Length [meter, m]: real;
type Time [second, s]: real;

l1: Length;
l2: Length;
t: Time;

l1 := 10 * meter;
l2 := 20 * meter;
t := 4 * second;

# 1. Direct inline printing of an Area calculation (Length * Length) without an 'Area' type
writeln "Area calculated (expected 200 m.m): ", string(l1 * l2);

# 2. Direct inline printing of a Velocity calculation (Length / Time) without a 'Velocity' type
writeln "Velocity calculated (expected 2.5 m/s): ", string(l1 / t);

# 3. Deep inline execution: Compound grouping with a mixture of addition and division
writeln "Average rate (expected 7.5): ", string((l1 + l2) / t);