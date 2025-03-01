	.data 
	.text
	.globl main

main:
    li $v0, 5           # syscall for reading an integer
    syscall              # execute the syscall
    move $t0, $v0       # move input value into $t0 for further operations
    
    sra $t1, $t0, 1     # $t1 = $t0 >> 1 (arithmetic right shift by 1)
    or $t2, $t0, $t1    # $t2 = $t0 | $t1 (bitwise OR)

    li $v0, 1           # syscall code for print integer
    move $a0, $t2       # move result into $a0 to print
    syscall              # execute the syscall to print the result

    li $v0, 10          # syscall for exit
    syscall              # execute the syscall to exit
