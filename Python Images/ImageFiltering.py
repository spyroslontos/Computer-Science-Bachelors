import matplotlib.pyplot as plt
import matplotlib.image as image

im = image.imread("parrots.png")
plt.imshow(im)
plt.axis("off")a
plt.show()

"""
blue = im.copy()
blue[:, :, (0, 1)] = 0
plt.imshow(blue)
"""