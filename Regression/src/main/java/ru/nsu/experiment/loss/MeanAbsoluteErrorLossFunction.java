package ru.nsu.experiment.loss;

import ru.nsu.experiment.regularizer.Regularizer;

import static java.lang.Math.abs;

public class MeanAbsoluteErrorLossFunction extends LossFunction {

    public MeanAbsoluteErrorLossFunction(Regularizer regularizer) {
        super(regularizer);
    }

    @Override
    protected double getDistance(double predicted, double actual) {
        return abs(predicted - actual);
    }

}
