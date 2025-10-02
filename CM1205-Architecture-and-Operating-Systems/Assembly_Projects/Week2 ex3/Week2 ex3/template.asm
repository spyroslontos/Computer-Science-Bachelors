	.586
	.model flat, stdcall
	
	GetStdHandle proto :dword
 	WriteConsoleA proto :dword, :dword, :dword, :dword, :dword
 	ExitProcess proto :dword
 	
 	STD_OUTPUT_HANDLE equ -11
 	
 	.data
 	
 	    sum_string db "Test Console output",0
 	 	outputHandle DWORD ?
 	    buffer db 64 dup(?)
 	    bytes_written dd ?
 	
 	.code
 	main proc
 	    invoke GetStdHandle, STD_OUTPUT_HANDLE
 	    mov outputHandle, eax

 	    invoke WriteConsoleA, outputHandle, addr sum_string, eax, addr bytes_written, 0

		mov eax,0
		mov eax,bytes_written
 	   
 	    invoke ExitProcess, 0
 	main endp
 	
 	end

; ----------------------------------------------------------- ;

.586
	.model flat, stdcall
	
	GetStdHandle proto :dword
	ReadConsoleA  proto :dword, :dword, :dword, :dword, :dword
 	ExitProcess proto :dword
 	
 	STD_INPUT_HANDLE equ -10
 	
 	.data
		bufSize = 80
 	 	inputHandle DWORD ?
 	    buffer db bufSize dup(?)
 	    bytes_read  DWORD  ?
 	
 	.code
 	main proc
 	    invoke GetStdHandle, STD_INPUT_HANDLE
 	    mov inputHandle, eax
 		invoke ReadConsoleA, inputHandle, addr buffer, bufSize, addr bytes_read,0
 	    invoke ExitProcess, 0
 	main endp
 	
 	end