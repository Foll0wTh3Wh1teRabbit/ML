package ru.nsu.util.activation.classification.binary;

import ru.nsu.util.activation.ActivationFunction;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public abstract class BinaryClassificationActivationFunction extends ActivationFunction<Double, Boolean> {

    protected final Function<Double, Boolean> thresholdFunction;

    protected BinaryClassificationActivationFunction(
        Function<Collection<Double>, Double> reduceFunction,
        Function<Double, Boolean> thresholdFunction
    ) {
        super(reduceFunction);

        this.thresholdFunction = thresholdFunction;
    }

    @Override
    public Boolean applyActivation(Collection<Double> args) {
        Double reducedComponents = reduceFunction.apply(args);

        return thresholdFunction.apply(reducedComponents);
    }

    public abstract Double getActivationValue(Collection<Double> args);

    protected static Double getThreshold(Double defaultThreshold, Double thresholdShift) {
        return Optional.ofNullable(thresholdShift)
            .map(it -> it + defaultThreshold)
            .orElse(defaultThreshold);
    }

}
