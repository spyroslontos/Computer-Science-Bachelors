.586
.model flat, stdcall
option casemap :none
.stack 4096
ExitProcess proto,dwExitCode:dword

.data	

.code
main proc
		MOV CX,0
AGAIN:	ADD CX,AX
		DEC BX
		JZ DONE
		JMP AGAIN
DONE:
		MOV AX,0
		MOV BX,0

finish:
	invoke ExitProcess,0
main endp

end