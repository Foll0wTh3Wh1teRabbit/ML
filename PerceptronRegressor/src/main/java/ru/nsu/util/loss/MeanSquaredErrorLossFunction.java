package ru.nsu.util.loss;

import static java.lang.Math.pow;

public class MeanSquaredErrorLossFunction implements LossFunction {

    @Override
    public double calculate(double predicted, double actual) {
        return pow(predicted - actual, 2);
    }

    @Override
    public double calculateGradient(double predicted, double actual) {
        return 2 * (predicted - actual);
    }

}
