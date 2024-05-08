library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity AccN is
	generic (N : positive := 4);
	port (dataIn  		: in  std_logic_vector(N-1 downto 0);
		  dataOut 		: out std_logic_vector(N-1 downto 0);
		  enable, reset : in  std_logic;
		  clk			: in  std_logic
	);
end AccN;

architecture Shell of AccN is
	signal s_adderOut : std_logic_vector(N-1 downto 0);
	signal s_regOut   : std_logic_vector(N-1 downto 0);
begin
	adder_core : entity work.AdderN(Behavioral)
		generic map (N=>N)
		port map(
				 operand0 => dataIn,
				 operand1 => s_regOut,
				 result   => s_adderOut
		);
	
	reg_core   : entity work.RegN(Behavioral)
		generic map (N=>N)
		port map(dataIn  => s_adderOut,
				 reset   => reset,
				 enable  => enable,
				 clk     => clk,
				 dataOut => s_regOut
		);
end Shell;

