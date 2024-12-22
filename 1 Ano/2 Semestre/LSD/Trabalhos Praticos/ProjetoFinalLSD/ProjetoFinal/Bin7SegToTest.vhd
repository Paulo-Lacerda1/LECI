library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Bin7SegToTest is
	port(binInput	: in  std_logic_vector(3 downto 0);
		  enable		: in std_logic;
		  decOut_n	: out std_logic_vector(6 downto 0));
end Bin7SegToTest;

architecture Behavioral of Bin7SegToTest is	
begin
	decOut_n <= "1111111" when(enable   = '0')    else
					"0000111" when(binInput = "0001") else
					"0000110" when(binInput = "0010") else
					"0010010" when(binInput = "0011") else
					"0000111" when(binInput = "0100") else
					"1000000";
end Behavioral;
