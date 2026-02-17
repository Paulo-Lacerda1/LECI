	.data
	.eqv 	SIZE,15
bonk:	.asciiz "Invalid argc"
	.text
	.globl func1

func1:
	addiu	$sp,$sp,-16
	sw 	$ra,12($sp)
	sw 	$s2,8($sp)
	sw	$s1,4($sp)
	sw	$s0,0($sp)
	
	move	$s0,$a0		#$s0 = *f1
	move 	$s1,$a1		#$s1 = k	
	move	$s2,$a2		#$s2 = *av[]
	

if:
	blt	$s1,2,else
	bgt	$s1,SIZE,else
	li	$t0,2		#i =2;
	
do_while:
		
	sll  	$t2, $t0, 2     # t2 = i * 4
	add  	$t3, $s2, $t2   # t3 = &av[i]
	lw	$a0,0($t3)	# a0 = av[i] (char *)
	
	jal	toi
	sll  	$t2, $t0, 2     # t2 = i * 4 (???) é preciso ?
	add	$t3,$s0,$t2
	sw	$v0,0($t3)	# f1[i] = toi(av[i]);
	
	addi 	$t0,$t0,1       #i++;
	blt	$t0,$s1,do_while		
			
	move	$a0,$s0
	move 	$a1,$s1
	jal	avz
	
	move	$a0,$v0
	li	$v0,1
	syscall			#print_int10(res);																
	j	endif
else:
	li	$v0,4
	la	$a0,bonk
	syscall
	
	li	$v0,-1
endif:
	lw 	$ra,12($sp)
	lw	$s2,8($sp)
	lw	$s1,4($sp)
	lw 	$s0,0($sp)
	addiu 	$sp,$sp,16
	jr	$ra
	
