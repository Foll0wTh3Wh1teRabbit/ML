package ru.nsu.experiment.gradient;

import ru.nsu.experiment.regularizer.Regularizer;

import static java.lang.Math.pow;

public class RootMeanSquaredErrorGradientStepFunction extends GradientStepFunction {

    private static final double epsilon = 1e-2;

    public RootMeanSquaredErrorGradientStepFunction(Double learningRate, Regularizer regularizer) {
        super(learningRate, regularizer);
    }

    @Override
    protected double getDerivativePartOfAntiGradient(double argument, double actual, double predicted, int degree) {
        return ((actual - predicted) * pow(argument, degree));
    }

    @Override
    protected double getCoefficientPartOfAntiGradient(int samplesNumber, double totalLoss) {
        return -1.0 / (samplesNumber * Math.max(totalLoss, epsilon));
    }

}
