#!/usr/bin/python
import cgi, cgitb
import datetime
cgitb.enable()
form = cgi.FieldStorage()


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

    return datetime.date(day=p, month=n, year=y)


year = int(form.getvalue("theYear"))
format = form.getvalue("howformat")
format_print = []




print('Content-Type: text/html; charset=utf-8')
print('')
print('<!DOCTYPE html>')
print('<html>')
print('<link rel="stylesheet" type="text/css" href="../easter_css.css">')
print('<head> <title> Python script for Easter date </title> </ head>')
print('<body>')
print('<h3>The Easter date of year %s is at:</h3><br/>' % year)
print('<p>')
for date in format_print:
    print(date)
    print('<br/>')
print('</p>')
print('</body>')
print('</html>')
