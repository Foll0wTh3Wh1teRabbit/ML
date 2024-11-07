package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.TANH;
import static ru.nsu.util.activation.FunctionalOperators.TANH_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class HyperbolicTangentActivationFunction implements RegressionActivationFunction {

    @Override
    public Double apply(Double x) {
        return TANH.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return TANH_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
