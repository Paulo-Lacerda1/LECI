#Mapa De Registos
# $t0 -> val
# $t1 -> i
# $t2 -> sBits

	.data
str1:	.asciiz "No set bits found\n"
	.text
	.globl main
	
main:
	li	$t2,0		#sBits = 0;
	
	li 	$v0,5
	syscall
	move	$t0,$v0		#read_int()
	
	li	$t1,0		# i = 0;
	li	$t3,0x20
	
for:	
	bge	$t1,$t3,endfor
	andi	$t4,$t0,1	#val & 1
	
if1:
	bne	$t4,1,endif1	
	addi	$t2,$t2,1	#sBits++
		
endif1:	
	srl	$t0,$t0,1
	addi 	$t1, $t1, 1   # i++

	j for
endfor:

if2:	
	bne 	$t2,0, else
	li	$v0,4
	la	$a0,str1
	syscall			#print_str()
	j endif2
else:
	li	$v0,1
	move 	$a0,$t2
	syscall
endif2:

	li 	$v0,10
	syscall
