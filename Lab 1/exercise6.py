sent = input("Enter a sentense: ")
integers = 0
characters = 0

for ch in sent:
	if ch.isdigit() == True:
		integers = integers+1

	if ch.isalpha() == True:
		characters=characters+1

print('Number of integers in sentence',integers)
print('Number of characters in sentence',characters)