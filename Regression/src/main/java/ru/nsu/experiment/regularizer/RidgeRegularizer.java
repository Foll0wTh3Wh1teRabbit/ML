package ru.nsu.experiment.regularizer;

public class RidgeRegularizer extends Regularizer {

    public RidgeRegularizer(double lambda) {
        super(lambda);
    }

    @Override
    public double evaluateLossRegularizationTerm(double[] coefficients) {
        double squaredCoefficientsSum = 0.0;

        for (double coefficient : coefficients) {
            squaredCoefficientsSum += (coefficient * coefficient);
        }

        return (lambda / 2) * squaredCoefficientsSum;
    }

}
