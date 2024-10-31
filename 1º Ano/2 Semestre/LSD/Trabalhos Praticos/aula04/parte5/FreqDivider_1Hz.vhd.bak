library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity FreqDivider_1Hz is
	port (clkIn  : in  std_logic;
		  clkOut : out std_logic
	);
end FreqDivider_1Hz;

architecture Shell of FreqDivider_1Hz is
	signal k : std_logic_vector(31 downto 0);
begin
	k <= x"02FAF080";
	sys_core : entity work.FreqDivider(Behavioral)
		port map(clkIn  => clkIn,
			     clkOut => clkOut,
				 k      => k
		);
end Shell;
