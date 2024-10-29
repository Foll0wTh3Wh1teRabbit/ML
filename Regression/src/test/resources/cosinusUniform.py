import matplotlib.pyplot as plt
import numpy as np

def f(x):
    return 1.9272879999466672 + -1.5825517258150934 * x + 0.24327417977662646 * x * x

points = [(4.537424899268576, -0.13276779766980845), (1.1510512640686479, 0.42811306509991676), (0.9486646645113018, 0.6449798314867623), (5.821753060052237, 0.9602905491391449), (2.336822618839431, -0.6047134422452635)]


x = [p[0] for p in points]
y = [p[1] for p in points]

k = np.linspace(-2, 7, 1000)

plt.scatter(x, y)
plt.plot(k, f(k), color='red')
plt.show()