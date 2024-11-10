package ru.nsu.util.selection.function;

import static java.lang.Math.*;

public class CosinusApproximatingFunction extends ApproximatingFunction {

    @Override
    protected Double evaluate(Double x) {
        return cos(2 * PI * x);
    }

}
