package ru.nsu.util.selection.regression.function;

import ru.nsu.util.selection.regression.function.error.EvaluationError;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class LinearWithSinusApproximatingFunction extends ApproximatingFunction {

    public LinearWithSinusApproximatingFunction(EvaluationError evaluationError) {
        super(evaluationError);
    }

    @Override
    protected Double getFunctionValue(Double x) {
        return x * sin(2 * PI * x);
    }

}
