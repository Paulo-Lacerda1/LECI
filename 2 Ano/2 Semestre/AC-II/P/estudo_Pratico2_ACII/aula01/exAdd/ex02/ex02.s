	.equ SIZE, 20
	.data
s0:	.asciiz "Introduza 2 strings: "
s1: 	.asciiz "Resultados:\n"
str1:	.space 	21
str2:	.space 	21
str3: 	.space 	41
	.text
	.globl main 

main: 				# int main(void){
	li 	$v0, 8
	la 	$a0, s0		
	syscall 		# 	printStr("Introduza 2 Strings: ");

	li 	$v0, 9
	la 	$a0,  str1
	li 	$a1, SIZE
	syscall 		# 	readStr( str1, SIZE);

	li 	$v0, 9
	la 	$a0,  str2
	li 	$a1, SIZE
	syscall 		# 	readStr( str1, SIZE);
	
	li 	$v0, 8
	la 	$a0, s1		
	syscall 		# 	printStr("Resultados:\n");


	jr 	$ra 		# }


########## int strlen(char *str) ##########
# $a0 	&(str)
# $t0 	len
# $t1 	*(str)

strlen: 				# int strlen(char *str){
	li	$t0, 0 			# 	int len = 0;
lenFor:					# 	for(*str != '\0'; len++, str++)
	lb 	$t1, 0($a0)		# 		
	beq 	$t1, '\0', lenEndFor	#
	addiu 	$t0, $t0, 1		#		len++;
	addiu 	$a0, $a0, 1 		#		str++;
	j 	lenFor
lenEndFor:
	move 	$v0, $t0		# 	return len;
	jr 	$ra 			# }


########## char *strcpy(char *dst, char *src) ##########
#
#
#

strcpy: 				# char* strcpy(char *dst, char *src){
	move 	$t0, $a0		# 	char *p = dst;
cpyFor:					# 	for( ; (*dst = *src) != '\0'; dst++, src++)
   	lb      $t1, 0($a1)        
   	sb      $t1, 0($a0)        
	beq 	$t1, '\0', cpyEndFor
continue: 
	addiu 	$a0, $a0, 1		# 		dst++;
	addiu 	$a1, $a1, 1		# 		src++;
	j 	cpyFor 			#	}
cpyEndFor:
	move 	$v0, $t0 		#	return p;
	jr 	$ra 			# }


########## int strcat(char *str) ##########
strcat:					# char *strcat(char *dst, char *src){
	addiu 	$sp, $sp, -16		# 	prologo
	sw 	$ra, 0($sp)
	sw 	$s0, 4($sp)
	sw 	$s1, 8($sp)
	sw 	$s2, 12($sp)
	move 	$s0, $a0		# 	dst
	move 	$s1, $a1 		# 	src
	move 	$s2, $s0 		# 	char *p = dst;
catFor:					# 	for( ; *dst != '\0'; dst++){
	lb 	$t0, 0($a0) 		# 		
	beq 	$t0, '\0', catEndFor 	# 		
	addiu 	$s0, $s0, 1 		# 		dst++;
catEndFor: 				# 	}
	move 	$a0, $s0		#
	move 	$a1, $s1 		#
	jal 	strcpy			# 	strcpy( dst, src );

	move 	$v0, $s2		#	return p;
	lw 	$ra, 0($sp)
	lw 	$s0, 4($sp)
	lw 	$s1, 8($sp)
	lw 	$s2, 12($sp)
	addiu 	$sp, $sp, 16		# 	prologo

	jr 	$ra 			# }