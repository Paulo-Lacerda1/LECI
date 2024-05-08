library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity PEnc4_2 is
    port (dencodedIn : in std_logic_vector(3 downto 0);
          encodedOut : out std_logic_vector(1 downto 0);
          validOut   : out std_logic
    );
end PEnc4_2;

architecture Behavioural of PEnc4_2 is
begin
    process(dencodedIn)
    begin
        validOut <= '1';
        if    (dencodedIn(3) = '1') then
            encodedOut <= "11";
        elsif (dencodedIn(2) = '1') then
            encodedOut <= "10";
        elsif (dencodedIn(1) = '1') then
            encodedOut <= "01";
        elsif (dencodedIn(0) = '1') then
            encodedOut <= "00";
        else
            validOut <= '0';
			encodedOut <= "XX";
        end if;
    end process;
end Behavioural;
