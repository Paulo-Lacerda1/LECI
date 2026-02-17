#Mapa_de_registos:
# &a 	-> $t0
# i     -> $t1
# n_pos -> $t2
# n_neg -> $t3	
	.data
a: 	.space 40
	.eqv N,10
str1:	.asciiz "len(n_pos) = "
str2:	.asciiz "\nlen(n_neg) = "
	.text
	.globl main 
	
main:
	la 	$t0,a
	li	$t1,0	
	li	$t2,0
	li	$t3,0
	
for1:
	bge	$t1,N,endfor1		#while(i<N) {
	
	li 	$v0,5
	syscall					#read_int()
	sw 	$v0,0($t0)
	addiu 	$t0,$t0,4	
				
	addi	$t1,$t1,1			#i++ 
	j	for1			#}
endfor1:
	
	li 	$t1,0			# i =0
	la 	$t0,a

for2:
	bge	$t1,N,endfor2
	lw	$t4,0($t0)		# $t4 = a[i]
	
if:	
	bltz	$t4,elseif
	addi	$t2,$t2,1		#n_pos++		
	j endif
	
elseif:
	bgtz	$t4,endif	
	addi	$t3,$t3,1		#n_neg
endif:	
	
	addiu 	$t0,$t0,4
	addi	$t1,$t1,1
	j	for2
endfor2:

	li	$v0,4
	la	$a0,str1
	syscall
	
	li	$v0,1
	move	$a0,$t2
	syscall

	li	$v0,4
	la	$a0,str2
	syscall
		
	li	$v0,1
	move	$a0,$t3
	syscall

	li	$v0,10
	syscall
