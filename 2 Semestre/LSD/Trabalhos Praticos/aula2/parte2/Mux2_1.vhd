library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Mux2_1 is
	port(sel    : in  std_logic;
		 dataIn : in  std_logic_vector(1 downto 0);
		 dataOut: out std_logic
	);
end Mux2_1;

architecture BehavMuxTwo of Mux2_1 is
begin
	process(sel, dataIn)
	begin
		if (sel = '0') then
			dataOut <= dataIn(0);
		else
			dataOut <= dataIn(1);
		end if;
	end process;
end BehavMuxTwo;
