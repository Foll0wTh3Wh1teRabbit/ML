package ru.nsu.util.activation.regression;

import java.util.function.Function;

public interface RegressionActivationFunction extends Function<Double, Double> {

    double getDerivative(double x);

    double getDerivativeFromActivation(double activation);

}
