package ru.nsu.util.activation.classification.binary;

import java.util.Collection;

import static ru.nsu.util.activation.FunctionalOperators.SIGMOID_WITH_SHIFT;

public class SigmoidActivationFunction extends BinaryClassificationActivationFunction {

    private static final Double DEFAULT_SIGMOID_THRESHOLD = 0.5;

    private final Double shift;

    public SigmoidActivationFunction(Double shift) {
        super(
            args -> args.stream().mapToDouble(Double::doubleValue).sum(),
            x -> SIGMOID_WITH_SHIFT.apply(shift == null ? 0.0 : shift).apply(x) >= DEFAULT_SIGMOID_THRESHOLD
        );

        this.shift = shift;
    }

    @Override
    public Double getActivationValue(Collection<Double> args) {
        Double reducedComponents = reduceFunction.apply(args);

        return SIGMOID_WITH_SHIFT.apply(shift == null ? 0.0 : shift).apply(reducedComponents);
    }

}
