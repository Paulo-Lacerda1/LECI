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
	.text
	.globl main

#* Contador binário módulo 16 crescente/decrescente cujo comportamaento depende
#* do valor lido do porto RB3: se RB3 = 1, contador crescente;
#* Caso contrário contador decrescente;
#* Frequencia de atualização de 2Hz.
main: 		
	addiu 	$sp, $sp, -16			# Prologo
	sw 	$ra, 0($sp)
	sw 	$s0, 4($sp)
	sw 	$s1, 8($sp)
	sw 	$s2, 12($sp)

	lui 	$s0, ADDR_BASE_HI

	# Colocar RE3 to RE0 -> como SAÍDA (bit 0)
	lw 	$t1, TRISE($s0) 		#
	andi 	$t1, $t1, 0xFFF0
	sw 	$t1, TRISE($s0) 		# RE3 to RE0 as OUTPUT

	# Colocar RB3 -> como ENTRADA (bit 1)
	lw 	$t1, TRISB($s0)
	ori 	$t1, $t1, 0x0008 
	sw 	$t1, TRISB($s0)  		# RB3 as INPUT

	li 	$s1, 0				# int increasingCounter = 0;
	li 	$s2, 15				# int decreasingCounter = 15;
case1:						# 
	lw 	$t1, PORTB($s0) 		#
	andi 	$t1, $t1, 0x0008 		# Isolar o bit do RB3
	beq 	$t1, 8, case2			# Caso seja igual a 1 quer dizer
						# que o switch está ligado, contador crescente; 
	
	lw 	$t1, LATE($s0)			#
	andi 	$t1, $t1,  0xFFF0 		# Reset bits 3-0
	or 	$t1, $s1, $t1 			# Merge cunter w/ LATE value
	sw 	$t1, LATE($s0) 			# Update LATE register

	li 	$a0, 500
	jal delay

	addiu 	$s1, $s1, 1 			# increasingCounter++;
	andi 	$t2, $t2, 0x000F 		# counter MOD 16
	j 	case1
case2:
	lw 	$t1, LATE($s0)
	andi 	$t1, $t1, 0xFFF0
	or 	$t1, $s2, $t1
	sw 	$t1, LATE($s0)

	li 	$a0, 500
	jal 	delay 

	addiu 	$s2, $s2, -1
	andi 	$s2, $s2, 0x000F
	j 	case1 

	addiu 	$sp, $sp, 12			# Epilogo
	lw 	$ra, 0($sp)
	lw 	$s0, 4($sp)
	lw 	$s0, 8($sp)

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

