library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity AND2Gate is
    port (  inPort0 : in std_logic;
            inPort1 : in std_logic;
            outPort : out std_logic);
end AND2Gate;

architecture Behavioural of AND2Gate is
begin

    outPort <= inPort1 and inPort0;
	 
end Behavioural;
