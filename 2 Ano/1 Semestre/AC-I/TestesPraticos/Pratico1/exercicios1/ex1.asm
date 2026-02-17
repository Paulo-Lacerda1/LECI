# Mapa de registos
# $t0 -> &val              (endereço base do array)
# $t1 -> i
# $t2 -> SIZE / 2
# $t3 -> v (temporário)
# $t4 -> endereço de val[i]
# $t5 -> endereço de val[i + SIZE/2]
# $t6 -> temporário para cálculos

	.data
	.eqv	SIZE,8
val:	.word	8, 4, 15, -1987, 327, -9, 27, 16
str1:	.asciiz "Result is: "
comma:	.asciiz ", "
	.text
	.globl main
	
main:
	li	$t1,0			#i = 0
	li   	$t3, SIZE
	li  	$t4, 2
	div  	$t2, $t3, $t4      	# $t2 = SIZE / 2
	la	$t0,val
while:
    bge   $t1, $t2, endw   # while (i < SIZE/2)

    ###### calcular endereço de val[i] ######
    sll   $t6, $t1, 2      # i * 4
    add   $t4, $t0, $t6    # $t4 = &val[i]

    ###### calcular endereço de val[i + SIZE/2] ######
    add   $t6, $t1, $t2    # i + SIZE/2
    sll   $t6, $t6, 2
    add   $t5, $t0, $t6    # $t5 = &val[i + SIZE/2]

    ###### trocar valores ######
    lw    $t3, 0($t4)      # v = val[i]
    lw    $t6, 0($t5)      # t6 = val[i + SIZE/2]
    sw    $t6, 0($t4)      # val[i] = val[i + SIZE/2]
    sw    $t3, 0($t5)      # val[i + SIZE/2] = v

    ###### i++ ######
    addi  $t1, $t1, 1
    j     while

endw:

	#    printf("Result is: ");...
	
