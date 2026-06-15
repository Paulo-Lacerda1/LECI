	.data
	.text
	.globl main
main: 					# void delay(unsigned int ms){
	li 	$v0, 12
	syscall 			# 	resetCoreTimer();
while:					#	while(readCoreTimer() < K * ms)
	li 	$v0, 11 
	syscall 			# 		readCoreTimer();
	li 	$t1, 20000
	mul 	$t3, $t1, $a0 		# 		K * ms
	bge 	$v0, $t3, endWhile 	#		if(readCoreTimer() < K * ms) break;
	j 	while
endWhile:
	jr 	$ra 		# }