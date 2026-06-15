        .equ    UP, 1
        .equ    DOWN, 0
        .data
        .text
        .globl main

##### MAPA DE REGISTOS #####
# cnt           $s0
#
# state         $t0   
# c             $t1
# 3 << 16       $t2

main: 					
        addiu   $sp, $sp, -8            # prologo
        sw      $ra, 0($sp)
        sw      $s0, 4($sp)

        li      $t0, 0                  #       state = 0;
        li      $s0, 1                  #       cnt = 0;
while:                                  #       do {
        beq     $t1, 'q', endWhile      #
        li      $v0, 3                  #
        li      $a0, '\r'               #
        syscall                         #               putChar('\r');

        li      $v0, 6 			#d
        move    $a0, $s0 		#
        li      $a1, 0x0003000A	 	#
        syscall                         #               printInt(cnt, 10 | 3 << 16);

        li      $v0, 3                  #
        li      $a0, '\t'               #
        syscall                         #               putChar('\t');

	li 	$v0, 6			#
	move 	$a0, $s0		#
	li 	$a1, 0x00080002 	#
	syscall 			# 		printInt(cnt, 2 | 8 << 16);
					
	li 	$a0, 5
	jal 	wait 			# 		wait(5);
	li 	$v0, 1 			#
	syscall 	 		#
	move 	$t1, $v0 		# 		c = inkey();
if1:
	bne 	$t1, '+', if2 		# 		if( c == 'x' )
	li 	$t0, UP  		# 			state = UP;
if2: 	 				# 		
	bne 	$t1, '-', if3 		# 		if ( c == '-' )
	li 	$t0, DOWN 		#			state  = DOWN;
if3: 					#  		if( state == UP )
	bne 	$t0, UP, else3 		#
	addiu 	$s0, $s0, 1 		# 			cnt = (cnt + 1)
	andi 	$s0, $s0, 0xFF 		# 			cnt = (cnt + 1) & 0xFF;
	j 	endif3			#
else3:					# 		else
	addiu 	$s0, $s0, -1
	andi 	$s0, $s0, 0xFF 		# 			cnt = (cnt - 1) & 0xFF; 
endif3:
	j 	while 			#
endWhile:                               #       } while (c != 'q');
      
        lw      $ra, 0($sp)
        lw      $s0, 4($sp)
	addiu   $sp, $sp, 8            	# epilogo

        li      $v0, 0
        jr      $ra                     #       return 0;

############## wait(int ts) ##############

wait:                                   # void waint(int ts)
        li      $t0, 0                  #       int i = 0;
        li      $t1, 515000 
        mul     $t1, $a0, $t1           #       515000 * ts
waitFor:                                #       for(i = 0; i < 515000 * ts) {
        bge     $t0, $t1, waitEndFor    #
        addiu   $t0, $t0, 1             #               i++;
        j       waitFor                 #
waitEndFor:                             #       }
        jr      $ra                     #
	