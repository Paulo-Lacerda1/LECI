#Mapa de registos
#num : $t0
#p   : $t1
#*p  : $t2

	.data
	.eqv SIZE, 20
str:	.space 21
	.text 
	.globl main

main:	li $t0,0			#int num = 0
	
	la $a0,str
	li $a1,SIZE
 	li $v0,8
        syscall     	         # read_string(str,SIZE)
	
	la $t1, str		#p = str
	
while:	lb $t2, 0($t1)		# *p 
	beq $t2, '\0',endw	#while(*p != '\0')

if:	blt $t2,'0',endif	#(*p >= '0') && 
	bgt $t2,'9', endif	#(*p <= '9') )
	addi $t0,$t0,1		#num++

endif:
	addi $t1,$t1,1		#p++
	
	j while			# }
endw:
	li $v0,1
	move $a0,$t0
	syscall
	
	jr $ra
	
	