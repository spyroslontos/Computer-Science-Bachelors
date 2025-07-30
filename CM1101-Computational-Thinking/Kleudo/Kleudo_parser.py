import string

# List of "unimportant" words (feel free to add more)
skip_words = ['a', 'about', 'all', 'an', 'another', 'any', 'around', 'at',
              'bad', 'beautiful', 'been', 'better', 'big', 'can', 'every', 'for',
              'from', 'good', 'have', 'her', 'here', 'hers', 'his', 'how',
              'i', 'if', 'in', 'into', 'is', 'it', 'its', 'large', 'later',
              'like', 'little', 'main', 'me', 'mine', 'more', 'my', 'now',
              'of', 'off', 'oh', 'on', 'please', 'small', 'some', 'soon',
              'that', 'the', 'then', 'this', 'those', 'through', 'till', 'to',
              'towards', 'until', 'us', 'want', 'we', 'what', 'when', 'why',
              'wish', 'with', 'would']


def filter_words(words, skip_words):
   
    for i in words: #checks every word in the sentence
        if i in skip_words: 
            words.remove(i) #removes the word if it appears in the skip_words list
            filter_words(words, skip_words)
    return words
    
def remove_punct(text):
  
    no_punct = "" #initializes no_punct as a string
    for char in text: #checks every letter in the sentence
        if not (char in string.punctuation): #if it is not a punctuation mark
            no_punct = no_punct + char #completes the sentence without puctuation

    return no_punct


def normalise_input(user_input):
  
    # Remove punctuation and convert to lower case
    no_punct = remove_punct(user_input).lower() 
    return (filter_words(no_punct.split(),skip_words)) #passes through they filter_words variable
