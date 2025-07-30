import matplotlib.pyplot as plt

examMarks = [25, 72, 83, 91, 61]
cwkMarks = [56, 90, 45, 62, 60]
fig = plt.figure()
ax = fig.add_subplot(111)

plt.title("Coursework and Exam Marks")
plt.xlabel("Exams")
plt.ylabel("Marks")
plt.ylim(0, 100)
plt.xlim(-0.5, 4.5)

ax.plot(examMarks, "ro" , label="Exam Marks")
ax.plot(cwkMarks, "bx", label="Coursework Marks")
ax.legend(loc="lower right")

plt.show()
