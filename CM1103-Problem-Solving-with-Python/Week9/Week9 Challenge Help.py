import random

a = ["a","b","c","d","e"]

print(a)
random.shuffle(a)
print(a)


def proportion(n):
    assignments = 10000  # 10000
    ratio = int(assignments / n)
    assign_a = ["a"]*ratio
    assign_b = ["b"]*ratio
    assign_c = ["c"]*ratio
    assign_d = ["d"]*ratio
    assign_e = ["e"]*ratio

    merged_list = assign_a + assign_b + assign_c + assign_d + assign_e

    print(merged_list)
    random.shuffle(merged_list)
    print(merged_list)

    handed_a = []
    for i in range(0,ratio):
        handed_a.append(merged_list[i])

    print(handed_a.count("a")/ratio)


# i in range(1,11):
#    print(proportion(i))


#----------------------------------------#

list = ["1","1","2","2"]

for i in list:
    temp=list.count("1")

print(temp)
