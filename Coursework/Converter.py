temp_entry = ""

def celsiusTOfarenh(temp):
    return (9*temp)/5+32

def farenhTOcelsius(temp):
    return (temp-(32)*5)/9

print("Just enter exit to close")
while temp_entry != "exit":
    temp_entry = input("Enter temperature: ")
    if temp_entry!="exit" and temp_entry.isdigit():
        conv_entry = input("Enter C (Celsius) or F (Fahrenheit) to convert: ")
        if conv_entry == "C" :
            print(farenhTOcelsius(int(temp_entry)))
        elif conv_entry == "F":
            print(celsiusTOfarenh(int(temp_entry)))
        else:
            print("Please enter either C or F")

    elif temp_entry=="exit":
        break
    else:
        print("Please enter a Correct temperature Value")


