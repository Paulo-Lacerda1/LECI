# int main(void){

# 	configD11();

# 	outD11(1);
# 	delay(500);	0,5s

# 	outD11(0);
# 	delay(600);	
	
# 	outD11(1);
# 	delay(200);

# 	outD11(0);
# 	delay(150);

# 	outD11(1);
# 	delay(100);

# 	outD11(0);
# 	delay(600);

#}

############

	.data
	.text
	.globl main


main:
	jal configD11

	li 	$a0, 1
	jal 	outD11
	li 	$a0, 500
	jal 	delay

	li 	$a0, 0
	jal 	outD11
	li 	$a0, 600
	jal 	delay

	li 	$a0, 1
	jal 	outD11
	li 	$a0, 200
	jal 	delay

	li 	$a0, 0
	jal 	outD11
	li 	$a0, 150
	jal 	delay

	li 	$a0, 1
	jal 	outD11
	li 	$a0, 100
	jal 	delay

	li 	$a0, 0
	jal 	outD11
	li 	$a0, 600
	jal 	delay

	li 	$a0, 1
	jal 	outD11

	jr 	$ra

delay: 					# void delay(unsigned int ms){
	li 	$v0, 12
	syscall 			# 	resetCoreTimer();
delayWhile:				#	while(readCoreTimer() < K * ms)
	li 	$v0, 11 		#
	syscall 			# 		readCoreTimer();
	mulou 	$t3, $a0, 20000 	# 		K * ms
	bge 	$v0, $t3, delayEndWhile #		if(readCoreTimer() < K * ms) break;
	j 	delayWhile		# 	}
delayEndWhile:
	jr 	$ra 			# }




configD11: 				# void configD11
	lui 	$t0, 0xBF88
	lw 	$t1, 0x6080($t0)
	andi 	$t1, $t1, 0xBFFF
	sw 	$t1, 0x6080($t0)
	jr 	$ra 


outD11:
	lui 	$t0, 0xBF88
	lw 	$t1, 0x60A0($t0)
	andi 	$t1, $t1, 0xBFFF
	sll 	$a0, $a0, 14
	or 	$t1, $t1, $a0
	sw 	$t1, 0x60A0($t0)
	jr 	$ra 
