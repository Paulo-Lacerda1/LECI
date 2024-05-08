library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DisplayDemoVHDL is
    port (KEY  : in  std_logic_vector(0 downto 0);
          SW   : in  std_logic_vector(3 downto 0);
          HEX0 : out std_logic_vector(6 downto 0);
          LEDR : out std_logic_vector(6 downto 0);
          LEDG : out std_logic_vector(3 downto 0)
    );
end DisplayDemoVHDL;

architecture Shell of DisplayDemoVHDL is
begin
    system_core : entity work.Bin7SegDecoder(Behavioural)
                    port map(enable   => KEY(0),
                             binInput => SW,
                             decOut_n => HEX0
                    );
    LEDG <= SW;
    LEDR <= HEX0;
end Shell;
