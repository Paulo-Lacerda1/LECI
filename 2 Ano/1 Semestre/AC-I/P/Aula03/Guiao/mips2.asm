# Mapa de registos:
# value: $t0
# bit: $t1
# i: $t2
	.data
str1: .asciiz "Introduza um numero: " 
str2: .asciiz "\nO valor em binário é: "  
	.eqv print_string, 4
	.eqv read_int, 5
	.eqv print_char, 11

	.text
	.globl main
main: 
    la $a0, str1           
    li $v0, print_string    
    syscall                 # print( str1 )

    li $v0, read_int        # 
    syscall                 # ler um inteiro
    move $t0, $v0           # value = read_int

    li $v0, print_string    
    la $a0, str2           
    syscall                 # print( str2 )
 
    li $t2, 0               # i = 0

for: 
    bge $t2, 32, endfor     # while(i < 32)
    li $t3, 0x80000000      # $t3 = 0x80000000
    and $t1, $t0, $t3       # bit = value & 0x80000000

if: 
    beq $t1, $0, else    # if (bit != 0)
    li $v0, print_char      
    li $a0, '1'             
    syscall                 # Chama syscall para imprimir '1'
    j endif                 # Salta para o final

else: 
    li $v0, print_char      
    li $a0, '0'             
    syscall                 # Chama syscall para imprimir '0'

endif: 
    sll $t0, $t0, 1         # value = value << 1
    addi $t2, $t2, 1        # i++

    j for                   # Volta para o início do loop

endfor: 
    jr $ra                  
