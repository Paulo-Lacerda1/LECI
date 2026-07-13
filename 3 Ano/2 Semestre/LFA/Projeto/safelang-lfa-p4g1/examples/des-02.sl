# line comment

use "grades.sl"; # Processed in compile time!

N := 4: integer;
nmecs := new list[NMEC]: list[NMEC]; # unbounded dynamic list of NMEC (first element has index 1)
names := new list[string]: list[string];
grades := new list[Grade]: list[Grade];
i: integer;

for i := 1 to N do 
   try
      integer(read "NMEC: ")*nmec >> nmecs; # add element to the end of list
   rescue
      writeln "ERROR: invalid NMEC";
      retry;
   end;
   read "Name: " >> names;
   try
      real(read "Grade: ")*val >> grades;
   rescue
      writeln "ERROR: invalid grade";
      retry;
   end
end;

writeln format("NMEC:",10, "center"), format("Name",30, "center"), format("Grade:",10, "center");
for i := 1 to length(nmecs) do 
   writeln format(string(nmecs[i]),10, "left"), format(names[i],30, "left"), format(string(grades[i]),10, "right")
end

