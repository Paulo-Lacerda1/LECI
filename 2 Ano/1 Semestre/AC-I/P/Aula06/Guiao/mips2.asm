# Mapa de Registos
# $t0 - p
# $t1 - pUltimo
# $t2 - *p
# $t5 - aux1

	.data
	.eqv SIZE,3
str1:	.asciiz "Array"
str2:	.asciiz "de"
str3:	.asciiz "ponteiros"
array:	.word str1,str2,str3
	.text
	.globl main
	
main:	
	la $t0, array		#p = &array[0]
	li $t5,SIZE			#aux1 = SIZE
	sll $t5,$t5,2		#aux1 = (size)*4
	add $t1,$t0,$t5		#pultimo = array + SIZE
	
while:	bge $t0,$t1,endw		#while (p<pultimo) {
	
	lw $t2,0($t0)		#$t2 = *p (conteudo apontado)
	
	move $a0,$t2
	li $v0,4
	syscall			#print_str()
	
	li $a0,'\n'
	li $v0,11
	syscall			#print_char
	
	addi $t0,$t0,4		#p++
	
	j while			# }
	
endw:
	jr $ra