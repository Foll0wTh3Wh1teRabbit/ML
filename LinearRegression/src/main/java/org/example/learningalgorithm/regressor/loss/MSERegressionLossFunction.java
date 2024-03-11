package org.example.learningalgorithm.regressor.loss;

import org.example.learningalgorithm.data.DataSet;

public class MSERegressionLossFunction implements RegressionLossFunction {

    @Override
    public double calculateLoss(double w0, double w1, DataSet dataSet) {
        return dataSet.getPoints().stream()
            .map(point -> (w0 * point.getX() + w1 - point.getY()) * (w0 * point.getX() + w1 - point.getY()))
            .reduce(Double::sum)
            .map(it -> it / (double) dataSet.getPoints().size())
            .get();
    }

    @Override
    public double getGradDescByW0(double w0, double w1, DataSet dataSet) {
        return dataSet.getPoints().stream()
            .map(point -> (-2) * (point.getY() - w0 * point.getX() - w1) * point.getX())
            .reduce(Double::sum)
            .map(it -> it / (dataSet.getPoints().size() * dataSet.getPoints().size()))
            .get();
    }

    @Override
    public double getGradDescByW1(double w0, double w1, DataSet dataSet) {
        return dataSet.getPoints().stream()
            .map(point -> (-2) * (point.getY() - w0 * point.getX() - w1))
            .reduce(Double::sum)
            .map(it -> it / (double) dataSet.getPoints().size())
            .get();
    }

}
