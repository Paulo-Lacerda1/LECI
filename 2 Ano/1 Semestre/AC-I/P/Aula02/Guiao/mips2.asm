	.data
	.text
	.globl main
main: 	li $t0,0x12345678 	# instru��o virtual (decomposta
			  	# em duas instru��es nativas)
	sll $t2,$t0,1 		# $t2 = $t0 * 2
	srl $t3,$t0,1 		# $t3 = $t0 / 2
	sra $t4,$t0,1 		# $t4 = $t0 / 2 (preservando o bit + significativo)
	
	jr $ra 			# fim do programa