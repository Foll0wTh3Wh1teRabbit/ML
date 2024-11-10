package ru.nsu.util.selection.function;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class LinearWithSinusApproximatingFunction extends ApproximatingFunction {

    @Override
    protected Double evaluate(Double x) {
        return x * sin(2 * PI * x);
    }

}
