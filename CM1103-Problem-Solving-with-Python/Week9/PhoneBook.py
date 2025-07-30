class PhoneBookEntry:
    pass

class PhoneBook:
    def __init__(self):
        self.data=dict()
	
    def addEntry(self,name,number,email):
        self.data[name] = PhoneBookEntry()

        self.data[name].number = number

        self.data[name].email = email


    def delEntry(self,name):
        del self.data[name]


    def exist(self,name):
        if name in self.data:
            return True
        else:
            return False


    def printBook(self):
        for key in self.data.keys():
            print(key, ":", self.data[key].number, ",", self.data[key].email)


myPhoneBook=PhoneBook()
myPhoneBook.addEntry("Stuart␣Allen","02920222222","S.M.Allen@cs.cf.ac.uk")
myPhoneBook.addEntry("Tom␣Beach","02920111111","T.H.Beach@cs.cf.ac.uk")
myPhoneBook.addEntry("Kostis_Yiannis","62425656343","PAPAS.SAS@cs.cf.ac.uk")
myPhoneBook.exist("Stuart␣Allen")
myPhoneBook.exist("Tom")
myPhoneBook.printBook()