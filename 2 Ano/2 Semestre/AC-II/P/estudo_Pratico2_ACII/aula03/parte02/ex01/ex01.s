	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	LATB, 0x6060 	#*Escrever valores em
	.equ 	LATE, 0x6120	# pinos configurados como
	.equ 	LATD, 0x60E0 	#! saída

	.equ 	TRISB, 0x6040 	# Definir INPUT/OUTPUT
	.equ 	TRISE, 0x6100
	.equ 	TRISD, 0x60C0

	.equ 	PORTB, 0x6050 	#* Ler o valor de pinos 
	.equ 	PORTE, 0x6110 	#! de entrada
	.equ 	PORTD, 0x60D0
	
	
	.data 
	.text 
	.globl main 

main: 			
	addiu 	$sp, $sp, -12 		# prologo
	sw 	$ra, 0($sp)
	sw 	$s0, 4($sp)
	sw 	$s1, 8($sp)

	# RE4 to RE1 = LEDs
	#* Configurar como saída
	lui 	$s0, ADDR_BASE_HI 	# $s0 = 0xBF88
	lw 	$t1, TRISE($s0) 	# 
	andi 	$t1, $t1, 0xFFE1 	# RE4 - RE1
	sw 	$t1, T RISE($s0)

	# RB3 to RB0 = SWITCHs 
	#* Configurar como entrada
	lw 	$t1, TRISB($s0)
	ori 	$t1, $t1, 0x000F 	# RE3 - RE0
	sw 	$t1, TRISB($s0)

	# Inicializar a variável de contagem 
	li 	$s1, 0 			# int counter = 0;

loop: 
	# Atualizar os portos de saida com o valor da variavel de contagme 
	lw 	$t1, LATE($s0)  	# Escrever valores em pinos configurados como saída
	andi 	$t1, $t1, 0xFFE1 	# Reset Bits 4-1
	sll 	$t3, $s1, 1 
	or 	$t1, $t1, $t3 		# merge LATE value with counter
	sw 	$t1, LATE($s0) 	

	li 	$a0, 1000
	jal 	delay 

	addi 	$s1, $s1, 1
	andi 	$s1, $s1, 0x000F
	j	loop	


	lw 	$ra, 0($sp)
	lw 	$s0, 4($sp)
	lw 	$s1, 8($sp)
	addiu 	$sp, $sp, 12 		# prologo

	jr 	$ra 


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
