package ru.nsu.experiment.loss;

import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.tuple.Pair;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RootMeanSquaredErrorLossFunction extends LossFunction {

    public RootMeanSquaredErrorLossFunction(Regularizer regularizer) {
        super(regularizer);
    }

    @Override
    public double calculateLoss(double[] coefficients, List<Pair<Double, Double>> samples) {
        return sqrt(super.calculateLoss(coefficients, samples));
    }

    @Override
    protected double getDistance(double predicted, double actual) {
        return pow(actual - predicted, 2);
    }

}
