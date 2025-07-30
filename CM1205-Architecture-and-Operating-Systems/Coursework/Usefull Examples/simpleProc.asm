;An example of a basic procedure to sum 3 values.
.586
.MODEL FLAT,stdcall
.STACK 4096
extrn ExitProcess@4: proc

.DATA
       
.CODE
main proc

	mov	eax,3
	mov	ebx,6
	mov	ecx,9
	call	sumOf3
	
finish:
	push 0
	call ExitProcess@4


 


sumOf3 	PROC
		add	eax,ebx
		add	eax,ecx
		ret
	sumOf3 	ENDP

main	endp
end