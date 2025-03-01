#Mapa de resgistos 
# val : $t0
# n : $t1
# min : $t2
# max :$t3

        .data
str1:   .asciiz "Digite ate 20 inteiros (zero para terminar):"
str2:   .asciiz "Maximo/minimo sao:"

        .text
        .globl main

main:
        li $t1,0         # n=0
        li $t2,0x7FFFFFFF # min
        li $t3,0x80000000 # max
        

        li $v0,4
        la $a0,str1           # print_str()
        syscall

do:
        li $v0,5              # read_int() val = $t0
        syscall
        move $t0,$v0

if:
        beq $t0,0,endif       # if (val != 0)

if2:
        ble $t0,$t3,endif2    # if (val > max)
        move $t3,$t0          # max = val;
endif2:

if3:
        bge $t0,$t2,endif3    # if (val < min)
        move $t2,$t0          # min = val;
endif3:

endif:
        addi $t1,$t1,1        # n++

        bge $t1,20,do         # while (n < 20)
        bne $t0,0,do          # && (val != 0)

        li $v0,4
        la $a0,str2           # print_string("Maximo/minimo sao: ")
        syscall

        li $v0,1
        move $a0,$t3          # print_int10(max)
        syscall

        li $v0,11
        li $a0,':'
        syscall               # print_char(":")

        li $v0,1
        move $a0,$t2          # print_int10(min)
        syscall

        jr $ra
