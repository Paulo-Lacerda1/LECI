	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	RESET_CORE_TIMER, 12
	.equ 	READ_CORE_TIMER, 11

	.equ 	LATB, 0x6060 	
	.equ 	LATE, 0x6120	
	.equ 	LATD, 0x60E0 	

	.equ 	TRISB, 0x6040 	
	.equ 	TRISE, 0x6100
	.equ 	TRISD, 0x60C0

	.equ 	PORTB, 0x6050 	
	.equ 	PORTE, 0x6110 	
	.equ 	PORTD, 0x60D0
	.data
	.text
	.globl main


#* Contador em anel de 4 bits (ring counter) com deslocamento
#* à esquerda ou à direita dependendo do valor lido do porto RB1
#! Se RB1=1, deslocamento à esquerda (0001, 0010, 0100, 1000, 0001) 
#! Frequência de atualização de 3Hz 


main: 					#
	lui 	$t0, ADDR_BASE_HI

	# LED
	# Definir RE-3 to RE-0 -> SAÍDA (0)
	lw 	$t1, TRISE($t0)
	andi 	$t1, $t1, 0xFFF0 
	sw 	$t1, TRISE($t0)

	# SWITCH
	# Definir RB1 -> ENTRADA (1)
	lw 	$t1, TRISB($t0)
	ori 	$t1, $t1, 0x0002 	
	sw 	$t1, TRISB($t0)

	li 	$t2, 1 				# int leftCounter = 1;
	li 	$t3, 8 				# int rightCounter = 15;
case1:	
	lw 	$t1, PORTB($t0) 		#
	andi 	$t1, $t1, 0x0002 

	beq 	$t1, 2, case2 			

	lw 	$t1, LATE($t0) 			# 
	andi 	$t1, $t1, 0xFFF0 			# Resetar os bits 
	or 	$t1, $t2, $t1			# Merge counter w/ LATE value
	sw 	$t1, LATE($t0) 			# Update LATE register


	li 	$v0, RESET_CORE_TIMER
	syscall 
wait1: 	li 	$v0, READ_CORE_TIMER
	syscall 
	blt 	$v0, 6666666, wait1

	sll 	$t2, $t2, 1 			# leftCounter << 1 
	andi 	$t2, $t2, 0x000F 			# counter MOD 16
	beq 	$t2, $zero, reset_left
	j 	continue_left
reset_left:
   	li 	$t2, 1  # Reinicia com 0001
continue_left:	 
	j 	case1 


case2:
	lw 	$t1, LATE($t0) 			# 
	andi 	$t1, $t1, 0xFFF0 			# Resetar os bits 
	or 	$t1, $t3, $t1			# Merge counter w/ LATE value
	sw 	$t1, LATE($t0) 			# Update LATE register

	li 	$v0, RESET_CORE_TIMER
	syscall 
wait2: 	li 	$v0, READ_CORE_TIMER
	syscall 
	blt 	$v0, 6666666, wait2

	srl 	$t3, $t3, 1
	andi 	$t3, $t3, 0x000F

	beq 	$t3, $zero, reset_right
	j 	continue_right
reset_right:
   	li 	$t3, 8  # Reinicia com 1000 
continue_right:
	j 	case1

	jr	 $ra
