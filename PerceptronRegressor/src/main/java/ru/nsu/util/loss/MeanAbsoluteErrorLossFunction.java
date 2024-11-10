package ru.nsu.util.loss;

import static java.lang.Math.abs;

public class MeanAbsoluteErrorLossFunction implements LossFunction {

    @Override
    public double calculate(double predicted, double actual) {
        return abs(predicted - actual);
    }

    @Override
    public double calculateGradient(double predicted, double actual) {
        return 2 * (predicted - actual);
    }

}