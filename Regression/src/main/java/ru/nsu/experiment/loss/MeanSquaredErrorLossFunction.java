package ru.nsu.experiment.loss;

import ru.nsu.experiment.regularizer.Regularizer;

import static java.lang.Math.pow;

public class MeanSquaredErrorLossFunction extends LossFunction {

    public MeanSquaredErrorLossFunction(Regularizer regularizer) {
        super(regularizer);
    }

    @Override
    protected double getDistance(double predicted, double actual) {
        return pow(predicted - actual, 2);
    }

}
