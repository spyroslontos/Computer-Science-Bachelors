import datetime

def Easter(y):

	a = y % 19
	b = y // 100
	c = y % 100
	d = b // 4
	e = b % 4
	g = (8 * b + 13) // 25
	h = (19 * a + b - d - g + 15) % 30
	j = c // 4
	k = c % 4
	m = (a + 11 * h) // 319
	r = (2 * e + 2 * j - k - h + m + 32) % 7
	n = (h - m + r + 90) // 25
	p = (h - m + r + n + 19) % 32

	return datetime.date(year=y,month=n,day=p)

year = int(input("Enter year to find easter: "))
format = input("Enter format n/v/b: ")
format_print = []

if format == "n":
	format_print.append((Easter(year).strftime('%d/%m/%Y')))
elif format == "v":
	if Easter(year).day==1 or Easter(year).day == 11 or Easter(year).day == 21 or Easter(year).day == 31:
		format_print.append(Easter(year).strftime('%dst of %B %Y'))
	elif Easter(year).day == 2 or Easter(year).day == 12 or Easter(year).day == 22:
		format_print.append(Easter(year).strftime('%dnd of %B %Y'))
	elif Easter(year).day == 3 or Easter(year).day == 13 or Easter(year).day == 23:
		format_print.append(Easter(year).strftime('%drd of %B %Y'))
	else:
		format_print.append(Easter(year).strftime('%dth of %B %Y'))
elif format == "b":
	format_print.append(Easter(year).strftime('%d/%m/%Y'))
	if Easter(year).day==1 or Easter(year).day == 11 or Easter(year).day == 21 or Easter(year).day == 31:
		format_print.append(Easter(year).strftime('%dst of %B %Y'))
	elif Easter(year).day == 2 or Easter(year).day == 12 or Easter(year).day == 22:
		format_print.append(Easter(year).strftime('%dnd of %B %Y'))
	elif Easter(year).day == 3 or Easter(year).day == 13 or Easter(year).day == 23:
		format_print.append(Easter(year).strftime('%drd of %B %Y'))
	else:
		format_print.append(Easter(year).strftime('%dth of %B %Y'))

for i in format_print:
	print(i)