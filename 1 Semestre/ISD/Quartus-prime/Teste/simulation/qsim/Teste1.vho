-- Copyright (C) 2023  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and any partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel FPGA IP License Agreement, or other applicable license
-- agreement, including, without limitation, that your use is for
-- the sole purpose of programming logic devices manufactured by
-- Intel and sold by Intel or its authorized distributors.  Please
-- refer to the applicable agreement for further details, at
-- https://fpgasoftware.intel.com/eula.

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 22.1std.2 Build 922 07/20/2023 SC Lite Edition"

-- DATE "12/07/2023 23:34:43"

-- 
-- Device: Altera EP4CE6E22C6 Package TQFP144
-- 

-- 
-- This VHDL file should be used for Questa Intel FPGA (VHDL) only
-- 

LIBRARY CYCLONEIVE;
LIBRARY IEEE;
USE CYCLONEIVE.CYCLONEIVE_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	Teste1 IS
    PORT (
	Y : OUT std_logic;
	S : IN std_logic_vector(2 DOWNTO 0);
	X0 : IN std_logic;
	X1 : IN std_logic;
	X3 : IN std_logic;
	X4 : IN std_logic;
	X5 : IN std_logic;
	X2 : IN std_logic;
	X7 : IN std_logic;
	X6 : IN std_logic
	);
END Teste1;

ARCHITECTURE structure OF Teste1 IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_Y : std_logic;
SIGNAL ww_S : std_logic_vector(2 DOWNTO 0);
SIGNAL ww_X0 : std_logic;
SIGNAL ww_X1 : std_logic;
SIGNAL ww_X3 : std_logic;
SIGNAL ww_X4 : std_logic;
SIGNAL ww_X5 : std_logic;
SIGNAL ww_X2 : std_logic;
SIGNAL ww_X7 : std_logic;
SIGNAL ww_X6 : std_logic;
SIGNAL \Y~output_o\ : std_logic;
SIGNAL \X5~input_o\ : std_logic;
SIGNAL \S[0]~input_o\ : std_logic;
SIGNAL \X6~input_o\ : std_logic;
SIGNAL \S[1]~input_o\ : std_logic;
SIGNAL \X4~input_o\ : std_logic;
SIGNAL \inst1|23~0_combout\ : std_logic;
SIGNAL \X7~input_o\ : std_logic;
SIGNAL \inst1|23~1_combout\ : std_logic;
SIGNAL \X2~input_o\ : std_logic;
SIGNAL \X1~input_o\ : std_logic;
SIGNAL \X0~input_o\ : std_logic;
SIGNAL \inst1|23~2_combout\ : std_logic;
SIGNAL \X3~input_o\ : std_logic;
SIGNAL \inst1|23~3_combout\ : std_logic;
SIGNAL \S[2]~input_o\ : std_logic;
SIGNAL \inst1|23~4_combout\ : std_logic;

BEGIN

Y <= ww_Y;
ww_S <= S;
ww_X0 <= X0;
ww_X1 <= X1;
ww_X3 <= X3;
ww_X4 <= X4;
ww_X5 <= X5;
ww_X2 <= X2;
ww_X7 <= X7;
ww_X6 <= X6;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

\Y~output\ : cycloneive_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false")
-- pragma translate_on
PORT MAP (
	i => \inst1|23~4_combout\,
	devoe => ww_devoe,
	o => \Y~output_o\);

\X5~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X5,
	o => \X5~input_o\);

\S[0]~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_S(0),
	o => \S[0]~input_o\);

\X6~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X6,
	o => \X6~input_o\);

\S[1]~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_S(1),
	o => \S[1]~input_o\);

\X4~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X4,
	o => \X4~input_o\);

\inst1|23~0\ : cycloneive_lcell_comb
-- Equation(s):
-- \inst1|23~0_combout\ = (\S[0]~input_o\ & (((\S[1]~input_o\)))) # (!\S[0]~input_o\ & ((\S[1]~input_o\ & (\X6~input_o\)) # (!\S[1]~input_o\ & ((\X4~input_o\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1110010111100000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \S[0]~input_o\,
	datab => \X6~input_o\,
	datac => \S[1]~input_o\,
	datad => \X4~input_o\,
	combout => \inst1|23~0_combout\);

\X7~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X7,
	o => \X7~input_o\);

\inst1|23~1\ : cycloneive_lcell_comb
-- Equation(s):
-- \inst1|23~1_combout\ = (\S[0]~input_o\ & ((\inst1|23~0_combout\ & ((\X7~input_o\))) # (!\inst1|23~0_combout\ & (\X5~input_o\)))) # (!\S[0]~input_o\ & (((\inst1|23~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111100000111000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \X5~input_o\,
	datab => \S[0]~input_o\,
	datac => \inst1|23~0_combout\,
	datad => \X7~input_o\,
	combout => \inst1|23~1_combout\);

\X2~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X2,
	o => \X2~input_o\);

\X1~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X1,
	o => \X1~input_o\);

\X0~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X0,
	o => \X0~input_o\);

\inst1|23~2\ : cycloneive_lcell_comb
-- Equation(s):
-- \inst1|23~2_combout\ = (\S[1]~input_o\ & (((\S[0]~input_o\)))) # (!\S[1]~input_o\ & ((\S[0]~input_o\ & (\X1~input_o\)) # (!\S[0]~input_o\ & ((\X0~input_o\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1110010111100000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \S[1]~input_o\,
	datab => \X1~input_o\,
	datac => \S[0]~input_o\,
	datad => \X0~input_o\,
	combout => \inst1|23~2_combout\);

\X3~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_X3,
	o => \X3~input_o\);

\inst1|23~3\ : cycloneive_lcell_comb
-- Equation(s):
-- \inst1|23~3_combout\ = (\S[1]~input_o\ & ((\inst1|23~2_combout\ & ((\X3~input_o\))) # (!\inst1|23~2_combout\ & (\X2~input_o\)))) # (!\S[1]~input_o\ & (((\inst1|23~2_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1111100000111000",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \X2~input_o\,
	datab => \S[1]~input_o\,
	datac => \inst1|23~2_combout\,
	datad => \X3~input_o\,
	combout => \inst1|23~3_combout\);

\S[2]~input\ : cycloneive_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_S(2),
	o => \S[2]~input_o\);

\inst1|23~4\ : cycloneive_lcell_comb
-- Equation(s):
-- \inst1|23~4_combout\ = (\S[2]~input_o\ & (\inst1|23~1_combout\)) # (!\S[2]~input_o\ & ((\inst1|23~3_combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1010101011001100",
	sum_lutc_input => "datac")
-- pragma translate_on
PORT MAP (
	dataa => \inst1|23~1_combout\,
	datab => \inst1|23~3_combout\,
	datad => \S[2]~input_o\,
	combout => \inst1|23~4_combout\);

ww_Y <= \Y~output_o\;
END structure;


