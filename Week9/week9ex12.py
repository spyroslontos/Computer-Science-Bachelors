def myNth(data,n):
    for index in range(0,len(data),n):
        yield data[index]


myList = [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
for i in myNth(myList,2):
    print(i)
