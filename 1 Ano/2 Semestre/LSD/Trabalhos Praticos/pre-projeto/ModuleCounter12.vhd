library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL; 
use IEEE.NUMERIC_STD.ALL;

entity ModuleCounter12 is
    Port (
        clk, reset : in STD_LOGIC;
        enable : in STD_LOGIC;
        LEDR : out STD_LOGIC_VECTOR(3 downto 0);
        dataOUT : out STD_LOGIC_VECTOR(6 downto 0));
		  
end entity ModuleCounter12;

architecture Behavioral of ModuleCounter12 is

    signal counter : integer range 0 to 11 := 0;
    signal displayValue : integer range 0 to 11 := 0;
    signal blinkToggle : std_logic := '0';

begin

    process(clk, reset)
    begin
        if reset = '1' then
            counter <= 0;
        elsif rising_edge(clk) then
            if counter = 11 then
                counter <= 0;
            else
                counter <= counter + 1;
            end if;
        end if;
    end process;

    process(counter, enable)

    begin
        if enable = '1' then
            displayValue <= counter;
        else
            displayValue <= 0;
        end if;
    end process;


    process(clk)
    begin
        if rising_edge(clk) then
            if blinkToggle = '0' then
                blinkToggle <= '1';
            else
                blinkToggle <= '0';
            end if;
        end if;
    end process;


    LEDR <= std_logic_vector(to_unsigned(counter, LEDR'length)) when blinkToggle = '1' else "0000";

    dataOUT <= "0000000";
	 
end architecture Behavioral;
