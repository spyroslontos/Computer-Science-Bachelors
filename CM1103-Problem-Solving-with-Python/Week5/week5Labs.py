#Exercise 2
"""
hours = 3
minutes = 45
seconds = 2

secs = (hours*60*60) + (minutes*6) + seconds

print (secs)
"""

#Exercise 6
"""
def hhmmss(totalSeconds):

	seconds = totalSeconds % 60

	totalMinutes = totalSeconds // 60

	minutes = totalMinutes % 60

	hours = totalMinutes // 60

	print (hours, minutes, seconds)


s = int(input("Enter the number seconds: "))

hhmmss(s)
"""

#Exercise 7

def totalseconds(time):

	"""
	>>> totalseconds("01:06:43")
	Total number of seconds is: 4003
	"""

	hours_secs = int(time[0:2]) * 60 * 60
	minutes_secs = int(time[3:5]) * 60
	seconds = int(time[6:])

	total_sec = hours_secs + minutes_secs + seconds

	print ("Total number of seconds is:",total_sec)

t = str(input("Enter hours:minutes:seconds in format (hh:mm:ss) :"))
totalseconds(t)


