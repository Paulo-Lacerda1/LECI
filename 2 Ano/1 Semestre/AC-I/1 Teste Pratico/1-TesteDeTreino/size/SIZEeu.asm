#Mapa de registos 
#$t0 = i
#	
	
	
	.data
        .eqv SIZE,8       
        .eqv print_int,1   
        .eqv print_string,4 
        .eqv print_char,11 
array:   .word 8,4,15,-1987,327,-9,27,16 
str1: .asciiz "Result is: "   


	.text
        .globl main

main:     
    li $t0,0          # i = 0
    li $t3,SIZE       # Carrega o valor de SIZE (8) em $t3
    srl $t3,$t3,1     # Divide SIZE por 2 (movendo os bits à direita)
    sll $t3,$t3,2     # Multiplica SIZE/2 por 4 (pois cada palavra no array tem 4 bytes)
    la $t2,array      #  $t2=&array
    
do1:     
    sll $t4,$t0,2     # Calcula o deslocamento de 4 bytes para acessar val[i] (i*4)
    addu $t4,$t4,$t2  # Obtém o endereço de val[i] (endereço base + deslocamento)
    addu $t5,$t4,$t3  # Calcula o endereço de val[i+SIZE/2] (endereço de val[i] + SIZE/2*4)
    lw $t1,0($t4)     # Carrega o valor de val[i] em t1
    lw $t6,0($t5)     # Carrega o valor de val[i+SIZE/2] em t6
    sw $t6,0($t4)     # Armazena o valor de val[i+SIZE/2] em val[i]
    sw $t1,0($t5)     # Armazena o valor de val[i] em val[i+SIZE/2]
    addi $t0,$t0,1    # i++

while1:    
    bge $t0,4,endw1  # Se i >= SIZE/2 (4), sai do loop
    j do1             # Caso contrário, repete o loop do1
    
endw1:     
    li $v0,print_string 
    la $a0,str1        
    syscall             
    li $t0,0           

do2:     
    sll $t4,$t0,2      # Calcula o deslocamento de 4 bytes para acessar val[i]
    addu $t4,$t4,$t2   # Obtém o endereço de val[i] (endereço base + deslocamento)
    lw $t1,0($t4)      # Carrega o valor de val[i] em t1
    li $v0,print_int   
    move $a0,$t1       # Move o valor de val[i] para o a0
    syscall           
    li $v0,print_char  
    li $a0,','        
    syscall            
    addi $t0,$t0,1     # i++

while2: 
    bge $t0,SIZE,endw2 
    j do2              
    
endw2:     
    jr $ra 
           
