#Mapa de registos
# i : $t0
# lista : $t1
# lista + i : $t2

	.data
lista:	.space 5
str1:	.asciiz "\nIntroduza um numero: "
	.eqv SIZE,5
	.text
	.align
	.globl main
	
main:	li $t0		# i = 0
	
while:	bge $t0,SIZE,endw 			#while(i<SIZE){
	
	la $a0,str1
	li $v0,4					#print_str()
	syscall
	
	la $t1,lista				#$t1 = endereco da lista
	
	sll $t2,$t0,2				#$t2 = lista[i]
	
	addu $t2,$t1,$t2			 	#lista + 4*i = &lista[i]
	
	sw $t2,0($t2)				#lista[i] = read_int
	
	addi $t0,$t0,1				#i++
				
	j endw					#}
endw:
	jr $ra