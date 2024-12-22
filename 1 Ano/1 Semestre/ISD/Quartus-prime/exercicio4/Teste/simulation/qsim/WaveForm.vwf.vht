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
-- Generated on "12/21/2023 10:16:50"
                                                             
-- Vhdl Test Bench(with test vectors) for design  :          ISDSim2
-- 
-- Simulation tool : 3rd Party
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY ISDSim2_vhd_vec_tst IS
END ISDSim2_vhd_vec_tst;
ARCHITECTURE ISDSim2_arch OF ISDSim2_vhd_vec_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL CLOCK : STD_LOGIC;
SIGNAL Q : STD_LOGIC;
SIGNAL RESET_L : STD_LOGIC;
SIGNAL X : STD_LOGIC;
COMPONENT ISDSim2
	PORT (
	CLOCK : IN STD_LOGIC;
	Q : OUT STD_LOGIC;
	RESET_L : IN STD_LOGIC;
	X : IN STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : ISDSim2
	PORT MAP (
-- list connections between master ports and signals
	CLOCK => CLOCK,
	Q => Q,
	RESET_L => RESET_L,
	X => X
	);

-- CLOCK
t_prcs_CLOCK: PROCESS
BEGIN
LOOP
	CLOCK <= '0';
	WAIT FOR 10000 ps;
	CLOCK <= '1';
	WAIT FOR 10000 ps;
	IF (NOW >= 1000000 ps) THEN WAIT; END IF;
END LOOP;
END PROCESS t_prcs_CLOCK;

-- RESET_L
t_prcs_RESET_L: PROCESS
BEGIN
	RESET_L <= '0';
	WAIT FOR 60000 ps;
	RESET_L <= '1';
	WAIT FOR 60000 ps;
	RESET_L <= '0';
	WAIT FOR 30000 ps;
	RESET_L <= '1';
	WAIT FOR 30000 ps;
	RESET_L <= '0';
	WAIT FOR 30000 ps;
	RESET_L <= '1';
	WAIT FOR 30000 ps;
	RESET_L <= '0';
	WAIT FOR 60000 ps;
	RESET_L <= '1';
	WAIT FOR 60000 ps;
	RESET_L <= '0';
	WAIT FOR 30000 ps;
	RESET_L <= '1';
	WAIT FOR 30000 ps;
	RESET_L <= '0';
	WAIT FOR 30000 ps;
	RESET_L <= '1';
	WAIT FOR 30000 ps;
	RESET_L <= '0';
WAIT;
END PROCESS t_prcs_RESET_L;

-- X
t_prcs_X: PROCESS
BEGIN
	X <= '0';
	WAIT FOR 30000 ps;
	X <= '1';
	WAIT FOR 30000 ps;
	X <= '0';
	WAIT FOR 60000 ps;
	X <= '1';
	WAIT FOR 60000 ps;
	X <= '0';
	WAIT FOR 30000 ps;
	X <= '1';
	WAIT FOR 30000 ps;
	X <= '0';
	WAIT FOR 30000 ps;
	X <= '1';
	WAIT FOR 30000 ps;
	X <= '0';
	WAIT FOR 60000 ps;
	X <= '1';
	WAIT FOR 60000 ps;
	X <= '0';
	WAIT FOR 30000 ps;
	X <= '1';
	WAIT FOR 30000 ps;
	X <= '0';
WAIT;
END PROCESS t_prcs_X;
END ISDSim2_arch;
