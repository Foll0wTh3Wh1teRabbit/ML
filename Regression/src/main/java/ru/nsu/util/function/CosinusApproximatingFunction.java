package ru.nsu.util.function;

import ru.nsu.util.stochastic.StochasticValue;

import static java.lang.Math.*;

public class CosinusApproximatingFunction extends ApproximatingFunction {

    public CosinusApproximatingFunction(StochasticValue stochasticValue) {
        super(stochasticValue);
    }

    @Override
    protected double evaluate(double x) {
        return cos(x);
    }

}
