library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity Bin2BCD is
	port(Databin 		 : in  std_logic_vector (12 downto 0);
		  BCD0,BCD1,BCD2,BCD3   : out std_logic_vector(3 downto 0));
end Bin2BCD;

architecture Behavioral of Bin2BCD is
	signal s_in,s_bcd0,s_bcd1,s_bcd2,s_bcd3 : unsigned(12 downto 0);
	signal div1,div2,div3 : unsigned(12 downto 0);
	
begin
	s_in <= unsigned(Databin);
	s_bcd0 <= s_in rem to_unsigned(10,13);
	BCD0 <= std_logic_vector(s_bcd0(3 downto 0));
	div1<= s_in / to_unsigned(10,13);
	s_bcd1 <= div1 rem to_unsigned(10,13);
	BCD1 <= std_logic_vector(s_bcd1(3 downto 0));
	div2 <= div1 / to_unsigned(10,13);
	s_bcd2 <= div2 rem to_unsigned(10,13);
	BCD2 <= std_logic_vector(s_bcd2(3 downto 0));
	div3 <= div2 / to_unsigned(10,13);
	s_bcd3 <= div3 rem to_unsigned(10,13);
	BCD3 <= std_logic_vector(s_bcd3(3 downto 0));
	
	
	
end Behavioral;