#Mapa
# $t0 -> &in
# $t1 -> &out
# $t2 -> k 
# $t3 -> min1
# $t4 -> min2
# $t5 -> 1 << 31	
	
	.data
in:	.word	56,11,5,72,11,-15
out:	.space 	8
	.eqv	SIZE,6
	.text
	.globl main
		
main:
	li	$t5,1
	sll	$t5,$t5,31
	nor	$t3,$t5,$t5	#t3 = min1
	move 	$t4,$t3		#min2 = min1
	
	li	$t2,0		#k = 0
	
	la	$t0,in
	la	$t1,out
for:		
	bge	$t2,SIZE,endfor
	sll	$t7,$t2,2	# $t6 = k * 4
	addu	$t6,$t0,$t7	# $t6 = &in[k]
	lw	$t8,0($t6)	# $t8 = in[k]

if:
	bge $t8,$t3,else
	move 	$t4,$t3		# min2 = min1
	move	$t3,$t8		# min1 = in[k];
	j endif
else:
	
if2:
	bge	$t8,$t4,endif2
	ble	$t8,$t3,endif2
	move	$t4,$t8

endif2:

endif:
	addi 	$t2,$t2,1	# k++
	j for	
endfor:
	 
	sw	$t3,0($t1)	# out[0] = min1;
	sw	$t4,4($t1)	# out[1] = min2;
	li	$t2,0		#k = 0
for2:
	bge 	$t2,2,endfor2
	
	li	$v0,1
	lw	$a0,0($t1)
	syscall
	
	addiu	$t1,$t1,4
	addi	$t2,$t2,1
	
	li	$v0,11
	li	$a0,'\n'
	syscall
	j for2
endfor2:


	li	$v0,10
	syscall
	