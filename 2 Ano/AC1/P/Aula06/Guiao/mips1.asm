# Mapa de registos
# i : $t0 

	.data
	.eqv SIZE,3
str1:	.asciiz "Array"
str2:	.asciiz "de"
str3:	.asciiz "ponteiros"
array:	.word str1,str2,str3
	.text
	.globl main
	
main:	li $t0,0
	la $t2, array		#$t2 = &array
	
while:	bge $t0,SIZE,endw	#while(i<SIZE){	
	sll $t1,$t0,2		#$t1 = i *4
	addu $t3,$t2,$t1		#$t3 = $(array[i])	

	lw $t3,0($t3)		#$t3 = array[i]
	
	move $a0,$t3		#$a0 = array[i]
	li $v0,4
	syscall			#print_string
	
	li $a0,'\n'
	li $v0,11
	syscall			#print_char
	
	addi $t0,$t0,1		#i++
	j while			# }
endw:
	jr $ra