	.data
oldg:	.float -1.0
g:	.float 1.0
s:	.float 0.0
	.text
	.globl main
main:
	li	$t0,0	# k -> $t0
	move	$t1,$a1 # n
	
	mov.s 	$f10,$f12
	
	la	$t4,oldg
	la	$t5,g
	la	$t6,s
	
	l.s	$f2,0($t4)	#oldg
	l.s	$f4,0($t5)	#g
	l.s	$f6,0($t6)	#s
	
for1:
	bge	$t0,$t1,endfor1
			
while:
	sub.s	$f8,$f4,$f2	#g - oldg
	c.le.s	$f8,$f10
	bc1t	endw

	mov.s	$f2,$f4
	
	sll	$t2,$t0,2	# k * 4
	addu	$t2,$t2,$a0	#&a[k]
	l.s	$f0,0($t2)	# $f0 -> a[k]
	
	add.s	$f4,$f4,$f0
	div.s	$f4,$f4,$f10	#g = (g + a[k]) / t;
	
	j	while
endw:	
	
	add.s	$f6,$f6,$f4
	
	s.s	$f4,0($t2)
	
	addi	$t0,$t0,1	#k++
	j	for1
endfor1:

	mtc1	$t1,$f2
	cvt.s.w	$f2,$f2
	
	div.s	$f0,$f6,$f2
	
	jr 	$ra
