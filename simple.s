.data
.comm	x,4,4

.text
	.align 4
.globl  a
a:
a_bb2:
	movl	%EDI, %ECX
	movl	%ESI, %EDI
a_bb3:
a_bb4:
	movl	$1, %EAX
	cmpl	%EAX, %EDX
	jge	a_bb6
a_bb5:
	movl	$1, %EAX
	jmp	a_bb1
a_bb8:
	movl	$10, %EAX
	cmpl	%EAX, %EDX
	jge	a_bb10
a_bb9:
	jmp	a_bb8
a_bb10:
	movl	%ECX, %EAX
	addl	%EDI, %EAX
a_bb1:
	ret
a_bb6:
	jmp	a_bb8
.globl  b
b:
b_bb2:
	movl	%EDI, %EAX
	movl	%ESI, %EDI
b_bb3:
	addl	%EDI, %EAX
b_bb1:
	ret
.globl  main
main:
main_bb2:
main_bb3:
	movl	$1, %EAX
main_bb1:
	ret
