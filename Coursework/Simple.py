def celsiusTOfarenh(temp):
    return (9*temp)/5+32

def farenhTOcelsius(temp):
    return ((temp -32)*5/9)


temp = int(input("Enter temperature: "))
print("Temperature entered: ",temp)

conv = input("Enter converter (C/F): ")
print("Converter chosen: ", conv)

if conv == "C":
    print(farenhTOcelsius(temp)," Celsius")
elif conv == "F":
    print(celsiusTOfarenh(temp)," Fahrenheit")

