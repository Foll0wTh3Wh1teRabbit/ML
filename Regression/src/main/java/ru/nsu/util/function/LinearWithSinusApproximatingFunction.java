package ru.nsu.util.function;

import ru.nsu.util.stochastic.StochasticValue;

import static java.lang.Math.*;

public class LinearWithSinusApproximatingFunction extends ApproximatingFunction {

    public LinearWithSinusApproximatingFunction(StochasticValue stochasticValue) {
        super(stochasticValue);
    }

    @Override
    protected double evaluate(double x) {
        return x * sin(x);
    }

}
