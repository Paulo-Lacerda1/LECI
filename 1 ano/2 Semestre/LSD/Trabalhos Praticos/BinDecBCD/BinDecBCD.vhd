library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity BinDecoderDecimal is
    port(
        binInput : in  std_logic_vector(3 downto 0);
		  decOut_n : out std_logic_vector(6 downto 0)
    );
end BinDecoderDecimal;

architecture Behavioural of BinDecoderDecimal is

    signal decimal_value : integer;
	 
begin

    process(binInput)
	 
begin
    case binInput is
        when "0000" =>
            decimal_value <= 0;
        when "0001" =>
            decimal_value <= 1;
        when "0010" =>
            decimal_value <= 2;
        when "0011" =>
            decimal_value <= 3;
        when "0100" =>
            decimal_value <= 4;
        when "0101" =>
            decimal_value <= 5;
        when "0110" =>
            decimal_value <= 6;
        when "0111" =>
            decimal_value <= 7;
        when "1000" =>
            decimal_value <= 8;
        when "1001" =>
            decimal_value <= 9;
        when others =>
            decimal_value <= 0;
    end case;

	  case decimal_value is
			when 0 =>
				 decOut_n <= "0110000";
			when 1 =>
				 decOut_n <= "0110001";
			when 2 =>
				 decOut_n <= "0110010";
			when 3 =>
				 decOut_n <= "0110011";
			when 4  =>
				 decOut_n <= "0110100";
			when 5 =>
				 decOut_n <= "0110101";
			when 6 =>
				 decOut_n <= "0110110";
			when 7 =>
				 decOut_n <= "0110111";
			when 8 =>
				 decOut_n <= "0111000";
			when 9 =>
				 decOut_n <= "0111001";
			when others  =>
				 decOut_n <= "1111111";
	  end case;
end process;


end Behavioural;
