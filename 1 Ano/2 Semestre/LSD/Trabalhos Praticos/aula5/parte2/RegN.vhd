library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity RegN is
	generic (N : positive := 4);
	port (dataIn        : in  std_logic_vector(N-1 downto 0);
		  dataOut       : out std_logic_vector(N-1 downto 0);
		  reset, enable : in  std_logic;
		  clk           : in  std_logic
	);
end RegN;

architecture Behavioral of RegN is
begin
	process(clk)
	begin
		if (rising_edge (clk)) then
			if (reset = '0') then
				if (enable = '1') then
					dataOut <= dataIn;
				end if;
			else
				dataOut <= "0000";
			end if;
		end if;
	end process;
end Behavioral;
