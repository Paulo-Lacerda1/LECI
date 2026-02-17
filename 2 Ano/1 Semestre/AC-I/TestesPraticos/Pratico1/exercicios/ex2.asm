#Mapa_de_registos:
# &a 	-> $t0
# max  -> $t1
# min -> $t2
# soma -> $t3	
# i 	->$t4
	.data
	.eqv 	N,12
a:	.space	48		# 4 *12
max:	.asciiz	" Max: "
min:	.asciiz	" \nMin: "
soma:	.asciiz	" \nSoma: "
	.text
	.globl main
	
main:	
	la	$t0,a
	li	$t3,0			# soma = 0;
	li	$t1,0x80000000		# max = -2147483648;
	li 	$t2,0x7FFFFFFF		# min = 2147483647;
	li	$t4,0			#i = 0;

for:	
	bge	$t4,N,endfor
	
	li	$v0,5
	syscall
	move	$t5,$v0
	
	
	sw	$t5,0($t0)		# $t5 = a[i]
	
	add	$t3,$t3,$t5		#soma = soma + a[i]
	
if1:
	ble	$t5,$t1,endif1
	move	$t1,$t5
endif1:	

if2:
	bge	$t5,$t2,endif2
	move	$t2,$t5
endif2:		
	addiu 	$t0,$t0,4		
	addi 	$t4,$t4,1		#i++
	j for
endfor:	

	li 	$v0,4
	la	$a0,max
	syscall
	
	li	$v0,1
	move	$a0,$t1
	syscall
	
	li 	$v0,4
	la	$a0,min
	syscall
	
	li	$v0,1
	move	$a0,$t2
	syscall
	
	li 	$v0,4
	la	$a0,soma
	syscall
	
	li 	$v0,1
	move	$a0,$t3
	syscall
	
	li 	$v0,10
	syscall			#exit
