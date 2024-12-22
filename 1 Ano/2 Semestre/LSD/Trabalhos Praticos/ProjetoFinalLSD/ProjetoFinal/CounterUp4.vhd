library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CounterUp4 is
	port(clk,reset,enable : in std_logic;
		  count : out std_logic_vector(12 downto 0));
end CounterUp4;

architecture Behavioral of CounterUp4 is
	signal s_count : unsigned(12 downto 0);
	constant s_max 	: std_logic_vector(12 downto 0) := "1111101000000";
	constant s_min 	: std_logic_vector(12 downto 0) := "0000000010000";
begin
	process(clk)
	begin
	if (rising_edge(clk)) then
		if reset ='1' then
			s_count <= unsigned(s_min);
		elsif enable='1' then
			if (s_count >= unsigned(s_max)-1) then
				s_count <= unsigned(s_min);
			else
				s_count <= s_count +1;
			end if;
		end if;
	end if;
	end process;
	count <= std_logic_vector(s_count);
end Behavioral;