package ru.nsu.util.selection.function;

import static java.lang.Math.pow;

public class PolynomialApproximatingFunction extends ApproximatingFunction {

    @Override
    public Double evaluate(Double x) {
        return (5.0 * pow(x, 3) + pow(x, 2) + 5.0);
    }

}
