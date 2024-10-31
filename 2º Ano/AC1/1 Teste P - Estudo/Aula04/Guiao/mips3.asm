#Mapa de registos
# p       : $t0
# pultimo : $t1
# *p      : $t2
# soma    : $t3

	.data	
	.eqv SIZE, 4
	.eqv print_int10, 1
array:	.word 7692,23,5,234
	.text
	.globl main
	
main:	li $t3,0			#soma=0

	la $t0,array		#p=array

	li $t4,SIZE
	addi $t4,$t4,-1
	sll  $t4,$t4,2
	la $t1, array		#pultimo = array
	addu $t1,$t1,$t4		#pultimo = pultimo + (SIZE-1)	
	
while:	bgt $t0,$t1, endw	# while (p <= pultimo){
	lw  $t2,0($t0)		#*p	
	add $t3,$t3,$t2		#soma+=(*p)
	addiu $t0,$t0,4		#p++

	j while			# }
endw:
	li $v0,print_int10
	move $a0,$t3
	syscall

	jr $ra
