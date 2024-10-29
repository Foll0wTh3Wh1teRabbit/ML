import matplotlib.pyplot as plt
import numpy as np

def f(x):
    return 2.3520479999162345 + 2.4031600039202514 * x + 0.31180012816016006 * x * x + 5.062984132294271 * x * x * x

points = [(1.9095598788739006, 43.329755444501906), (2.319017771317092, 72.83276634813559), (3.5246549690646636, 236.38483187679682), (4.522569093020785, 487.93994294159097), (2.1615774660769262, 60.14001132829041)]


x = [p[0] for p in points]
y = [p[1] for p in points]

k = np.linspace(-2, 7, 1000)

plt.scatter(x, y)
plt.plot(k, f(k), color='red')
plt.show()