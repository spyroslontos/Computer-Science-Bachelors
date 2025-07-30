class MyName:

    def printMe(self):
        print("{:s} {:s}".format(self.firstname, self.surname))

    def __repr__(self):
        return ("{:s} {:s} {:s}".format(self.firstname, self.midname, self.surname))


me = MyName()
me.firstname="Stuart"
me.surname="Allen"
me.midname="LuL"
print(me)
