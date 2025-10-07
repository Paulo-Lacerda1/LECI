#Regist Map 
# $t0 = str
# $t1 = SIZE
# $t2 = num 
# $t3 = i

	.data
str:	.space 21
	.text
	.globl main
main:
	la $a0,str
	li $a1,20 
	li $v0, 8
	syscall		#read_string()
	
	li $t2,0		#num = 0
	li $t3,0		#i = 0
	
while:	lb $t7, 0($a0)        # carregar o byte atual da string
    	beq $t7, '\0', endw    
if:
    	blt $t7, '0', endif
    	bgt $t7, '9', endif

    	addi $t2, $t2, 1      # num++
	
endif:
	addi $a0,$a0,1	#str[i+1]
	j while
endw:
	
	move $a0,$t2
	li $v0,1
	syscall		#print_int10
	
	jr $ra
