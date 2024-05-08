library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD;

entity AddSub4 is
	port(a, b     : in std_logic_vector (3 downto 0);
		 sub, cin : in std_logic;
		 cout     : out std_logic;
		 s        : out std_logic_vector (3 downto 0) 
	);
end AddSub4;

/* architecture Structural of AddSub4 is

signal s_b : std_logic_vector(3 downto 0);

begin
	sys_core : entity work.Adder4(Structural)
		port map (a    => a,
				  b    => s_b,
				  s    => s,
				  cin  => cin,
				  cout => cout
		);
	s_b <= b when (sub = '0') else not b;
end Structural; */

architecture Behavioral of AddSub4 is

signal s_a, s_b, s_s : unsigned(4 downto 0);

begin
	s_a  <= '0' & unsigned(a);
	s_b  <= '0' & unsigned(b);
	s_s  <= (s_a + s_b) when (sub = '0') else
		    (s_a - s_b);
	s    <= std_logic_vector(s_s(3 downto 0));
	cout <= s_s(4);
end Behavioral;
