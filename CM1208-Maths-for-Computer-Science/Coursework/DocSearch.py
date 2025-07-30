#C1722325
import math
import numpy as np

#Function to calculate the angle between 2 vector arrays
def calc_angle(x, y):
    norm_x = np.linalg.norm(x)
    norm_y = np.linalg.norm(y)
    cos_theta = np.dot(x, y) / (norm_x * norm_y)
    theta = math.degrees(math.acos(cos_theta))
    return theta


def relevantDocuments(documentFile,queryFile):

    file_dict = {}

    #Stores all the unique words found in the document inside a dictionary
    with open(documentFile) as docs:
        for line in (docs):
            for word in line.split():
                if word not in (file_dict):
                    file_dict[word] = ["found"]

    print("Words in dictionary: ",len(file_dict))

    #Finds and stores each relevant document for each query
    with open(queryFile) as queries:
        for query in queries:
            relevant = []
            print("Query: ", query.strip())
            with open(documentFile) as docs:
                for i, line in enumerate(docs, start=1):
                    # Finds the subsets of words in each line and
                    # stores them in a list
                    if set(query.split()).issubset(line.split()):
                        relevant.append(i)
                # Shows every unique element in the list without the square brackets and comma
                print("Relevant documents:", str(relevant)[1:-1].replace(",", ""))
            #Jumps to another function using 3 important arguments
            arrayGenerator(query.strip(),relevant,documentFile,queryFile)


def arrayGenerator(query,relevant,documentFile,queryFile):

    document_vector = []
    query_vector = []
    angles_dictionary = {}

    #For each relevant document stored. A vector is created using words found
    #A second vector is created using the same words but the only values stored
    #are the presence of the queries
    with open(documentFile) as docs:
        for i,line in enumerate(docs,start=1):
            # Checks if the document ID is found in the relevant list
            # for the query used
            if i in relevant:

                document_template = []
                document_dictionary = {}

                # Stores each word found in the document in a dictionary
                for word in line.split():
                    if word not in (document_dictionary):
                        document_dictionary[word] = 1
                        #Also a template is created with each unique word
                        document_template.append(word)
                    else:
                        document_dictionary[word] += 1

                # The template is then used to create a document vector
                for word in document_template:
                    document_vector.append(document_dictionary.get(word))

                # And the same template is used to create the query vector
                for word in document_template:
                    if word in query.split():
                        query_vector.append(1)
                    else:
                        query_vector.append(0)

                #Creates arrays using the vectors
                docArray = np.array(document_vector)
                queryArray = np.array(query_vector)

                # The 2 vectors are used to create an angle between them to show
                # their relation. They are all stored in a dictionary using their
                # document ID and angle
                angles_dictionary[i] = round(calc_angle(docArray,queryArray),5)

                #This clears up the lists and dictionaries previously
                document_template.clear()
                document_dictionary.clear()
                document_vector.clear()
                query_vector.clear()

        # Then they are sorted and printed in ascending order
        # From most similar to least similar
        sorted_angles = sorted(angles_dictionary.items(), key=lambda value: value[1])
        for i in sorted_angles:
            print(i[0], i[1])

#Change the file name to Test other documents
relevantDocuments("set2//docs.txt","set2//queries.txt")

