#soma > $t0
#value > $t1
#i > $t2	
	
	.data
str1: 	.asciiz "Introduza um numero: "
str2:	.asciiz "Valor ignorado\n"
str3: 	.asciiz " A soma dos positivos e': "
	.text 
	.globl main			
main:					# void main(void {
	li $t2, 0			# i = 0
	li $t0, 0			#soma = 0
			
while:
	bge $t2,5,endw		#while (i<5)
	
	li $v0, 4
	la $a0,str1
	syscall			#print_string
	
	li $v0,5
	syscall			# $v0 = input()
	move $t1,$v0			#copiar para o $t1 o valor do $v0
	
if:
	ble $t1,$0,else		#if (value > 0) {
	add $t0,$t0,$t1		#soma+= value
	j endif			# }

else:	
	li $v0, 4
	la $a0,str2
	syscall			#print_string(str2)
		
		
endif:
	addi $t2,$t2,1		#i++
	j while			#}
endw:
	
	li $v0, 4
	la $a0,str3
	syscall

	li $v0, 1
	move $a0, $t0
	syscall
	
	jr $ra