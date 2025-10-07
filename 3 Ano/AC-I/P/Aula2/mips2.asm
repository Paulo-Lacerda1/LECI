#Mapa:
# $t0 = a 
# $t1 = b	
# $t2 = a+b
	
	.data
str1:	.asciiz "Introduza 2 numeros "
str2:	.asciiz "A soma dos dois numeros e' : "
	.text
	.globl main
	
main: 	li $v0, 4
	la $a0, str1
	syscall		#print_string
	
	
	li $v0, 5 
	syscall
	move $t0, $v0		# a = read_int()
	
	li $v0, 5 
	syscall
	move $t1, $v0		# b = read_int()
	
	li $v0, 4
	la $a0, str2
	syscall		#print_string
	
	add $t2, $t0, $t1
	
	li $v0, 1
	move $a0, $t2
	syscall		#print_int10
	
	jr $ra