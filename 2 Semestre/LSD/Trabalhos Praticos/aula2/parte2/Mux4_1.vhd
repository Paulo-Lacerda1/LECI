library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Mux4_1 is
	port(sel    : in  std_logic_vector(1 downto 0);
		 dataIn : in  std_logic_vector(3 downto 0);
		 dataOut: out std_logic
	);
end Mux4_1;

/* architecture BehavProc of Mux4_1 is
begin
	process(sel, dataIn)
	begin
		if    (sel = "00") then
			dataOut <= dataIn(0);
		elsif (sel = "01") then
			dataOut <= dataIn(1);
		elsif (sel = "10") then
			dataOut <= dataIn(2);
		else
			dataOut <= dataIn(3);
		end if;
	end process;
end BehavProc;

architecture BehavAssign of Mux4_1 is
begin
	dataOut <= dataIn(0) when sel = ("00") else
			   dataIn(1) when sel = ("01") else
			   dataIn(2) when sel = ("10") else
			   dataIn(3);
end BehavAssign; */

architecture BehavEquations of Mux4_1 is
begin
	dataOut <= dataIn(0) and not(sel(1)) and not(sel(0));
	dataOut <= dataIn(1) and not(sel(1)) and    (sel(0));
	dataOut <= dataIn(2) and    (sel(1)) and not(sel(0));
	dataOut <= dataIn(3) and    (sel(1)) and    (sel(0));
end BehavEquations;
