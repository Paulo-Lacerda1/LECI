	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	LATB, 0x6060 	# Escrita
	.equ 	LATE, 0x6120
	.equ 	LATD, 0x60E0

	.equ 	TRISB, 0x6040 	# Definir INPUT/OUTPUT
	.equ 	TRISE, 0x6100
	.equ 	TRISD, 0x60C0

	.equ 	PORTB, 0x6050 	# Leitura 
	.equ 	PORTE, 0x6110
	.equ 	PORTD, 0x60D0
		
	.data
	.text 
	.globl main

main: 				
	lui 	$t0, ADDR_BASE_HI

	# PORTO DE ENTRADA RD8 (INT 1)
	lw 	$t1, TRISD($t0)
	ori 	$t1, $t1, 0x0100
	sw 	$t1, TRISD($t0)

	# PORTO DE SAIDA RE0 
	lw 	$t1, TRISE($t0)
	andi 	$t1, $t1, 0xFFFE
	sw 	$t1, TRISE($t0)

loop: 					#
	#! LER O VALOR DO RD8
	lw 	$t1, PORTD($t0)
	andi 	$t1, $t1, 0x0100
	srl 	$t1, $t1, 8

	#! ESCREVER EM RE0
	lw 	$t2, LATE($t0)
	andi 	$t2, $t2, 0xFFFE
	or 	$t1, $t1, $t2 		

	sw  	$t1, LATE($t0)

	j 	loop
	jr 	$ra 

