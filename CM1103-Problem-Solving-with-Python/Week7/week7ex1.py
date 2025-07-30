def counting(i):
    print(i)
    i = i * 2
    if i >= 1025: return
    counting(i)


counting(2)
