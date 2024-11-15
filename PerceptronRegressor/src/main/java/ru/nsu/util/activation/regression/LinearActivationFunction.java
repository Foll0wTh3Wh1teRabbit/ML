package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.LINEAR;
import static ru.nsu.util.activation.FunctionalOperators.LINEAR_DERIVATIVE;
import static ru.nsu.util.activation.FunctionalOperators.LINEAR_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class LinearActivationFunction implements RegressionActivationFunction {

    @Override
    public double value(double x) {
        return LINEAR.apply(x);
    }

    @Override
    public double getDerivative(double x) {
        return LINEAR_DERIVATIVE.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return LINEAR_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
