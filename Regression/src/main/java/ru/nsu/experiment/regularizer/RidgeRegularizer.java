package ru.nsu.experiment.regularizer;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class RidgeRegularizer extends Regularizer {

    public RidgeRegularizer(double lambda) {
        super(lambda);
    }

    @Override
    public double evaluateLossRegularizationTerm(double[] regressionModel) {
        double squaredCoefficientsSum = 0.0;

        for (double coefficient : regressionModel) {
            squaredCoefficientsSum += (coefficient * coefficient);
        }

        return lambda * squaredCoefficientsSum / 2;
    }

    @Override
    public RealMatrix evaluateMatrixRegularizationTerm(int dimension) {
        return MatrixUtils.createRealIdentityMatrix(dimension).scalarMultiply(lambda);
    }

}
