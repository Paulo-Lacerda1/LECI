# Mapa de registos:
# str: $a0 -> $s0 (argumento � passado em $a0)
# p1: $s1 (registo callee-saved)
# p2: $s2 (registo callee-saved)
	.data
str:	.asciiz	"ITED - orievA ed edadisrevinU"
	.text
	.globl main
	
main:				#void main(void){
	addi $sp,$sp,-4  	#reservar espaco na pilha
	sw $ra,0($sp)		#primeiro slot para o $ra
	la $a0,str
	jal strrev
	
	move $a0,$v0
	li $v0,4
	syscall			#print...
	
	li $v0,0			#return 0
	
	lw $ra,0($sp)		#retorna o valor de $ra
	addi $sp,$sp,4		#retorna a pilha
	jr $ra
	
	
strrev:	addiu $sp,$sp,-16			#reserva 16 bits da memoria interna 
	
	sw $ra,0($sp)			#reservar o registo $ra na primeira slot da memoria
	sw $s0, 4($sp) 			# guarda valor dos registos:
	sw $s1, 8($sp) 			#$s0, $s1 e $s2
	sw $s2, 12($sp)
	
	move $s0,$a0			#"called saver"
	move $s1,$a0			#p1
	move $s2,$a0			#p2
	
while:	lb $t1,0($s2)			#$t1 = *$s2
	beq $t1,'\0',endw		#while() {
	addiu $s2,$s2,1			#p2++
	j while 				# }
	
endw:	addi $s2,$s2,1			#p2--

while2: bge $s1,$s2,endw2		#while (p1<p2)
	move $a0, $s1 		#
	move $a1, $s2
	jal exchange			#funcao de exchange
	addi $s1,$s1,1			#p1++
	addi $s2,$s2,-1			#p2--
	j while2
	
endw2:
	move $v0,$s0
	lw $ra, 0($sp) 			#rep�e endere�o de retorno
 	lw $s0, 4($sp) 			#rep�e o valor dos registos
	lw $s1, 8($sp)			#$s0, $s1 e $s2
	lw $s2, 12($sp)	
	addiu $sp,$sp,16	         #repor memoria
	jr $ra
	
exchange: 
	lb $t0,0($a0)
	lb $t1,0($a1)  	
	sb $t0,0($a1)
	sb $t1,0($a0)
	jr $ra				#devolve o valor em $v0