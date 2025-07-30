# These are the values that you must change.
m = 83
a = 84
c = 53

#m = 87
#a = 38
#c = 60 #This doesn't change anything

#You do not need to change below here
x = 57
def random():
	global x
	x = (a * x + c) % m
	return x

def RandExperiment(n):
	randomNumbers = []
	for i in range(n):
		randomNumbers.append(random())

	return randomNumbers

#print(RandExperiment(10))
#print(RandExperiment(50))
print(RandExperiment(100))
#print(RandExperiment(200))

#import MyRandom
#for i in range(5):
	#print(MyRandom.random())
seen = []

for i,element in enumerate(RandExperiment(100)):
	if element not in seen:
		seen.append(element)
	else:
		print("The Period is:", i)
		break

