library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity FreqDivider_Demo is
	port (CLOCK_50 : in  std_logic;
		  LEDR     : out std_logic
	);
end FreqDivider_Demo;

architecture Shell of FreqDivider_Demo is
	signal k : std_logic_vector(31 downto 0);
begin
	k <= x"017D7840";
	sys_core : entity work.FreqDivider(Behavioral)
		port map(clkIn  => CLOCK_50,
			     clkOut => LEDR,
				 k        => k
		);
end Shell;

