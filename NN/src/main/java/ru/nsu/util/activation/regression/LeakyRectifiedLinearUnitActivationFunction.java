package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.LEAKY_RELU_WITH_SHIFT;

public class LeakyRectifiedLinearUnitActivationFunction extends RegressionActivationFunction {

    public LeakyRectifiedLinearUnitActivationFunction(Double shift) {
        super(
            args -> args.stream().mapToDouble(Double::doubleValue).sum(),
            LEAKY_RELU_WITH_SHIFT.apply(shift != null ? shift : DEFAULT_SHIFT)
        );
    }

}
