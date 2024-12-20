package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.TANH;
import static ru.nsu.util.activation.FunctionalOperators.TANH_DERIVATIVE;
import static ru.nsu.util.activation.FunctionalOperators.TANH_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class HyperbolicTangentActivationFunction implements RegressionActivationFunction {

    @Override
    public double value(double x) {
        return TANH.apply(x);
    }

    @Override
    public double getDerivative(double x) {
        return TANH_DERIVATIVE.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return TANH_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
