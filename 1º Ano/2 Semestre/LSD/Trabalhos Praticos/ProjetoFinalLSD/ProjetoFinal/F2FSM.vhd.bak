library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity ProjetoFinalFSM is
	port( reset : in std_logic;
			clk : in std_logic;
			key0 : in std_logic;
			d_on : in std_logic;
			timerEn : out std_logic;
			displayTest : out std_logic;
			regEn : out std_logic;
			freerunEn : out std_logic);
end ProjetoFinalFSM;

architecture Behav of ProjetoFinalFSM is
	type state is ( P1Start,P1Wait,P1Test,P2Start,P2Wait,P2Test, Concl);
	signal PS, NS : state;
	begin
		sync_proc: process(clk)
		begin
			if (rising_edge(clk)) then
				if (reset = '1') then
					PS <= P1Start;
				else
					PS <= NS;
				end if;
			end if;
		end process;
		
		comb_proc: process(key0,PS,d_on)
		begin
			case (PS) is
			
			when P1Start =>
				displayTest <= '1';
				timerEn <= '0';
				regEn <= '0';
				freerunEn <= '1';
				
				if(key0 = '1') then
					NS <= P1Wait;
				else
					NS <= P1Start;
				end if;
			
			when P1Wait =>
				displayTest <= '1';
				timerEn <= '1';
				regEn <= '0';
				freerunEn <= '1';
				
				if(d_on = '1') then
					NS <= P1Test;
				else
					NS <= P1Wait;
				end if;
			
			when P1Test =>
				displayTest <= '1';
				timerEn <= '0';
				regEn <= '1';
				freerunEn <= '1';
				
				if(key0 = '1') then
					NS <= P2Start;
				else
					NS <= P1Test;
				end if;
				
			when P2Start =>
				displayTest <= '0';
				timerEn <= '0';
				regEn <= '1';
				freerunEn <= '1';
				
				if(key0 = '1') then
					NS <= P2Wait;
				else
					NS <= P2Start;
				end if;
			
			when P2Wait =>
				displayTest <= '0';
				timerEn <= '1';
				regEn <= '1';
				freerunEn <= '1';
				
				if(d_on = '1') then
					NS <= P2Test;
				else
					NS <= P1Wait;
				end if;
			
			when P2Test =>
				displayTest <= '0';
				timerEn <= '0';
				regEn <= '1';
				freerunEn <= '1';
				
				if(key0 = '1') then
					NS <= Concl;
				else
					NS <= P2Test;
				end if;
			
			when Concl =>
				displayTest <= '0';
				timerEn <= '0';
				regEn <= '1';
				freerunEn <= '0';
				NS <= P1Start;
			
			
			end case;
		end process;
				
			
			
				
	end Behav;
