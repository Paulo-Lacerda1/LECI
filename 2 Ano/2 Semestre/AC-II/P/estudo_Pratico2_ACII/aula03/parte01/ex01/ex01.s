	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	PORTB,	0x6050		# LEITURA 
	.equ 	PORTE,	0x6110

	.equ 	LATB, 0x6060		# ESCRITA
	.equ 	LATE, 0x6120

	.equ 	TRISB, 0x6040 		# TRIS - INTPUT/OUTPUT
	.equ 	TRISE, 0x6100


	.data
	.text 
	.globl main 
main: 			
	lui 	$t0, ADDR_BASE_HI 		# $t0 = 0xBF880000

	# Configuração de RE0 como saída (bit=0)
	lw 	$t1, TRISE($t0) 		# 
	andi 	$t1, $t1, 0xFFFE 		# Forçar saída a 0
	sw 	$t1, TRISE($t0) 		# RE0 OUTPUT

	# Configuração de RB0 como entrada (bit = 1)
	lw 	$t1, TRISB($t0)
	ori 	$t1, $t1, 0x0001		
	sw 	$t1, TRISB($t0) 		# RB0 INPUT
loop:
	#! LER o valor de RB0  $t1 = RB0
	lw 	$t1, PORTB($t0)			# Lê o estado atual de todos os pinos B
	andi 	$t1, $t1, 0x0001 		# Isola o RB0 (bit 0)	
						# agora $t1 contém o valor de RB0

	#! ESCREVER esse valor em RE0  $t2 = RE0 (preservando outros bits) 
	lw 	$t2, LATE($t0)			# Lê estado atual de todos os pinos E
	andi 	$t2, $t2, 0xFFFE		# Limpa apenas RE0 (bit 0)
						# Mantém intacto todos os outros bits
	or 	$t1, $t1, $t2			# !!!!!!!
						# - Coloca o valor de RB0 na posição de RE0
						# - Preserva todos os outros bits de E
	#! ATUALIZAR registo de saída 
	sw 	$t1, LATE($t0) 			#  Escrever resultado de votla no registo
	j 	loop 				
	jr 	$ra
