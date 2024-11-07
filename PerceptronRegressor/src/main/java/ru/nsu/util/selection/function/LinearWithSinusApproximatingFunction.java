package ru.nsu.util.selection.function;

import ru.nsu.util.selection.error.EvaluationError;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class LinearWithSinusApproximatingFunction implements ApproximatingFunction {

    @Override
    public Double apply(Double x, EvaluationError evaluationError) {
        return x * sin(2.0 * PI * x) + evaluationError.get();
    }

}
