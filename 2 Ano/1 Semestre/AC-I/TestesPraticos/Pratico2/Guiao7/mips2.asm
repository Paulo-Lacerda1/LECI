#################################
# PROGRAMA PRINCIPAL (EX2)
#################################
	.include "mips1.asm"
	.data
array:  .space 200        # 50 ints (50 * 4 = 200 bytes)

str:   .asciiz "Size of array : "
str1:  .asciiz "] = "
str2:  .asciiz "array["
str3:  .asciiz "Enter the value to be inserted: "
str4:  .asciiz "Enter the position: "
str5:  .asciiz "\nOriginal array: "
str6:  .asciiz "\nModified array: "

	.text
	.globl main

main:

    ###########################
    # Ler tamanho do array
    ###########################
    la  $a0, str
    li  $v0, 4
    syscall

    li  $v0, 5
    syscall
    move $t0, $v0        # array_size

    li  $t1, 0           # i = 0

for:
    bge  $t1, $t0, endfor

    # print "array["
    la  $a0, str2
    li  $v0, 4
    syscall

    move $a0, $t1
    li  $v0, 1
    syscall

    la  $a0, str1
    li  $v0, 4
    syscall

    # read_int()
    li  $v0, 5
    syscall

    # array[i] = read_int();
    sll $t2, $t1, 2
    la  $t3, array
    add $t3, $t3, $t2
    sw  $v0, 0($t3)

    addi $t1, $t1, 1
    j for

endfor:

    ############################
    # Ler insert_value
    ############################
    la  $a0, str3
    li  $v0, 4
    syscall

    li  $v0, 5
    syscall
    move $s0, $v0   # insert_value

    ############################
    # Ler insert_pos
    ############################
    la  $a0, str4
    li  $v0, 4
    syscall

    li  $v0, 5
    syscall
    move $s1, $v0   # insert_pos

    ############################
    # print original array
    ############################
    la  $a0, str5
    li  $v0, 4
    syscall

    la  $a0, array
    move $a1, $t0
    jal print_array

    ############################
    # insert(array, value, pos, size)
    ############################
    la   $a0, array
    move $a1, $s0
    move $a2, $s1
    move $a3, $t0
    jal  insert

    ############################
    # print modified array
    ############################
    la  $a0, str6
    li  $v0, 4
    syscall

    la  $a0, array
    addi $a1, $t0, 1
    jal print_array

    ############################
    # terminar programa
    ############################
    li $v0, 10
    syscall
