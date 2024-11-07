package ru.nsu.util.activation.regression;

import java.util.function.Function;

public interface RegressionActivationFunction extends Function<Double, Double> {

    double getDerivativeFromActivation(double activation);

}
