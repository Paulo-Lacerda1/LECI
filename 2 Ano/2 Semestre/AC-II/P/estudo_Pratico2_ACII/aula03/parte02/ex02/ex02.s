	.equ 	ADDR_BASE_HI, 0xBF88

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
	.data 
	.text 
	.globl main 

#* Contador binário decrescente de 4 bits (módulo 16) atualizado com frequência de 4Hz 
#* 4Hz = 250 ms
main: 		
	addiu 	$sp, $sp, -12 		# prologo
	sw 	$ra, 0($sp)
	sw 	$s0, 4($sp)
	sw 	$s1, 8($sp)

	lui 	$t0, ADDR_BASE_HI 		

	# RE4 - RE1 SAÍDAS -> forçar 0 
	lw 	$t1, TRISE($t0) 
	andi 	$t1, 0xFFE1
	sw 	$t1, TRISE($t0) 			# RE4 to RE1 set to OUTPUT

	# RB3 ou RB2 ou RB1 como entradas
	# Neste caso não é necessário

	li 	$s0, 15 				# int counter = 15;
loop:
	lw 	$t1, LATE($t0)
	andi 	$t1, $t1, 0xFFE1 			# Forçar os bits a zero
	sll 	$t3, $s0, 1
	or 	$t1, $t1, $t3
	sw 	$t1, LATE($t0)

	li 	$a0, 250
	jal 	delay 

	addi 	$s0, $s0, -1
	andi 	$s0, $s0, 0x000F
	j 	loop 


	addiu 	$sp, $sp, -12 		# epilogo
	lw 	$ra, 0($sp)
	lw 	$s0, 4($sp)
	lw 	$s1, 8($sp)

	jr 	$ra

########################################################
delay: 					# void delay(unsigned int ms){
	li 	$v0, 12 		# 	
	syscall 			# 	resetCoreTimer();
delayWhile:				#	while(readCoreTimer() < K * ms)
	li 	$v0, 11 		#
	syscall 			# 		readCoreTimer();
	mulou 	$t3, $a0, 20000 	# 		K * ms
	bge 	$v0, $t3, delayEndWhile #		if(readCoreTimer() < K * ms) break;
	j 	delayWhile		# 	}
delayEndWhile: 				#
	jr 	$ra 			# }
