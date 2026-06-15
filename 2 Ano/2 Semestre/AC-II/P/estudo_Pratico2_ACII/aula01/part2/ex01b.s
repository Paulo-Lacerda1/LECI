	.data
	.text
	.globl main

###### MAPA DE REGISTOS ######
# $t0 	c
# $t1 	cnt

main: 				# int main(void) {
	li 	$t1, 0 		# 	int cnt = 0;
do: 				# 	do {
	beq 	$t0, '\n', endwhile 
	li 	$v0, 1 		# 	
	syscall  		# 		inkey();
	move 	$t0, $v0 	# 		c = inkey();
if: 				# 		if ( c != 0 ){
	beq 	$t0, 0, else 	# 		
	li 	$v0, 3 		#
	move 	$a0, $t0 	# 		
	syscall 		#  			putChar(c);
	j 	endif
else: 				# 		} else {
	li 	$v0, 3 		#
	li 	$a0, '.'	#
	syscall 		# 			putChar('.');
endif: 				# 		}
	addiu 	$t1, $t1, 1 	# 		cnt++;
	j 	do		# 	while(
endwhile:
	li 	$v0, 6
	move 	$a0, $t1
	li 	$a1, 10
	syscall 		# 	printInt(cnt, 10);
	li 	$v0, 0 		#
	jr 	$ra 		#	return 0;