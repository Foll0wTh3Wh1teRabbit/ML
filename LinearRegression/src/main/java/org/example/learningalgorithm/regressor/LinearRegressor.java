package org.example.learningalgorithm.regressor;

import org.example.learningalgorithm.data.DataSet;
import org.example.learningalgorithm.regressor.loss.MSERegressionLossFunction;
import org.example.learningalgorithm.regressor.loss.RegressionLossFunction;

/**
 * Solution implemented like f(x) = w0 * x + w1
 */
public class LinearRegressor implements Regressor {

    private static final double learningRate = 0.001;

    private final DataSet dataSet;

    private final RegressionLossFunction regressionLossFunction;

    private double w0;

    private double w1;

    public LinearRegressor(DataSet dataSet) {
        this.w0 = 0;
        this.w1 = 0;

        this.dataSet = dataSet;
        this.regressionLossFunction = new MSERegressionLossFunction();
    }

    @Override
    public void launchIteration() {
        double loss = regressionLossFunction.calculateLoss(w0, w1, dataSet);

        System.out.printf("Current loss: %f, w0=%f, w1=%f\n", loss, w0, w1);

        w0 = w0 - learningRate * regressionLossFunction.getGradDescByW0(w0, w1, dataSet);
        w1 = w1 - learningRate * regressionLossFunction.getGradDescByW1(w0, w1, dataSet);
    }

    public double getW0() {
        return w0;
    }

    public double getW1() {
        return w1;
    }

}
