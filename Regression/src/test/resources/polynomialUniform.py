import matplotlib.pyplot as plt
import numpy as np

def f(x):
    return 1.7209599999880796 + 2.361707990152848 * x + 0.4751248811491979 * x * x + 5.03669281102159 * x * x * x

points = [(5.075594492481822, 684.6162963743445), (4.942028893706607, 633.0187957173822), (2.7929491868069074, 121.78202501029598), (5.75285034094621, 990.1463433776065), (4.623673345255712, 520.6577397428345)]


x = [p[0] for p in points]
y = [p[1] for p in points]

k = np.linspace(-2, 7, 1000)

plt.scatter(x, y)
plt.plot(k, f(k), color='red')
plt.show()