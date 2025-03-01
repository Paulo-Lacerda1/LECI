#Mapa de registos
# $t0 = res
# $t1 = val	

	.data
res:	.float 0.0
	.text
	.globl main

main:
	
while:	
	li $v0, 5
	syscall			#read_int
	move $t1,$v0	
	
	mtc1 $t1,$f0		# Move o inteiro para o registo $f0 (FPU)
	cvt.s.w $f0,$f0		# Converte inteiro (word) para float
	
	l.s $t1,2.59375		#Carregar o $t1 com o valor do float
	mul.s $f2,$f0,$f4 	#calcular o "res"
	
	li $v0,3 
	move $f12, $f2
	syscall			#print_float(res)
	
	l.s $f6, 0.0              # Carrega 0.0 no registo $f6
    	c.eq.s $f6,$f2
    	c.le.s $f6,$f2
    	

	j while
endw: