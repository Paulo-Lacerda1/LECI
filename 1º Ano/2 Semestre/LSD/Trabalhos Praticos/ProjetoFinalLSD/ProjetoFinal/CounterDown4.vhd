library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CounterDown4 is
	generic( N : unsigned := "0110");
	port(clk,reset,enable : in std_logic;
		  wichplayer : in std_logic;
		  count1,count2 : out std_logic_vector(3 downto 0));
end CounterDown4;

architecture Behavioral of CounterDown4 is
	signal s_count1,s_count2 : unsigned(3 downto 0);
	signal s_max 	: unsigned(3 downto 0);
	signal s_wich  : std_logic;
begin
	s_max <= N;
	process(clk)
	begin
	if (rising_edge(clk)) then
		s_wich <= wichplayer;
		if reset ='1' then
			s_count1 <= s_max;
			s_count2 <= s_max;
		elsif enable='1' then
			if(s_wich = '0') then
				if (s_count2 > "0000") then
					s_count2 <= s_count2 -1;
				end if;
			elsif(s_wich ='1') then
				if (s_count1 > "0000") then
					s_count1 <= s_count1 -1;
					
				end if;
			end if;
		end if;
	end if;
	end process;
	count1 <= std_logic_vector(s_count1);
	count2 <= std_logic_vector(s_count2);
end Behavioral;