library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity Comparator is
	port( clk : in std_logic;
			bestreaction : in std_logic_vector(12 downto 0);
			player : in std_logic_vector(3 downto 0);
			bestplayer : out std_logic_vector(3 downto 0);
			draw	: out std_logic;
			reaction : in std_logic_vector(12 downto 0);
			reactionout : out std_logic_vector(12 downto 0));

end Comparator;

architecture Behav of Comparator is
	signal s_reactionout : std_logic_vector(12 downto 0);

begin

	process(clk)
	begin
		if (rising_edge(clk)) then
			if(reaction < bestreaction) then
				s_reactionout <= reaction;
				bestplayer <= player;
				draw <= '0';
			elsif(bestreaction = reaction) then
				draw <= '1';
			else
				draw <= '0';
			end if;
		end if;
	end process;
	reactionout <= s_reactionout;
end Behav;