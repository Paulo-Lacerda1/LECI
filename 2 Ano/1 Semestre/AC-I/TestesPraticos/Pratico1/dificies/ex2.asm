#Mapa
# $t0 -> &a
# $t1 -> &b
# $t2 -> pa
# $t3 -> pb
	.data	
	.eqv	SIZE,6
a:	.word 	-3,4,-1,9,0,7
vir:	.asciiz	", "
	.align	2
c:	.space	24
	.text
	.globl main

main:
	la	$t2,a			# $t2 = &a
	la	$t3,c			# $t3 = &c
	li	$t4,SIZE
	sll 	$t4,$t4,2		# SIZE *4
	addu	$t4,$t4,$t2		# a[N]
	
	
while:
	bgeu	$t2,$t4,endw
	lw	$t5,0($t2)		# $t5= *pa
if:	
	ble	$t5,$0,endif
	sw	$t5,0($t3)		# *pb = *pa
	addiu	$t3,$t3,4		# pb++
		
endif:	
	
	addiu	$t2,$t2,4		#pa++
	j	while
endw:
	la	$t3,c
	li	$t4,SIZE
	sll 	$t4,$t4,2		# SIZE *4
	addu	$t4,$t4,$t3		# b + N
for:
	bgeu	$t3,$t4,endfor
	
	li	$v0,1
	lw	$a0,0($t3)		# $t3 = &c
					# $a0 = c
	syscall
	
	li	$v0,4			           
	la	$a0,vir	
	syscall
	
	addiu	$t3,$t3,4		# pb++
	j 	for
endfor:
	
	jr	$ra
