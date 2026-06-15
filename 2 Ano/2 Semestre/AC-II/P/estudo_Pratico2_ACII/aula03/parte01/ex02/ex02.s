	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	LATB, 0x6060 	# Escrita
	.equ 	LATE, 0x6120

	.equ 	TRISB, 0x6040 	# Definir INPUT/OUTPUT
	.equ 	TRISE, 0x6100

	.equ 	PORTB, 0x6050 	# Leitura 
	.equ 	PORTE, 0x6110
		
	.data
	.text
	.globl main
main: 					# 	Configurar RE0 como OUTPUT; RB0 como INPUT
	lui 	$t0, ADDR_BASE_HI 	# 	$t0 = 0xBF88	

	# Configuração de *RE0 como sáida*
	lw 	$t1, TRISE($t0) 	#	
	andi 	$t1, $t1, 0xFFFE	#	Forçar saída a 0
	sw 	$t1, TRISE($t0) 	# 	RE0 OUTPUT

	# Configuração de *RB0 como entrada*
	lw 	$t1, TRISB($t0)
	ori 	$t1, $t1, 0x0001	# 	último bit  a 1
	sw 	$t1, TRISB($t0) 	#	RB0 INPUT

loop:
	#! LER o valor de RB0
	lw 	$t1, PORTB($t0)		# 
	andi 	$t1, $t1, 0x0001	# $t1 tem o valor de RB0

	lw 	$t2, LATE($t0)
	andi 	$t2, $t2, 0xFFFE 	# isola o bit 0

	or 	$t1, $t1, $t2 
	xor 	$t1, $t1, 1 
	sw 	$t1, LATE($t0)

	j 	loop
	jr 	$ra 

