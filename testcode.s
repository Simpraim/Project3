.data
.comm	a,4,4

.text
	.align 4
.globl  addThem
addThem:
addThem_bb2:
	movl	%EDI, %EAX
	movl	%ESI, %EDI
addThem_bb3:
	addl	%EDI, %EAX
addThem_bb1:
	ret
.globl  putDigit
putDigit:
putDigit_bb2:
putDigit_bb3:
	movl	$48, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
putDigit_bb1:
	ret
.globl  printInt
printInt:
printInt_bb2:
	pushq	%R14
	pushq	%R15
	movl	%EDI, %R15D
printInt_bb3:
	movl	$0, %EAX
	movl	%EAX, %R14D
printInt_bb4:
	movl	$10000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb8
printInt_bb5:
	movl	$45, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$1, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb1:
	popq	%R15
	popq	%R14
	ret
printInt_bb16:
	movl	$1, %EAX
	cmpl	%EAX, %R14D
	jne	printInt_bb20
printInt_bb17:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb19:
	jmp	printInt_bb20
printInt_bb24:
	movl	$1, %EAX
	cmpl	%EAX, %R14D
	jne	printInt_bb23
printInt_bb25:
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
printInt_bb27:
	jmp	printInt_bb23
printInt_bb8:
	movl	$1000, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb12
printInt_bb9:
	movl	$1000, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$1000, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%R15D, %EAX
	subl	%EDI, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
printInt_bb12:
	movl	$100, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb16
printInt_bb13:
	movl	$100, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$100, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%R15D, %EAX
	subl	%EDI, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
printInt_bb20:
	movl	$10, %EAX
	cmpl	%EAX, %R15D
	jl	printInt_bb24
printInt_bb21:
	movl	$10, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %EDI
	call	putDigit
	movl	$10, %EDI
	movl	%R14D, %EAX
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%R15D, %EAX
	subl	%EDI, %EAX
	movl	%EAX, %R15D
printInt_bb23:
	movl	%R15D, %EDI
	call	putDigit
	jmp	printInt_bb1
.globl  main
main:
main_bb2:
	pushq	%R13
	pushq	%R14
	pushq	%R15
main_bb3:
	movl	$5, %EAX
	movl	%EAX, %R14D
	movl	%R14D, %R13D
main_bb4:
	movl	$5, %EAX
	cmpl	%EAX, %R13D
	jne	main_bb6
main_bb5:
	movl	$3, %EAX
	movl	%EAX, a(%RIP)
main_bb7:
	movl	$0, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %R14D
main_bb8:
	movl	$8, %EAX
	cmpl	%EAX, %R14D
	jg	main_bb10
main_bb9:
	movl	%R15D, %EAX
	addl	%R14D, %EAX
	movl	%EAX, %R15D
	movl	$1, %EDI
	movl	%R14D, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %R14D
	jmp	main_bb8
main_bb10:
	movl	$3, %EDI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%EDI, %EAX
	movl	%EAX, %ESI
	movl	$4, %EDI
	movl	%ESI, %EAX
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%EDI, %R15D
	movl	a(%RIP), %EDI
	movl	%R13D, %ESI
	call	addThem
	movl	%EAX, %R14D
	movl	$56, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$61, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	a(%RIP), %EAX
	movl	$48, %EDI
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$48, %EDI
	movl	%R13D, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$48, %EDI
	movl	%R14D, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	%R14D, %EAX
	addl	%R15D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %R14D
main_bb11:
	movl	$10, %EAX
	cmpl	%EAX, %R14D
	jge	main_bb13
main_bb12:
	movl	$48, %EAX
	addl	%R14D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$1, %EDI
	movl	%R14D, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %R14D
	jmp	main_bb11
main_bb13:
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$67, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$83, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$3510, %EAX
	movl	%EAX, %EDI
	call	printInt
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %R13D
	movl	$1, %EAX
	movl	%EAX, %R14D
	movl	$1, %EAX
	movl	%EAX, %R15D
	movl	$0, %EAX
	movl	%EAX, %ESI
	movl	$0, %EAX
main_bb14:
	movl	$0, %EAX
	cmpl	%EAX, %R13D
	jne	main_bb16
main_bb18:
	movl	$0, %EAX
	cmpl	%EAX, %R14D
	jne	main_bb22
main_bb19:
	movl	$1, %EAX
	movl	%EAX, %R14D
main_bb30:
	movl	$10, %EAX
	cmpl	%EAX, %R14D
	jne	main_bb32
main_bb31:
	movl	$99, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
	movl	$0, %EAX
	movl	%EAX, %EDI
	call	putDigit
	movl	$108, %EAX
	movl	%EAX, %EDI
	call	putchar
main_bb33:
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
main_bb1:
	popq	%R15
	popq	%R14
	popq	%R13
	ret
main_bb6:
	movl	$4, %EAX
	movl	%EAX, a(%RIP)
	jmp	main_bb7
main_bb28:
	movl	$3, %EAX
	movl	%EAX, %R14D
	jmp	main_bb30
main_bb26:
	movl	$0, %EAX
	cmpl	%EAX, %ESI
	jne	main_bb28
main_bb27:
	movl	$10, %EAX
	movl	%EAX, %R14D
main_bb29:
	jmp	main_bb30
main_bb22:
	movl	$0, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb26
main_bb23:
	movl	$2, %EAX
	movl	%EAX, %R14D
main_bb25:
	jmp	main_bb30
main_bb16:
	movl	$0, %EAX
	movl	%EAX, %R14D
	jmp	main_bb30
main_bb32:
	movl	$98, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$97, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$100, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$61, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	%R14D, %EDI
	call	printInt
	jmp	main_bb33
