library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity FreqDivider_1Hz_LED_7seg is
    port (
        clkIn  : in  std_logic;
        clkOut : out std_logic;
        LED    : out std_logic_vector(6 downto 0)
    );
end FreqDivider_1Hz_LED_7seg;

architecture Behavioral of FreqDivider_1Hz_LED_7seg is
    signal k : std_logic_vector(31 downto 0);
    signal seg_pattern : std_logic_vector(6 downto 0) := "1111111";
    signal toggle : std_logic := '0';
begin
    k <= x"02FAF080";

    process(clkOut)
    begin
        if rising_edge(clkOut) then
            
				toggle <= not toggle;
            if toggle = '1' then
                LED <= seg_pattern; -- LED's ligados
            else
                LED <= (others => '0'); -- LED's apagados
            end if;
        
		  end if;
    end process;
end Behavioral;
