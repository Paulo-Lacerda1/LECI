
	.equ 	ADDR_BASE_HI, 0xBF88

	.equ 	LATB, 0x6060 			# Escrever valores em pinos configurados como saída
	.equ 	LATE, 0x6120	
	.equ 	LATD, 0x60E0 	

	.equ 	TRISB, 0x6040 			# Configura o pino como OUTPUT ou INPUT 
	.equ 	TRISE, 0x6100
	.equ 	TRISD, 0x60C0

	.equ 	PORTB, 0x6050 			# Ler valores de pinos de entrada 
	.equ 	PORTE, 0x6110 	
	.equ 	PORTD, 0x60D0
	.data
	.text
	.globl main

#* Contador de 4 bits com um comportamento idêntico ao contador de Johnson
#* mas com deslocamento à direita 
#* 0000, 1000, 1100, 1110, 1111, 0111, 0011, 0001, 0000, 1000 
#* frequência de atualização de 1.5Hz


main: 
	lui 	$t0, ADDR_BASE_HI

	# COLOCAR RE0-3 COMO OUTPUT (=0)
	lw 	$t1, TRISE($t0)
	andi 	$t1, $t1, 0xFFF0
	sw 	$t1, TRISE($t0)

	li 	$t2, 0
loop:
	lw 	$t1, LATE($t0)
	andi 	$t1, $t1, 0xFFF0
	or 	$t1, $t2, $t1
	sw 	$t1, LATE($t0)

	# Negar o LSB e acrescentá-lo à esquerda

	andi 	$t3, $t2, 1 		# Isolar o LSB
	xori 	$t3, $t3, 1 
	sll 	$t3, $t3, 3
	srl 	$t2, $t2, 1
	or 	$t2, $t2, $t3

	# Frequência 1.5Hz
	li 	$v0, 12				
	syscall 
wait: 	li 	$v0, 11
	syscall
	blt 	$v0, 13333333, wait		# 

	j 	loop

	jr 	$ra
