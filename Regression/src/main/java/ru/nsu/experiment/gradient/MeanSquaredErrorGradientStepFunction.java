package ru.nsu.experiment.gradient;

import ru.nsu.experiment.regularizer.Regularizer;

import static java.lang.Math.pow;

public class MeanSquaredErrorGradientStepFunction extends GradientStepFunction {

    public MeanSquaredErrorGradientStepFunction(Double learningRate, Regularizer regularizer) {
        super(learningRate, regularizer);
    }

    @Override
    protected double getDerivativePartOfAntiGradient(double argument, double actual, double predicted, int degree) {
        return ((actual - predicted) * pow(argument, degree));
    }

    @Override
    protected double getCoefficientPartOfAntiGradient(int samplesNumber, double totalLoss) {
        return -2.0 / (samplesNumber);
    }

}
