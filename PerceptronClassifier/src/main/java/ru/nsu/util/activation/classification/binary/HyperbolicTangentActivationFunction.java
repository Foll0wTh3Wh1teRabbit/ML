package ru.nsu.util.activation.classification.binary;

import java.util.Collection;

import static ru.nsu.util.activation.FunctionalOperators.TANH_WITH_SHIFT;

public class HyperbolicTangentActivationFunction extends BinaryClassificationActivationFunction {

    private static final Double DEFAULT_TANH_THRESHOLD = 0.0;

    private final Double shift;

    public HyperbolicTangentActivationFunction(Double shift) {
        super(
            args -> args.stream().mapToDouble(Double::doubleValue).sum(),
            x -> TANH_WITH_SHIFT.apply(shift == null ? 0.0 : shift).apply(x) >= DEFAULT_TANH_THRESHOLD
        );

        this.shift = shift;
    }

    @Override
    public Double getActivationValue(Collection<Double> args) {
        Double reducedComponents = reduceFunction.apply(args);

        return TANH_WITH_SHIFT.apply(shift == null ? 0.0 : shift).apply(reducedComponents);
    }

}
