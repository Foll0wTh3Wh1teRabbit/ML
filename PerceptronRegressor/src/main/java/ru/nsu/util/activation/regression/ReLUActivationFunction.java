package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.RELU;
import static ru.nsu.util.activation.FunctionalOperators.RELU_DERIVATIVE;
import static ru.nsu.util.activation.FunctionalOperators.RELU_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class ReLUActivationFunction implements RegressionActivationFunction {

    @Override
    public double value(double x) {
        return RELU.apply(x);
    }

    @Override
    public double getDerivative(double x) {
        return RELU_DERIVATIVE.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return RELU_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
