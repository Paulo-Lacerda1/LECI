	.equ 	READ_CORE_TIMER, 11
	.equ 	RESET_CORE_TIMER, 12

	.data
	.text
	.globl main

######## MAPA DE REGISTOS ########
# $t0 	counter

main: 				# int main(void){
	li 	$t0, 0 		# 	int counter = 0;
while1: 			# 	while(1){
	li 	$v0, 3
	li 	$a0, '\r'
	syscall 		# 		putChar('\r');
	li 	$v0, 6
	move 	$a0, $t0
	li 	$a1, 0x0004000A 
	syscall 		#		printInt(counter, 10 | 4 << 16)
	li 	$v0, 12
	syscall 		# 		resetCoreTimer();
while2:				#		while(readCoreTimer() < 20000);
	li 	$v0, 11
	syscall
	bge 	$v0, 1, endWhile2
	j 	while2
endWhile2:
	addiu 	$t0, $t0, 1 	#		counter++;
	j 	while1
endwhile1:			# 	}
	jr 	$ra 		# }
	