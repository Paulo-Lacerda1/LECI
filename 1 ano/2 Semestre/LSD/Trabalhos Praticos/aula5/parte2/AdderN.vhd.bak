library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity AdderN is
	generic (N : positive := 4);
	port (operand0, operand1 : in  std_logic_vector(N-1 downto 0);
		  result             : out std_logic_vector(N-1 downto 0)
	);
end AdderN;

architecture Behavioral of AdderN is
	signal uns_result : unsigned(3 downto 0);
begin
	uns_result <= unsigned(operand0) + unsigned(operand1);
	result <= std_logic_vector(uns_result);
end Behavioral;
