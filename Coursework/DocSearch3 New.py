import math
import numpy as np

def calc_angle(x, y):
    norm_x = np.linalg.norm(x)
    norm_y = np.linalg.norm(y)
    cos_theta = np.dot(x, y) / (norm_x * norm_y)
    theta = math.degrees(math.acos(cos_theta))
    return theta


def relevantDocuments():
    file_dict = {}
    with open("set3//docs.txt") as docs:
        for i,line in enumerate (docs,start=1):
            for word in line.split():
                if word not in (file_dict):
                    file_dict[word] = [i]
                else:
                    file_dict[word].append(i)

    print("Words in dictionary: ",len(file_dict))

    with open("set3//queries.txt") as queries:
        for query in queries:
            relevant = []
            relevant_clear = []
            print("Query: ", query.strip())
            with open("set3//docs.txt") as docs:
                for i, line in enumerate(docs, start=1):
                    if set(query.strip().split()).issubset(line.split()):
                        relevant.append(i)
                print("Relevant documents:", str(relevant)[1:-1].replace(", ", " "))
            arrayGenerator(query.strip(),relevant)


def arrayGenerator(query,relevant):

    document_vector = []
    query_vector = []
    angles_dict = {}
    sorted_angles = []

    with open("set3//docs.txt") as docs:
        for i,line in enumerate(docs,start=1):
            if i in relevant:
                document_template = []
                document_array = {}
                for word in line.split():
                    if word not in (document_array):
                        document_array[word] = 1
                        document_template.append(word)
                    else:
                        document_array[word] += 1
                # print(document_template)
                # print(document_array)

                for word in document_template:
                    document_vector.append(document_array.get(word))
                # print(document_vector)

                for word in document_template:
                    if word in query.split():
                        query_vector.append(1) #document_array.get(word)
                    else:
                        query_vector.append(0)
                # print(query_vector)

                docArray = np.array(document_vector)
                queryArray = np.array(query_vector)

                angles_dict[i] = round(calc_angle(docArray,queryArray),5)

                # print(i, round(calc_angle(docArray,queryArray),5))

                document_template.clear()
                document_array.clear()
                document_vector.clear()
                query_vector.clear()

        sorted_angles = sorted(angles_dict.items(), key=lambda x: x[1])
        for i in sorted_angles:
            print(i[0], i[1])

relevantDocuments()
