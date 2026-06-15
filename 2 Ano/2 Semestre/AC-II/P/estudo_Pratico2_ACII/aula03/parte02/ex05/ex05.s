  aa
#* Contador Johnson de 4 bits 
#* (sequência: 0000, 0001, 0011, 0111, 1111, 1110, 1100, 1000, 0000, 0001)
#* Com uma frequência de atualização de 1.5Hz; para implementar este contador observe
#* que o bit a introduzir na posição menos significado quando se faz o deslocamento à esquerda
#* corresponde ao valor negado que o bit mais significativo tinha na iteração anterior

main:
	lui 	$t0, ADDR_BASE_HI 		# 	$t0 = 0xBF88

	# Colocar RE0-3 como SAÍDAS (=0)
	lw 	$t1, TRISE($t0) 		# 
	andi 	$t1, $t1, 0xFFF0 		#
	sw 	$t1, TRISE($t0) 		#	 RE0 to 3 as OUTPUTº

	li 	$t2, 0 				# 	$t2 = 0000
loop:
	lw 	$t1, LATE($t0)
	andi 	$t1, $t1, 0xFFF0
	or 	$t1, $t2, $t1
	sw 	$t1, LATE($t0)

	srl 	$t3, $t2, 3
	andi 	$t3, $t3, 1
	xori 	$t3, $t3, 1

	sll 	$t2, $t2, 1
	andi 	$t2, $t2, 0xF

	or 	$t2, $t2, $t3

	# Frequencia de 1,5Hz
	li 	$v0, 12				
	syscall 
wait: 	li 	$v0, 11
	syscall
	blt 	$v0, 13333333, wait		# 

	j 	loop 				#

	jr 	$ra