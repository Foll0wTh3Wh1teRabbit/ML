package org.example.learningalgorithm.regressor.loss;

import org.example.learningalgorithm.data.DataSet;

public interface RegressionLossFunction {

    double calculateLoss(double w0, double w1, DataSet dataSet);

    double getGradDescByW0(double w0, double w1, DataSet dataSet);

    double getGradDescByW1(double w0, double w1, DataSet dataSet);

}
