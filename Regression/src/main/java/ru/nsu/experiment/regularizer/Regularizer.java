package ru.nsu.experiment.regularizer;

public abstract class Regularizer {

    protected final double lambda;

    protected Regularizer(double lambda) {
        this.lambda = lambda;
    }

    public abstract double evaluateLossRegularizationTerm(double[] coefficients);

}
