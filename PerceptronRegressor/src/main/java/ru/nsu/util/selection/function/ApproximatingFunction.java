package ru.nsu.util.selection.function;

import ru.nsu.util.selection.stochastic.StochasticValue;

import java.util.function.BiFunction;

public abstract class ApproximatingFunction implements BiFunction<Double, StochasticValue, Double> {

    @Override
    public Double apply(Double x, StochasticValue error) {
        return evaluate(x) + error.get();
    }

    protected abstract Double evaluate(Double x);

}
