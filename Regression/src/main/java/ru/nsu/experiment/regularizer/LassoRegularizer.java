package ru.nsu.experiment.regularizer;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class LassoRegularizer extends Regularizer {

    public LassoRegularizer(double lambda) {
        super(lambda);
    }

    @Override
    public double evaluateLossRegularizationTerm(double[] coefficients) {
        double coefficientSum = 0.0;

        for (double coefficient : coefficients) {
            coefficientSum += abs(coefficient);
        }

        return (lambda / 2) * coefficientSum;
    }

    @Override
    public double evaluateGradientRegularizationTerm(double coefficient) {
        return lambda * signum(coefficient);
    }

}
