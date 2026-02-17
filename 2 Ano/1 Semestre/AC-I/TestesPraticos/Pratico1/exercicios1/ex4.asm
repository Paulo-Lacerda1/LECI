#Mapa  
# $t0 -> &str
# $t1 -> ms
# $t2 -> pf
# $t3 -> temp (*pf)
# $t4 -> auxiliar

	.data
str:	.asciiz "Teste-Pratico-1"

	.text
	.globl main
main:	
	la	$t0, str
	move	$t1, $t0
	addi	$t2, $t1, -1

# -------- DO WHILE --------
while:
	addiu 	$t2, $t2, 1
	lb	$t3, 0($t2)
	bne	$t3, $zero, while		# enquanto (*pf != '\0')
endw:	

# -------- WHILE (ms < pf) --------
while2:
	bge	$t1, $t2, endw2
	lb    	$t3, 0($t1)         # $t3 = *ms

	# if (*ms < '0' || *ms > 'z')
	blt	$t3, '0', iftrue
	bgt	$t3, 'z', iftrue
	j	else

iftrue:
	li	$t4, '?'
	sb	$t4, 0($t1)		# *ms = '?'
	j	endif

else:
	xori	$t3, $t3, 0x15
	sb	$t3, 0($t1)

endif:	
	addiu 	$t1, $t1, 1		# ms++
	j	while2
endw2:

	li 	$v0, 10
	syscall
