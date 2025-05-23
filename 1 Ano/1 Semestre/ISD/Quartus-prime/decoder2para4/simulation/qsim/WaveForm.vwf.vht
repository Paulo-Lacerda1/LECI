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

-- *****************************************************************************
-- This file contains a Vhdl test bench with test vectors .The test vectors     
-- are exported from a vector file in the Quartus Waveform Editor and apply to  
-- the top level entity of the current Quartus project .The user can use this   
-- testbench to simulate his design using a third-party simulation tool .       
-- *****************************************************************************
-- Generated on "11/30/2023 09:29:13"
                                                             
-- Vhdl Test Bench(with test vectors) for design  :          Decoder2para4
-- 
-- Simulation tool : 3rd Party
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY Decoder2para4_vhd_vec_tst IS
END Decoder2para4_vhd_vec_tst;
ARCHITECTURE Decoder2para4_arch OF Decoder2para4_vhd_vec_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL E : STD_LOGIC;
SIGNAL x1 : STD_LOGIC;
SIGNAL x2 : STD_LOGIC;
SIGNAL x3 : STD_LOGIC;
SIGNAL x4 : STD_LOGIC;
SIGNAL x5 : STD_LOGIC;
SIGNAL x6 : STD_LOGIC;
COMPONENT Decoder2para4
	PORT (
	E : IN STD_LOGIC;
	x1 : IN STD_LOGIC;
	x2 : IN STD_LOGIC;
	x3 : OUT STD_LOGIC;
	x4 : OUT STD_LOGIC;
	x5 : OUT STD_LOGIC;
	x6 : OUT STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : Decoder2para4
	PORT MAP (
-- list connections between master ports and signals
	E => E,
	x1 => x1,
	x2 => x2,
	x3 => x3,
	x4 => x4,
	x5 => x5,
	x6 => x6
	);

-- E
t_prcs_E: PROCESS
BEGIN
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 20000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 30000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 60000 ps;
	E <= '1';
	WAIT FOR 20000 ps;
	E <= '0';
	WAIT FOR 20000 ps;
	E <= '1';
	WAIT FOR 60000 ps;
	E <= '0';
	WAIT FOR 20000 ps;
	E <= '1';
	WAIT FOR 30000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 20000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 20000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 30000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 50000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 20000 ps;
	E <= '1';
	WAIT FOR 20000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 20000 ps;
	E <= '1';
	WAIT FOR 30000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 90000 ps;
	E <= '0';
	WAIT FOR 10000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
	WAIT FOR 40000 ps;
	E <= '1';
	WAIT FOR 10000 ps;
	E <= '0';
WAIT;
END PROCESS t_prcs_E;

-- x1
t_prcs_x1: PROCESS
BEGIN
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 40000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 70000 ps;
	x1 <= '1';
	WAIT FOR 40000 ps;
	x1 <= '0';
	WAIT FOR 40000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 50000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 50000 ps;
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 30000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 30000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
	WAIT FOR 10000 ps;
	x1 <= '1';
	WAIT FOR 10000 ps;
	x1 <= '0';
	WAIT FOR 20000 ps;
	x1 <= '1';
	WAIT FOR 20000 ps;
	x1 <= '0';
WAIT;
END PROCESS t_prcs_x1;

-- x2
t_prcs_x2: PROCESS
BEGIN
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 40000 ps;
	x2 <= '1';
	WAIT FOR 30000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 40000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 30000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 50000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 30000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 40000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 40000 ps;
	x2 <= '0';
	WAIT FOR 50000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 60000 ps;
	x2 <= '0';
	WAIT FOR 30000 ps;
	x2 <= '1';
	WAIT FOR 10000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 50000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 10000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 20000 ps;
	x2 <= '1';
	WAIT FOR 20000 ps;
	x2 <= '0';
	WAIT FOR 30000 ps;
	x2 <= '1';
WAIT;
END PROCESS t_prcs_x2;
END Decoder2para4_arch;
