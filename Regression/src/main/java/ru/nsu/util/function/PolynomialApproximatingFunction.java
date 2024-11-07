package ru.nsu.util.function;

import ru.nsu.util.stochastic.StochasticValue;

import static java.lang.Math.*;

public class PolynomialApproximatingFunction extends ApproximatingFunction {

    public PolynomialApproximatingFunction(StochasticValue stochasticValue) {
        super(stochasticValue);
    }

    @Override
    protected double evaluate(double x) {
        return 5.0 * pow(x, 3) + pow(x, 2) + 5.0;
    }

}
