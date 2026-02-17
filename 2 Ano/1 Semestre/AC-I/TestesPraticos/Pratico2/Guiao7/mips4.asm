	.text
	.globl main

main:
    jal print_array
    li $v0, 10
    syscall
