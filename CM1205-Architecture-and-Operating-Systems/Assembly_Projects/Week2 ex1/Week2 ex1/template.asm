.586
.model flat, stdcall
option casemap :none
.stack 4096
ExitProcess proto,dwExitCode:dword

.data	

.code
main proc
	MOV CX,AX
	SUB AX,BX
	JZ  ITSAMATCH
	MOV DX,0
	JMP RESETAX
  ITSAMATCH:
	MOV DX,1
  RESETAX:
	MOV AX,CX

finish:
	invoke ExitProcess,0
main endp

end