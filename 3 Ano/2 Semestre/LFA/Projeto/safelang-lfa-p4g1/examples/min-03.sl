use "physics.sl"; # Processed in compile time!

l: Length;
t: Time;
v: Velocity;

l := 10*meter; # l:=10 is a semantic (dimensional) error
t := 2*second;
v := l/t;      # t/l is a semantic (dimensional) error
writeln "Velocity: ", string(v); # Result: "Velocity: 5m/s"

l :=? real(read "Distance: ")*meter; # real() may fail, :=? "catches" the fail and aborts program with an error message ([line 12] ERROR: unable to convert string to real)
t := real(read "Time: ")*second; # read() may fail, program is aborted with exception 
v := l/t;
writeln "Velocity: ", string(v);

