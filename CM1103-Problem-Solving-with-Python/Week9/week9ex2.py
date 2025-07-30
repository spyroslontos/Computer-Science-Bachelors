import matplotlib.pyplot as plt

examMarks = [25, 72, 83, 91, 61]
cwkMarks = [56, 90, 45, 62, 60]

plt.plot(cwkMarks, examMarks, "bx")
plt.xlabel("Exam Marks")
plt.ylabel("Coursework Marks")
plt.show()