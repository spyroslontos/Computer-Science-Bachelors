import random

guess = False
number = random.randrange(1,11)

while guess == False:

	numguess = input("Your guess? ")
	
	if number > int(numguess):
		print("It is higher.")

	if number < int(numguess):
		print("It is smaller.")

	if int(numguess) == number:
		guess = True
		print("Correct!")