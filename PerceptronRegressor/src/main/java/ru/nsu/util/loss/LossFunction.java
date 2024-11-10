package ru.nsu.util.loss;

import java.util.function.BiFunction;

public interface LossFunction {

    double calculate(double output, double target); // Вычисление ошибки для одного выхода

    double calculateGradient(double output, double target); // Градиент ошибки по выходу

}
