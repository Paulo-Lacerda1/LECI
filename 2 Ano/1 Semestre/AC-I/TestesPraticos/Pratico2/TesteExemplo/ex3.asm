#+----------------------+-------+------+--------+
#| Campo                | Align | Dim  | Offset |
#+----------------------+-------+------+--------+
#| int acc              |  4    |  4   |  0     |
#| unsigned char nm     |  1    |  1   |  4     |
#| double grade         |  8    |  8   |  8     |
#| char quest[14]       |  1    |  14  |  16    |
#| int cq               |  4    |  4   |  32    |
#|			|  8	|36->40| 	|
#+----------------------+-------+------+--------+


	.data
sum:	.double	0.0
t_kvd:	.space	40
	.text
	.globl main:
	
main:	
	li	$t0,0		#i = 0;
	move 	$t1,$a0		#$t1 -> nv
	la	$t2,t_kvd
for:
	bge	$t0,$t1,endfor
	li	$t3,0		#j = 0;
	
do:
	addiu	$t4,$t2,16	
	addu	$t4,$t4,$t3	# $t4 = &pt->quest[j]
	
	lb	$t5,0($t4)	# $t5 = pt->quest[j]
	
	mtc1	$t5,$f4
	cvt.d.w	$f6,$f4		# $f6 = (double) pt->quest[j]
	
	la	$t9,sum
	l.d	$f8,0($t9)	# $f8 = sum
	add.d	$f8,$f8,$f6	
	s.d	$f8,0($t9)	##sum += (double) pt->quest[j];
	
	addi	$t3,$t3,1	#j++
	
	addiu	$t6,$t2,4	# &pt->nm
	
	lbu	$t7,0($t6)	# $f7 = pt->nm
	blt	$t3,$t7,do	# } while (j < pt->nm);
end_do:	

	l.d	$f4,8($t2)	#    # carregar pt->grade
	div.d	$f6,$f8,$f4
	
	cvt.w.d	$f10,$f6
	
	mfc1	$t9,$f10
	
	sw	$t9,0($t2)
	
	
	addi	$t0,$t0,1	#i++
	addi	$t2,$t2,40	#p++
	j 	for
endfor:
	
	lw	$t1,32($t2)	# $t1 = pt->cq
	l.d	$f4,8($t2)
	mtc1	$t1,$f2
	cvt.d.w	$f2,$f2
	
	mul.d	$f0,$f4,$f2
	
	jr	$ra
	
	
	