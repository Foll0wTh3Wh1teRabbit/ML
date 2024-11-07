package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.SIGMOID;
import static ru.nsu.util.activation.FunctionalOperators.SIGMOID_DERIVATIVE_FROM_ACTIVATION_VALUE;

public class SigmoidActivationFunction implements RegressionActivationFunction {

    @Override
    public Double apply(Double x) {
        return SIGMOID.apply(x);
    }

    @Override
    public double getDerivativeFromActivation(double activation) {
        return SIGMOID_DERIVATIVE_FROM_ACTIVATION_VALUE.apply(activation);
    }

}
