library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity TimerOp is--com atraso à operação
	port( clk		: in std_logic;
			N			: in std_logic_vector(12 downto 0);
			reset 	: in std_logic;
			enable	: in std_logic;
			start 	: in std_logic;
			timerOut : out std_logic);

end TimerOp;

architecture Behav of TimerOP is
	constant hzCLKFreq : integer := 50_000;
	signal s_count : integer := 0;
	signal cycles : integer;
begin
	process(clk)
	begin
		if (rising_edge(clk)) then
			if (reset = '1') then
				timerOut <= '0';
				s_count <= 0;
			elsif (enable = '1') then
				if (s_count = 0) then
					if (start = '1') then
						cycles <= ((to_integer(unsigned(N)))-1)*hzCLKFreq;
						s_count <= s_count + 1;
						timerOut <= '0';
					else
						timerOut <= '0';
					end if;
				else
					if (s_count = cycles) then
						timerOut <= '1';
						s_count <= 0;
					else
						timerOut <= '0';
						s_count <= s_count + 1;
					end if;
				end if;
			end if;
		end if;
end process;
end Behav;