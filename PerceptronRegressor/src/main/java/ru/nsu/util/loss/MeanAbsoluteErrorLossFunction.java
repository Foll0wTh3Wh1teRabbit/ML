package ru.nsu.util.loss;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class MeanAbsoluteErrorLossFunction implements LossFunction {

    @Override
    public double calculate(double predicted, double actual) {
        return abs(predicted - actual);
    }

    @Override
    public double calculateGradient(double predicted, double actual) {
        return signum(predicted - actual);
    }

}
