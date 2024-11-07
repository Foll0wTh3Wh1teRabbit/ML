package ru.nsu.util.selection.function;

import ru.nsu.util.selection.error.EvaluationError;

import static java.lang.Math.*;

public class CosinusApproximatingFunction implements ApproximatingFunction {

    @Override
    public Double apply(Double x, EvaluationError evaluationError) {
        return cos(2.0 * PI * x) + evaluationError.get();
    }

}
