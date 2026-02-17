#Mapa_de_registos:
# &a       -> $t0
# &pares   -> $t1
# &impares -> $t2
# i	   -> $t3	
# p 	   -> $t4
# q        -> $t5

	.data
	.eqv	N,10
a:	.space	40
pares:	.space	40
impares: .space	40
virgula:	.asciiz 	","
	.text
	.globl main
	
main:
	la	$t0,a
	la	$t1,pares
	la	$t2,impares
	li	$t3,0
	li	$t4,0
	li	$t5,0
	
for1:
	bge	$t3,N,endfor1
	
	li	$v0,5
	syscall
	sw	$v0,0($t0)			
	
	addiu 	$t0,$t0,4			
	addi	$t3,$t3,1			#i++
	j for1
endfor1:
	li	$t3,0				#i = 0;
	la 	$t0,a
for2:
	bge	$t3,N,endfor2
	lw 	$t6,0($t0)			# $t6 = [i]
	li 	$t7,2				
# verifica se é par:
	rem	$t8,$t6,$t7			#$t8 = a[i] % 2
	bne	$t8,$0,impar	#se for impar, salta para a label impar
	sw	$t6,0($t1)
	addiu	$t1,$t1,4
	addi 	$t4,$t4,1         # p++

	j e_par			#salta para o final, para nao acrescentar no array impares		
impar:	
	sw	$t6,0($t2)
	addiu 	$t2,$t2,4
	addi    $t5,$t5,1
e_par:
	addiu 	$t0,$t0,4			
	addi 	$t3,$t3,1			#i++
	j for2
endfor2:
	
	li	$t3,0			#i=0
	la	$t1,pares
	la	$t2,impares
for_pares:
	bge	$t3,$t4,end_for_pares
	li	$v0,1
	lw	$a0,0($t1)
	syscall
	
	li 	$v0,4
	la 	$a0,virgula
	syscall
	
	addiu	$t1,$t1,4
	addi	$t3,$t3,1			#i++
	
	j	for_pares
	
end_for_pares:

	li	$t3,0
	li  	$v0,11
	li  	$a0,'\n'	
	syscall					#paragrafo
	
for_impares:
	bge	$t3,$t5,end_for_impares
	
	li	$v0,1
	lw	$a0,0($t2)
	syscall

	li	$v0,4
	la 	$a0,virgula
	syscall
	
	addiu	$t2,$t2,4
	addi 	$t3,$t3,1			# i ++
	
	j	for_impares
end_for_impares:

	li	$v0,10
	syscall
	
	