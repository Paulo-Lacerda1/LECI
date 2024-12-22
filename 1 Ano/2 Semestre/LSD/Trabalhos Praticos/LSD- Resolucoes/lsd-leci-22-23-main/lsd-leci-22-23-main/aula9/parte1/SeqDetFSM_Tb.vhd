library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity SeqDetFSM_Tb is
end SeqDetFSM_Tb;

architecture Stimulus of SeqDetFSM_Tb is
	signal s_reset : std_logic;
	signal s_clk   : std_logic;
	signal s_xIn   : std_logic;
	signal s_zOut  : std_logic;
begin
	uut : entity work.SeqDetFSM(MealyArch)
	port map(reset => s_reset,
			 clk   => s_clk,
			 xIn   => s_xIn,
			 zOut  => s_zOut
	);
	
	stim_proc : process
	begin
		s_reset <= '0';
		s_xIn <= '0';
		wait for 100 ns;
		s_xIn <= '1';
		wait for 100 ns;
		s_xIn <= '0';
		wait for 100 ns;
		s_xIn <= '1';
		wait for 100 ns;
		s_xIn <= '0';
		wait for 100 ns;
		s_xIn <= '0';
		wait for 100 ns;
		s_xIn <= '1';
		wait for 100 ns;
		s_reset <= '1';
		wait for 100 ns;
		
	end process;
end Stimulus;