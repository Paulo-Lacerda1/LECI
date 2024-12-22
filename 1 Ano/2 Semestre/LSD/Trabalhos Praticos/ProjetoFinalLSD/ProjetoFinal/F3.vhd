library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity F3 is
	port (KEY 	: in std_logic_vector(0 downto 0);
			LEDG 	: out std_logic_vector(7 downto 0);
			LEDR 	: out std_logic_vector(7 downto 0);
			HEX0  : out std_logic_vector(6 downto 0);
			HEX1  : out std_logic_vector(6 downto 0);
			HEX2  : out std_logic_vector(6 downto 0);
			HEX3  : out std_logic_vector(6 downto 0);
			HEX4  : out std_logic_vector(6 downto 0);
			HEX5  : out std_logic_vector(6 downto 0);
			HEX6  : out std_logic_vector(6 downto 0);
			HEX7  : out std_logic_vector(6 downto 0);
			SW	  	: in  std_logic_vector(0 downto 0);
			CLOCK_50 : in std_logic);
end F3;

architecture Behav of F3 is
	signal s_blink2Hzdraw,s_WintimeExp,s_DrawtimeExp,s_sw,s_key0,s_clk,s_clk1Hz,s_stim,s_pulse,s_playerdown : std_logic;
	signal s_cnten,s_cntre,s_regen,s_freerunEN,s_blink2Hz,s_PlayerUp,s_TimerEn,s_ChronoStart,s_ChronoStop,s_Draw,s_DrawEN,s_BestDisplay,s_TestDisplay,s_WinEN,s_AfterFirst,s_Complete : std_logic;	 
	signal s_bcd3,s_bcd2,s_bcd1,s_bcd0 : std_logic_vector(3 downto 0);
	signal s_d,t_reac,s_res	  : std_logic_vector(12 downto 0);
	signal t_reacbest : std_logic_vector(12 downto 0) := (others => '1');
	signal s_hex7,s_hex6,s_hex5,s_hex4,s_hex3,s_hex2,s_hex1,s_hex0 : std_logic_vector(6 downto 0);
	signal s_playerhex5,s_win0,s_win1,s_win2,s_win3,s_win4,s_draw0,s_draw1,s_draw2,s_draw3,s_draw4,s_test0,s_test1,s_test2,s_test3,s_test5,s_best0,s_best1,s_best2,s_best3,s_best4 : std_logic_vector(6 downto 0);
	signal s_player,s_playerbest,s_playerbestdraw,s_player1left,s_player2left : std_logic_vector(3 downto 0);
	constant WinTime : std_logic_vector(7 downto 0) := "00000110";--6s
	
begin	
		
		--clkdiv
		
		clkdividerMiliSec : entity work.ClkDividerN(Behavioral)
				generic map(divFactor => 50_000)
				port map(clkIn	=> CLOCK_50,
							clkOut => s_clk);
		
		clkdivider1Hz : entity work.ClkDividerN(Behavioral)
				generic map(divFactor => 50_000_000)
				port map(clkIn	=> CLOCK_50,
							clkOut => s_clk1Hz);
		
		--blinker
		
		blinker2Hz : entity work.LargePulseGen(Behavioral)
				generic map(NUMBER_STEPS => 25_000_000)
				port map(clk => CLOCK_50,
							enable => s_WinEN,
							reset => s_sw,
							blink => s_blink2Hz);
		blinker2Hzdraw : entity work.LargePulseGen(Behavioral)
				generic map(NUMBER_STEPS => 25_000_000)
				port map(clk => CLOCK_50,
							enable => s_DrawEn,
							reset => s_sw,
							blink => s_blink2Hzdraw);
							
							

		
		
		-------------------------------------------------

		comparator : entity work.Comparator(Behav)
				port map(clk => CLOCK_50,
							bestreaction => t_reacbest,
							player => s_player,
							reaction => t_reac,
							bestplayer => s_playerbest,
							draw	=> s_Draw,
							reactionout => t_reacbest);
		
		playerdown : entity work.CounterDown4(Behavioral)
				generic map(N => "0110")
				port map(clk => CLOCK_50,
							reset => s_sw,
							enable => s_Playerup,
							wichplayer => s_playerdown,
							count1 => s_player1left,
							count2 => s_player2left);
		 
		
		freeruncnt : entity work.CounterUp4(Behavioral)
				port map(clk		=> CLOCK_50,
							reset		=>	s_sw,
							enable	=> s_freerunEN,
							count		=> s_d);
		
		player : entity work.COunterUp4v2(Behavioral)
			port map(clk => CLOCK_50,
						reset => s_sw,
						enable => s_PlayerUp,
						count => s_player);
		
		playerdraw : entity work.COunterUp4v2(Behavioral)
			port map(clk => s_clk1Hz,
						reset => s_sw,
						enable => s_DrawEN,
						count => s_playerbestdraw);
		
		cyclecounter : entity work.CycleCounter(Behavioral)
			port map(clk => CLOCK_50,
						enable => s_Playerup,
						N => "0110",
						reset => s_sw,
						playerdown => s_playerdown,
						s_afterfirst => s_AfterFirst,
						complete => s_Complete);
		
		timer : entity work.TimerOp(Behav)
			port map(clk		=> s_clk,
						N 			=> s_d,
						reset 	=> s_sw,
						enable	=> '1',
						start 	=> s_TimerEn,
						timerOut => s_stim);
		
		Wintimer_fsm : entity work.TimerAuxFSM(Behavioral)
		port map(reset		=> s_sw,
					clk		=> not s_clk1Hz,
					newTime	=> s_WinEN,
					timeVal	=> WinTime,
					timeExp	=> s_WintimeExp);
		
		Drawtimer_fsm : entity work.TimerAuxFSM(Behavioral)
		port map(reset		=> s_sw,
					clk		=> not s_clk1Hz,
					newTime	=> s_DrawEN,
					timeVal	=> WinTime,
					timeExp	=> s_DrawtimeExp);

		
		chronocounter : entity work.CounterForDisplay(Behavioral)
				port map(clk		=> s_clk,
							reset		=>	s_ChronoStop,
							enable	=> s_ChronoStart,
							count		=> t_reac);
		
		RegReact: entity work.RegN(Behavioral)
			generic map(size	  => 13)
			port map(asyncReset => s_sw,
						clk		  => CLOCK_50, 
						enable	  => s_key0,
						dataIn	  => t_reacbest,
						dataOut	  => s_res);
		
		Bin2BCD : entity work.BIN2BCD(Behavioral)
				port map(Databin 	=> s_res,
							BCD0 => s_bcd0,
							BCD1 => s_bcd1,
							BCD2 => s_bcd2,
							BCD3 => s_bcd3);
		
		--MEFControl
		
		control : entity work.F2FSM(Behav)
			port map(reset => s_sw,
						clk => CLOCK_50,
						key0 => s_key0,
						playerup => s_PlayerUp,
						AfterFirst => s_AfterFirst,
						s_WinOver => s_WintimeExp,
						s_DrawOver => s_DrawtimeExp,
						stim => s_stim,
						complete => s_Complete,
						s_draw => s_Draw,
						timerEn => s_TimerEN,
						DrawDisplay => s_DrawEN,
						WinDisplay =>  s_WinEN,
						TestDisplay => s_TestDisplay,
						BestDisplay => s_BestDisplay,
						ChronoStart => s_ChronoStart,
						ChronoStop => s_ChronoStop,
						freerunEn => s_freerunEN);
		
		---------Decoders
		--BestDisplay
		BestDisplayhex4 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_playerbest,
						enable	=> s_BestDisplay,
						decOut_n	=> s_best4);
		BestDisplayhex3 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd3,
						enable	=> s_BestDisplay,
						decOut_n	=> s_best3);
		BestDisplayhex2 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd2,
						enable	=> s_BestDisplay,
						decOut_n	=> s_best2);
		BestDisplayhex1 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd1,
						enable	=> s_BestDisplay,
						decOut_n	=> s_best1);
		BestDisplayhex0 : entity work.Bin7SegDecoder(Behavioral)
			port map(enable	=> s_BestDisplay,
						binInput => s_bcd0,
						decOut_n	=> s_best0);
		--TestDisplay
		TestDisplayhex3 : entity work.Bin7SegToTest(Behavioral)
			port map(binInput => "0001",
						enable	=> s_TestDisplay,
						decOut_n	=> s_test3);
		TestDisplayhex2 : entity work.Bin7SegToTest(Behavioral)
			port map(binInput => "0010",
						enable	=> s_TestDisplay,
						decOut_n	=> s_test2);
		TestDisplayhex1 : entity work.Bin7SegToTest(Behavioral)
			port map(binInput => "0011",
						enable	=> s_TestDisplay,
						decOut_n	=> s_test1);
		TestDisplayhex0 : entity work.Bin7SegToTest(Behavioral)
			port map(enable	=> s_TestDisplay,
						binInput => "0100",
						decOut_n	=> s_test0);
		--Player				
		playerhex5 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_player,
						enable	=> '1',
						decOut_n	=> s_playerhex5);
		cycleleft1 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_player1left,
						enable	=> '1',
						decOut_n	=> s_hex7);
		
		cycleleft2 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_player2left,
						enable	=> '1',
						decOut_n	=> s_hex6);
		--WinDisplay
		WinDisplayhex4 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_playerbest,
						enable	=> s_blink2Hz,
						decOut_n	=> s_win4);
		WinDisplayhex3 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd3,
						enable	=> s_blink2Hz,
						decOut_n	=> s_win3);
		WinDisplayhex2 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd2,
						enable	=> s_blink2Hz,
						decOut_n	=> s_win2);
		WinDisplayhex1 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd1,
						enable	=> s_blink2Hz,
						decOut_n	=> s_win1);
		WinDisplayhex0 : entity work.Bin7SegDecoder(Behavioral)
			port map(enable	=> s_blink2Hz,
						binInput => s_bcd0,
						decOut_n	=> s_win0);
		
		--DrawDisplay
		DrawDisplayhex4 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_playerbestdraw,
						enable	=> s_DrawEN,
						decOut_n	=> s_draw4);
		DrawDisplayhex3 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd3,
						enable	=> s_blink2Hzdraw,
						decOut_n	=> s_draw3);
		DrawDisplayhex2 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd2,
						enable	=> s_blink2Hzdraw,
						decOut_n	=> s_draw2);
		DrawDisplayhex1 : entity work.Bin7SegDecoder(Behavioral)
			port map(binInput => s_bcd1,
						enable	=> s_blink2Hzdraw,
						decOut_n	=> s_draw1);
		DrawDisplayhex0 : entity work.Bin7SegDecoder(Behavioral)
			port map(enable	=> s_blink2Hzdraw,
						binInput => s_bcd0,
						decOut_n	=> s_draw0);
		------------------------------------------------				
		display : process(CLOCK_50,s_WinEN,s_DrawEN,s_TestDisplay,s_BestDisplay)
		begin
			if(rising_edge(CLOCK_50)) then
				s_sw <= SW(0);
				s_key0 <= not KEY(0);
				if(s_TestDisplay='1') then
					s_hex5 <= s_playerhex5;
					s_hex3 <= s_test3;
					s_hex2 <= s_test2;
					s_hex1 <= s_test1;
					s_hex0 <= s_test0;
				elsif(s_BestDisplay='1') then
					s_hex5 <= s_playerhex5;
					s_hex4 <= s_best4;
					s_hex3 <= s_best3;
					s_hex2 <= s_best2;
					s_hex1 <= s_best1;
					s_hex0 <= s_best0;
				elsif(s_WinEN='1') then
					s_hex4 <= s_win4;
					s_hex3 <= s_win3;
					s_hex2 <= s_win2;
					s_hex1 <= s_win1;
					s_hex0 <= s_win0;
				elsif(s_DrawEN='1') then
					s_hex4 <= s_draw4;
					s_hex3 <= s_draw3;
					s_hex2 <= s_draw2;
					s_hex1 <= s_draw1;
					s_hex0 <= s_draw0;
				end if;
			end if;
		end process;
		LEDG <= (others => s_ChronoStart);
		HEX7 <= s_hex7;
		HEX6 <= s_hex6;
		HEX5 <= s_hex5;
		HEX4 <= s_hex4;
		HEX3 <= s_hex3;
		HEX2 <= s_hex2;
		HEX1 <= s_hex1;
		HEX0 <= s_hex0;
			
						
end Behav;