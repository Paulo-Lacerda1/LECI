#Mapa 
# $t0 -> &a
# $t1 -> i 	
# $t2 -> pairs


	.data
	.eqv N,10
a:	.word 	1,1,3,4,4,4,5,6,6,9
str1:	.asciiz "Pares iguais = "
	.text
	.globl main
	
main:
	la 	$t0,a
	li 	$t1,0		# i =0
	li 	$t2,0
	li	$t3,N
	addi	$t3,$t3,-1	# $t3 = N -1
while:
	bge	$t1,$t3,endw
	
	sll	$t4,$t1,2	#i * 4 	
	addu	$t4,$t4,$t0	# &a[i]
				
	addi	$t5,$t1,1	# i+1
	sll	$t5,$t5,2	
	addu	$t5,$t5,$t0	# &a[i+1]
	
	lw	$t6,0($t4)	#a[i]
	lw	$t7,0($t5) 	#a[i+1]
				
if:
	bne	$t6,$t7,endif
	addi 	$t2,$t2,1	#pairs++
endif:
				
	addi	$t1,$t1,1	#i++									
	j	while
endw:	

	li 	$v0,4
	la	$a0,str1
	syscall
	
	li	$v0,1
	move	$a0,$t2
	syscall

	jr 	$ra