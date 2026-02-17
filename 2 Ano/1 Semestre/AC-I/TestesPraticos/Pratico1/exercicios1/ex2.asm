# Mapa de registos 
# $t0 -> p1 = &a
# $t1 -> p2 = &C
# $t2 -> n_even
# $t3 -> n_odd
# $t4 -> i

    .data
    .eqv N,5

a:  .space 140
c:  .space 140

    .text
    .globl main

main:
    	la   $t0,a          # $t0 = &a
    	li   $t4,0          # i = 0

for1:
    	bge  $t4,N,endfor1  # while(i < N)
    	li   $v0,5
    	syscall
    	sw   $v0,0($t0)     # a[i] = valor lido

    	addiu $t0,$t0,4
    	addi  $t4,$t4,1
   	j for1
endfor1:

    	la   $t0,a          # p1 = &a
    	la   $t1,c          # p2 = &b


    	la   $t5,a          # base de a
    	li   $t6,N
    	sll  $t6,$t6,2      # N * 4
    	add  $t5,$t5,$t6    # t5 = a + N*4


    	li   $t2,0          # n_even = 0
    	li   $t3,0          # n_odd = 0


while:
    	bge  $t0,$t5,endw   # while(p1 < a+N)

    	lw   $t7,0($t0)     # t7 = *p1  (valor atual)


    	rem  $t8,$t7,2       # t8 = t7 % 2
    	beq  $t8,$zero,else  # se resto == 0 → par → vai para else

if:
    	sw   $t7,0($t1)      # *p2 = *p1  (copia para b)
    	addi $t1,$t1,4       # p2++
    	addi $t3,$t3,1       # n_odd++
    	j endif

else:
    	addi $t2,$t2,1       # n_even++

endif:
    	addi $t0,$t0,4       # p1++
    	j while

endw:

    	la 	$t1,c
	li	$t4,0
for_print:
	bge	$t4,$t3,endfor_print
	
	lw	$a0,0($t1)
	li	$v0,1

	syscall
	
	addiu 	$t1,$t1,4
	addi	$t4,$t4,1
	j 	for_print
endfor_print:	
	
	li	$v0,10
	syscall	

