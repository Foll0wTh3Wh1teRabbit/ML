package ru.nsu.util.loss;

public interface LossFunction {

    double calculate(double output, double target);

    double calculateGradient(double output, double target);

}
