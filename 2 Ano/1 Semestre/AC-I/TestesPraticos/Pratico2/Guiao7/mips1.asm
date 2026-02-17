###############################
# FUNÇÕES DO EXERCÍCIO 1
###############################

	.data
sep_str: .asciiz ", "

	.text
	.globl insert
	.globl print_array
	.globl print_int10
	.globl print_string


###################################
# print_int10(int x)
###################################
print_int10:
    li $v0, 1      # syscall print int
    syscall
    jr $ra


###################################
# print_string(char *s)
###################################
print_string:
    li $v0, 4      # syscall print string
    syscall
    jr $ra


###################################
# insert(int *array, int value, int pos, int size)
###################################
insert:
    addi $sp, $sp, -20
    sw   $ra, 16($sp)
    sw   $s0, 12($sp)
    sw   $s1, 8($sp)
    sw   $s2, 4($sp)
    sw   $s3, 0($sp)

    move $s0, $a0        # array
    move $s1, $a1        # value
    move $s2, $a2        # pos
    move $s3, $a3        # size

    bgt  $s2, $s3, insert_error

    addi $t0, $s3, -1     # i = size - 1

insert_shift_loop:
    blt  $t0, $s2, insert_shift_end

    sll  $t1, $t0, 2
    add  $t2, $s0, $t1
    lw   $t3, 0($t2)

    addi $t1, $t1, 4
    add  $t4, $s0, $t1
    sw   $t3, 0($t4)

    addi $t0, $t0, -1
    j insert_shift_loop

insert_shift_end:
    sll $t1, $s2, 2
    add $t2, $s0, $t1
    sw  $s1, 0($t2)

    li $v0, 0
    j insert_exit

insert_error:
    li $v0, 1

insert_exit:
    lw   $ra, 16($sp)
    lw   $s0, 12($sp)
    lw   $s1, 8($sp)
    lw   $s2, 4($sp)
    lw   $s3, 0($sp)
    addi $sp, $sp, 20
    jr   $ra


###################################
# print_array(int *a, int n)
###################################
print_array:
    addi $sp, $sp, -16
    sw   $ra, 12($sp)
    sw   $s0, 8($sp)
    sw   $s1, 4($sp)
    sw   $s2, 0($sp)

    move $s0, $a0   # pointer a
    move $s1, $a1   # n

    sll  $t0, $s1, 2
    add  $s2, $s0, $t0  # p = a + 4*n

print_loop:
    bge  $s0, $s2, print_end

    lw   $a0, 0($s0)
    jal  print_int10

    la   $a0, sep_str
    jal  print_string

    addi $s0, $s0, 4
    j print_loop

print_end:
    lw   $ra, 12($sp)
    lw   $s0, 8($sp)
    lw   $s1, 4($sp)
    lw   $s2, 0($sp)
    addi $sp, $sp, 16
    jr $ra
