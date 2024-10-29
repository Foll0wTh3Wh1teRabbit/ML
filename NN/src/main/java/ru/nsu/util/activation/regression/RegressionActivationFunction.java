package ru.nsu.util.activation.regression;

import ru.nsu.util.activation.ActivationFunction;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class RegressionActivationFunction extends ActivationFunction<Double, Double> {

    protected static final Double DEFAULT_SHIFT = 0.0;

    protected final UnaryOperator<Double> transformFunction;

    protected RegressionActivationFunction(
        Function<Collection<Double>, Double> reduceFunction,
        UnaryOperator<Double> transformFunction
    ) {
        super(reduceFunction);

        this.transformFunction = transformFunction;
    }

    @Override
    public Double applyActivation(Collection<Double> args) {
        Double reducedComponents = reduceFunction.apply(args);

        return transformFunction.apply(reducedComponents);
    }

}
