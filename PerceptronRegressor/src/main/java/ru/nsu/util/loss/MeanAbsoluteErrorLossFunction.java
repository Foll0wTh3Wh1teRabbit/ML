package ru.nsu.util.loss;

import static java.lang.Math.abs;

public class MeanAbsoluteErrorLossFunction implements LossFunction {

    @Override
    public Double apply(Double predicted, Double actual) {
        return abs(predicted - actual);
    }

}
