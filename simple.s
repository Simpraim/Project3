.data
.comm	x,4,4

.text
	.align 4
.globl  add
add:
add_bb2:
	movl	%EDI, %EAX
	movl	%ESI, %EDI
add_bb3:
	addl	%EDI, %EAX
add_bb1:
	ret
