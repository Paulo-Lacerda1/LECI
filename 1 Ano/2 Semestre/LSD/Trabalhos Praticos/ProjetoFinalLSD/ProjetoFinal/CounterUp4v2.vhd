	library IEEE;
	use IEEE.STD_LOGIC_1164.all;
	use IEEE.NUMERIC_STD.all;

	entity CounterUp4v2 is
		port(clk,reset,enable : in std_logic;
			  count : out std_logic_vector(3 downto 0));
	end CounterUp4v2;

	architecture Behavioral of CounterUp4v2 is
		signal s_count : unsigned(3 downto 0);
		signal s_max 	: unsigned(3 downto 0);
	begin
		s_max  <= "0010";
		process(clk)
		begin
		if (rising_edge(clk)) then
			if reset ='1' then
				s_count <= (others => '0');
			elsif enable='1' then
				if (s_count >= unsigned(s_max)-1) then
					s_count <= "0001";
				else
					s_count <= s_count +1;
				end if;
			end if;
		end if;
		end process;
		count <= std_logic_vector(s_count);
	end Behavioral;