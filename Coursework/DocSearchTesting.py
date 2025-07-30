# input_file = open("set1//docs.txt", 'r')

# lines = len(input_file.readlines())

# with open("set2//docs.txt") as docs:
#     for i,line in enumerate(docs):
#         print("Line",i,":",line)

with open("set1//queries.txt") as queries:
    for query in queries:
        word_found = []
        relevant = []
        relevant_clear = []
        print("Query: ", query.strip())
        with open("set1//docs.txt") as docs:
            for i, line in enumerate(docs, start=1):
                for word in query.split():
                    if word in line:
                        word_found.append(i)
            for element in word_found:
                if word_found.count(element) == len(query.split()):
                    relevant.append(element)

            [relevant_clear.append(x) for x in relevant if x not in relevant_clear]

            print("Relevant documents:", str(relevant_clear)[1:-1].replace(", ", " "))
