## 
Error Case: Assigning dimensional values to an adimensional primitive.
Professor Rule 1: "l := 5 * meter : real; não é válido e deve ser estaticamente rejeitado."
##

type Length [meter, m]: real;

l: real;          # Adimensional primitive
lenVar: Length;

lenVar := 10 * meter;

# Should fail: A dimensional type cannot collapse implicitly into a pure primitive
l := lenVar; 

# Should fail: Explicit base casting does not strip the physical quantity/unit
l := 5 * meter : real;