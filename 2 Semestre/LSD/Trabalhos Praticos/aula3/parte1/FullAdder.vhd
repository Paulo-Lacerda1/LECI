library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity FullAdder is
	port(a, b, cin : in  std_logic;
		 s, cout   : out std_logic
	);
end FullAdder;

architecture Behavioural of FullAdder is
begin
	s    <= (a xor b) xor cin;
	cout <= ((a xor b) and cin) or (a and b);
end Behavioural;
