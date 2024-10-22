package ru.nsu.experiment.gradient;

import ru.nsu.experiment.regularizer.Regularizer;

import static java.lang.Math.pow;
import static java.lang.Math.signum;

public class MeanAbsoluteErrorGradientStepFunction extends GradientStepFunction {

    public MeanAbsoluteErrorGradientStepFunction(Double learningRate, Regularizer regularizer) {
        super(learningRate, regularizer);
    }

    @Override
    protected double getDerivativePartOfAntiGradient(double argument, double actual, double predicted, int degree) {
        return (-signum(actual - predicted) * pow(argument, degree));
    }

    @Override
    protected double getCoefficientPartOfAntiGradient(int samplesNumber, double totalLoss) {
        return 1.0 / samplesNumber;
    }

}
