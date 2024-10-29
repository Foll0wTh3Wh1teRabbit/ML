package ru.nsu.util.activation.regression;

import static ru.nsu.util.activation.FunctionalOperators.IDENTITY_WITH_SHIFT;

public class LinearActivationFunction extends RegressionActivationFunction {

    public LinearActivationFunction(Double shift) {
        super(
            args -> args.stream().mapToDouble(Double::doubleValue).sum(),
            IDENTITY_WITH_SHIFT.apply(shift != null ? shift : DEFAULT_SHIFT)
        );
    }
}
