package ru.nsu.util.selection.function;

import ru.nsu.util.selection.error.EvaluationError;

import static java.lang.Math.pow;

public class PolynomialApproximatingFunction implements ApproximatingFunction {

    @Override
    public Double apply(Double x, EvaluationError evaluationError) {
        return (5.0 * pow(x, 3) + pow(x, 2) + 5.0) + evaluationError.get();
    }

}
