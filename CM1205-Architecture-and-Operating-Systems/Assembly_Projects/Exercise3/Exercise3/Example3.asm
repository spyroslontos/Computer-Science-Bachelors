; Calculate the value of X, given that
; X = 3*(10A - (B+2))/(A+B) - (B+2)
;
; (This isn't the only way to do this!)

.586p
.model flat ,stdcall
.data

; Declare varA, varB and varX
	VarA	DB ?
	VarB	DB ?
	VarX	DB ?
; Some useful temporary variables
	Temp1	DB ?
	Temp2	DB ?
.stack
.code
main PROC
	MOV VarA, 4		; Assign initial values to A
	MOV VarB, 5		; and B (A=4, B=5)

; First calculate (A+B) and store it in Temp1
	MOV AL, VarA
	ADD AL, VarB
	MOV Temp1, AL
; Now calculate (B+2)
	MOV AL, VarB
	MOV Temp2, AL
	ADD Temp2, 2	; Temp2 = B+2
; Now calculate 3*(10A - (B+2))
	MOV AL, VarA
	MOV BL, 10		; Multiply A by 10
	MUL BL
	SUB AL, Temp2		; Subtract (B+2)
	MOV BL, 3		; Multiply the lot by 3
	MUL BL
; Can now divide by (A+B) and subtract (B+2)
	MOV BL, Temp1
	MOV AH, 0		; extend the byte in AL into AH
	DIV BL			; before the division
	SUB AL, Temp2		; Subtract (B+2)
; Finally, put the result in VarX
	MOV VarX, AL
	NOP
	NOP
main ENDP
END
