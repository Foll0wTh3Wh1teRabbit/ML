package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.LEAKY_RELU;
import static ru.nsu.util.activation.FunctionalOperators.LEAKY_RELU_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class LeakyReLUActivationFunction implements RegressionActivationFunction {

    @Override
    public Double apply(Double x) {
        return LEAKY_RELU.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return LEAKY_RELU_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
