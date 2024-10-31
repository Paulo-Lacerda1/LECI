library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CounterForDisplay is
	port(clk,reset,enable : in std_logic;
		  count : out std_logic_vector(12 downto 0));
end CounterForDisplay;

architecture Behavioral of CounterForDisplay is
	signal s_count : unsigned(12 downto 0);
	constant s_max 	: std_logic_vector(12 downto 0) := "1111101000000";
begin
	process(clk)
	begin
	if (rising_edge(clk)) then
		if reset ='1' then
			s_count <= (others => '0');
		elsif (enable='1') then
			if (s_count >= unsigned(s_max)-1) then
				s_count <= unsigned(s_max);
			else
				s_count <= s_count +1;
			end if;
		elsif (enable='0') then
			s_count <= (others => '0');
		end if;
	end if;
	end process;
	count <= std_logic_vector(s_count);
end Behavioral;