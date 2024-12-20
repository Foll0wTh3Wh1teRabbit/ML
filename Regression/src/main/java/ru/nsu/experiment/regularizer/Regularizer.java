package ru.nsu.experiment.regularizer;

import org.apache.commons.math.linear.RealMatrix;

public abstract class Regularizer {

    protected final double lambda;

    protected Regularizer(double lambda) {
        this.lambda = lambda;
    }

    public abstract double evaluateLossRegularizationTerm(double[] regressionModel);

    public abstract RealMatrix evaluateMatrixRegularizationTerm(int dimension);

}
