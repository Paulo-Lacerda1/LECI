library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity F1 is
    port (
        KEY : in std_logic_vector(0 downto 0);
        LEDG : out std_logic_vector(7 downto 0);
        HEX0 : out std_logic_vector(6 downto 0);
        HEX1 : out std_logic_vector(6 downto 0);
        HEX2 : out std_logic_vector(6 downto 0);
        HEX3 : out std_logic_vector(6 downto 0);
        SW : in std_logic_vector(0 downto 0);
        CLOCK_50 : in std_logic
    );
end F1;

architecture Behav of F1 is
    signal s_sw, s_key0, s_clk, stim, s_stim, reset_reaction_time : std_logic;
    signal t_reac, s_res : std_logic_vector(12 downto 0);
    signal s_d : std_logic_vector(12 downto 0);
    signal s_hex3, s_hex2, s_hex1, s_hex0, s_res3, s_res2, s_res1, s_res0 : std_logic_vector(6 downto 0);
    signal s_bcd0, s_bcd1, s_bcd2, s_bcd3 : std_logic_vector(3 downto 0);

begin
    -- clkdiv
    clkdivider : entity work.ClkDividerN(Behavioral)
        generic map(divFactor => 50_000)
        port map(
            clkIn => CLOCK_50,
            clkOut => s_clk
        );

    freeruncnt : entity work.CounterUp4(Behavioral)
        port map(
            clk => CLOCK_50,
            reset => s_sw,
            enable => '1',
            count => s_d
        );

    timer : entity work.TimerOp(Behav)
        port map(
            clk => CLOCK_50,
            N => s_d,
            reset => s_sw,
            enable => '1',
            start => s_key0,
            timerOut => stim
        );

    chronocounter : entity work.CounterForDisplay(Behavioral)
        port map(
            clk => s_clk,
            reset => reset_reaction_time, 
            enable => s_stim,
            count => t_reac
        );

    RegReact: entity work.RegN(Behavioral)
        generic map(size => 13)
        port map(
            asyncReset => reset_reaction_time,
            clk => CLOCK_50,
            enable => s_key0,
            dataIn => t_reac,
            dataOut => s_res
        );

    Bin2BCD : entity work.BIN2BCD(Behavioral)
        port map(
            Databin => s_res,
            BCD0 => s_bcd0,
            BCD1 => s_bcd1,
            BCD2 => s_bcd2,
            BCD3 => s_bcd3
        );

    -- Decoders
    dechex3 : entity work.Bin7SegDecoder(Behavioral)
        port map(
            binInput => s_bcd3,
            enable => '1',
            decOut_n => s_res3
        );

    dechex2 : entity work.Bin7SegDecoder(Behavioral)
        port map(
            binInput => s_bcd2,
            enable => '1',
            decOut_n => s_res2
        );

    dechex1 : entity work.Bin7SegDecoder(Behavioral)
        port map(
            binInput => s_bcd1,
            enable => '1',
            decOut_n => s_res1
        );

    dechex0 : entity work.Bin7SegDecoder(Behavioral)
        port map(
            enable => '1',
            binInput => s_bcd0,
            decOut_n => s_res0
        );

    -- Processes
    activation : process(CLOCK_50)
    begin
        if rising_edge(CLOCK_50) then
            s_sw <= SW(0);
            s_key0 <= not KEY(0);

            if s_sw = '1' then
                LEDG <= (others => '0');
                s_stim <= '0';
                reset_reaction_time <= '1'; 
            elsif stim = '1' and s_stim = '0' then
                reset_reaction_time <= '1'; 
                LEDG <= (others => '1');
                s_stim <= '1';
            elsif s_key0 = '1' then
                LEDG <= (others => '0');
                s_stim <= '0';
                reset_reaction_time <= '0';
            else
                reset_reaction_time <= '0';
            end if;

            s_hex3 <= s_res3;
            s_hex2 <= s_res2;
            s_hex1 <= s_res1; 
            s_hex0 <= s_res0; 
                
        end if;     

        HEX3 <= s_hex3;
        HEX2 <= s_hex2;    
        HEX1 <= s_hex1;
        HEX0 <= s_hex0;
          
    end process;

end Behav;
