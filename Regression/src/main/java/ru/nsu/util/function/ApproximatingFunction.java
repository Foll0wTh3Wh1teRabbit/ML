package ru.nsu.util.function;

import ru.nsu.util.stochastic.StochasticValue;

import java.util.function.UnaryOperator;

public abstract class ApproximatingFunction implements UnaryOperator<Double> {

    protected final StochasticValue stochasticValue;

    protected ApproximatingFunction(StochasticValue stochasticValue) {
        this.stochasticValue = stochasticValue;
    }

    @Override
    public Double apply(Double x) {
        return evaluate(x) + stochasticValue.get();
    }

    protected abstract double evaluate(double x);

}
