library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;
--MEF nome:F4SM
entity F4FSM is
	port( reset : in std_logic;
			clk : in std_logic;
			key0 : in std_logic;
			AfterFirst : in std_logic;
			stim : in std_logic;
			complete : in std_logic;
			s_WinOver : in std_logic;
			s_DrawOver : in std_logic;
			s_draw : in std_logic;
			timerEn : out std_logic;
			DrawDisplay : out std_logic;
			WinDisplay : out std_logic;
			TestDisplay : out std_logic;
			BestDisplay : out std_logic;
			ConfDisplay : out std_logic;
			ChronoStart : out std_logic;
			ChronoStop : out std_logic;
			freerunEn : out std_logic;
			playerup : out std_logic);
end F4FSM;

architecture Behav of F4FSM is
	signal s_stim : std_logic;
	type state is ( Conf,Start,PWait,PTest, Conc,Draw);
	signal PS, NS : state;
	begin
		sync_proc: process(clk)
		begin
			if (rising_edge(clk)) then
				if(key0 <= '1') then
					s_stim <= '0';
				elsif ( stim <= '1') then
					s_stim <= '1';
				end if;
				
				if (reset = '1') then
					PS <= Start;
				else
					PS <= NS;
				end if;
			end if;
		end process;
		
		comb_proc: process(key0,PS,stim,complete,s_draw,AfterFirst,s_WinOver,s_DrawOver)
		begin
			case (PS) is
			
			when Conf =>
				ConfDisplay <= '1';
				TestDisplay <= '0';
				timerEn <= '0';
				ChronoStart <= '0';
				ChronoStop <= '0';
				freerunEn <= '1';
				BestDisplay <= '0';
				DrawDisplay <= '0';
				WinDisplay <= '0';
				playerup <= '0';
				
				if (key0 = '1') then
					NS <= Start;
				else
					NS <= Conf;
				end if;
				
			when Start =>
				if(AfterFirst = '1') then
					TestDisplay <= '0';
					timerEn <= '0';
					ChronoStart <= '0';
					ChronoStop <= '1';
					freerunEn <= '1';
					BestDisplay <= '1';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '1';
				else
					TestDisplay <= '1';
					timerEn <= '0';
					freerunEn <= '1';
					BestDisplay <= '0';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '1';
					ChronoStart <= '0';
					ChronoStop <= '1';
				end if;

				if(key0 = '1') then
					NS <= PWait;
				else
					NS <= Start;
				end if;
			
			when PWait =>
				if(AfterFirst = '1') then
					TestDisplay <= '0';
					timerEn <= '1';
					freerunEn <= '1';
					BestDisplay <= '1';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '0';
					ChronoStart <= '0';
					ChronoStop <= '0';
				else
					TestDisplay <= '1';
					timerEn <= '1';
					freerunEn <= '1';
					BestDisplay <= '0';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '0';
					ChronoStart <= '0';
					ChronoStop <= '0';
				end if;
				
				if(stim = '1') then
					NS <= PTest;
				else
					NS <= PWait;
				end if;
			
			when PTest =>
				if(AfterFirst = '1') then
					TestDisplay <= '0';
					timerEn <= '0';
					freerunEn <= '1';
					BestDisplay <= '1';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '0';
					ChronoStart <= '1';
					ChronoStop <= '0';
				else
					TestDisplay <= '1';
					timerEn <= '0';
					freerunEn <= '1';
					BestDisplay <= '0';
					DrawDisplay <= '0';
					WinDisplay <= '0';
					playerup <= '0';
					ChronoStart <= '1';
					ChronoStop <= '0';
				end if;
				
				if(key0 = '1' and complete = '1' and s_draw = '0') then
					ChronoStop <= '1';
					NS <= Conc;
				elsif(key0 = '1' and complete = '1' and s_draw = '1') then
					ChronoStop <= '1';
					NS <= Draw;
				elsif(key0 = '1' and complete = '0') then
					ChronoStop <= '1';
					NS <= Start;
					playerup <= '1';
				else
					NS <= PTest;
				end if;
			
			when Conc =>
				TestDisplay <= '0';
				WinDisplay <= '1';
				DrawDisplay <= '0';
				BestDisplay <= '0';
				freerunEn <= '0';
				timerEn <= '0';
				playerup <= '0';
				ChronoStart <= '0';
				ChronoStop <= '0';
				if(s_WinOver = '1') then
					NS <= Start;
				else
					NS <= Conc;
				end if;
			
			when Draw =>
				TestDisplay <= '0';
				WinDisplay <= '0';
				DrawDisplay <= '1';
				BestDisplay <= '0';
				freerunEn <= '0';
				timerEn <= '0';
				playerup <= '0';
				ChronoStart <= '0';
				ChronoStop <= '0';
				
				if(s_DrawOver = '1') then
					NS <= Start;
				else
					NS <= Draw;
				end if;
			
			
			end case;
		end process;
				
			
			
				
	end Behav;