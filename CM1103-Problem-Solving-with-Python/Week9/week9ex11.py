myList = [1,2,4,8,16,32,64,128,256,512,1024]

def myReverse(data):
    for index in range(len(data)-1,-1,-1):
        yield data[index]

for i in myReverse(myList):
    print(i)