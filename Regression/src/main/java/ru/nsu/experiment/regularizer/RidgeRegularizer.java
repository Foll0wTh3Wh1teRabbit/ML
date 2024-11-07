package ru.nsu.experiment.regularizer;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class RidgeRegularizer extends Regularizer {

    public RidgeRegularizer(double lambda) {
        super(lambda);
    }

    @Override
    public double evaluateLossWithRegularization(double[] regressionModel) {
        return 0.0;
    }

    @Override
    public RealMatrix evaluateMatrixWithRegularization(RealMatrix xTx) {
        RealMatrix regularizationTerm =
            MatrixUtils.createRealIdentityMatrix(xTx.getRowDimension()).scalarMultiply(lambda);

        return xTx.subtract(regularizationTerm);
    }

}
