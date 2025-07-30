print ("Enter numbers to find their average. Just press enter to exit")

num1=0
inc=0
sums=0

while True:
	num1 = input("Enter Number: ")

	if num1 == "":
		break

	inc = inc + 1
	sums += int(num1)

avg = sums / inc
print (avg)