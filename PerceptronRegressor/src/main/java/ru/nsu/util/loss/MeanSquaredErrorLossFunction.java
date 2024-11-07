package ru.nsu.util.loss;

import static java.lang.Math.pow;

public class MeanSquaredErrorLossFunction implements LossFunction {

    @Override
    public Double apply(Double predicted, Double actual) {
        return pow(predicted - actual, 2);
    }

}
