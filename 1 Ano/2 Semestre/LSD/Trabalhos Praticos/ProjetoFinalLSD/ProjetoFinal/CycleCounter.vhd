library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CycleCounter is
    port(
        clk, reset, enable : in std_logic;
        N : in std_logic_vector(3 downto 0) := "0110";
        playerdown : out std_logic;
        s_afterfirst : out std_logic;
        complete : out std_logic);
		  
end CycleCounter;

architecture Behavioral of CycleCounter is
    signal s_count : unsigned(7 downto 0);
    signal s_max : unsigned(7 downto 0);
    signal s_complete : std_logic := '0';
    signal s_playerdown : std_logic;
    signal s_rem : unsigned(7 downto 0);
    signal afterfirst : std_logic := '0';

begin
    s_max <= unsigned(N) * 2;

    process(clk)
    begin
        if rising_edge(clk) then
            if reset = '1' then
                s_count <= (others => '0');
                s_complete <= '0';
                s_playerdown <= '0';
                afterfirst <= '0'; -- Reset afterfirst on reset
            elsif enable = '1' then
					if(s_count /= "00000000") then
						s_rem <= s_count rem to_unsigned(2, 8);
						if s_rem = 0 then
							s_playerdown <= '1';
						else
							s_playerdown <= '0';
						end if;
					end if;
               if s_count >= s_max then
						s_count <= (others => '0');
						s_complete <= '1';
               else
                  s_count <= s_count + 1;
                  s_complete <= '0';
                  afterfirst <= '1';
               end if;
            end if;
        end if;
    end process;

    complete <= s_complete;
    playerdown <= s_playerdown;
    s_afterfirst <= afterfirst;

end Behavioral;
