package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.RELU_WITH_SHIFT;

public class RectifiedLinearUnitActivationFunction extends RegressionActivationFunction {

    public RectifiedLinearUnitActivationFunction(Double shift) {
        super(
            args -> args.stream().mapToDouble(Double::doubleValue).sum(),
            RELU_WITH_SHIFT.apply(shift != null ? shift : DEFAULT_SHIFT)
        );
    }

}
