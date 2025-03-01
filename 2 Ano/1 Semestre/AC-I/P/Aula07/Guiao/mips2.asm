# O argumento da função é passado em $a0
# O resultado é devolvido em $v0

	.data
str1:	.asciiz "Arquitetura de Computadores I"
	.text
	.globl main
	
main:
	addiu $sp,$sp,-4			#reservar espaco na stack
	sw $ra,0($sp)			#resevar o primeiro slot da stack para o $ra
	la $a0, str1			#$a0 = &str1
	jal strlen			#(strlen(str)
	
	move $a0,$v0
	li $v0,1
	syscall				#print_int10 
	
	li $v0,0				#return 0;
	lw $ra,0($sp)			# ???
	addi $sp,$sp,4 			#report a stack
	jr $ra				# } END  
	
strlen:	li $t1,0			#len = 0

while:	lb $t0,0($a0)		#while (*s++ != '\0'=)
	addiu $a0,$a0,1
	beq $t0,'\0', endw	# }
	addi $t1,$t1,1		#len++
	j while 			# }
	
endw:	move $v0,$t1
	jr $ra			#