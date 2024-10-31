library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CounterUpDown4 is
    port(
        clk, reset, enable : in std_logic;
        up, down : in std_logic;
        count : out std_logic_vector(3 downto 0));
end CounterUpDown4;

architecture Behavioral of CounterUpDown4 is
    signal s_count : unsigned(3 downto 0) := "0110";
    constant s_max : unsigned(3 downto 0) := "1001";
    constant s_min : unsigned(3 downto 0) := "0010"; 
    signal s_up, s_down : std_logic;
begin
    process(clk)
    begin
        if rising_edge(clk) then
            s_up <= up;
            s_down <= down;
            if reset = '1' then
                s_count <= "0110";
            elsif enable = '1' then
                if s_up = '1' then
                    if s_count < s_max then
                        s_count <= s_count + 1;
                    else
                        s_count <= s_max;
                    end if;
                elsif s_down = '1' then
                    if s_count > s_min then
                        s_count <= s_count - 1;
                    else
                        s_count <= s_min;
                    end if;
                else
                    s_count <= s_count;
                end if;
            end if;
        end if;
    end process;
    count <= std_logic_vector(s_count);
end Behavioral;
