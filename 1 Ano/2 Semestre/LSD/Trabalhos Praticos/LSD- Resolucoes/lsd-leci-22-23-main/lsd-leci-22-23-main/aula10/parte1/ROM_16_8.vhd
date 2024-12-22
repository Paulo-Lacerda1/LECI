library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity ROM_16_8 is
	port(address : in std_logic_vector(3 downto 0);
		 dataOut : out std_logic_vector(7 downto 0));
end ROM_16_8;

architecture Behavioral of ROM_16_8 is
	
	subtype TDataWord is std_logic_vector(7 downto 0);
	type TROM is array (0 to 15) of TDataWord;
	constant c_memory: TROM := ("00001010", "00000000", "01101010", "01011000",
								"10001100", "11111111", "10011010", "01011001",
								"10100011", "01011101", "00101100", "01001110",
								"01001101", "01100101", "11010011", "10100111");
	
begin
	dataOut <= c_memory(to_integer(unsigned(address)));
end Behavioral;