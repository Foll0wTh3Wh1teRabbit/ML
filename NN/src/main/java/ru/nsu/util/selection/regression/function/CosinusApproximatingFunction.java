package ru.nsu.util.selection.regression.function;

import ru.nsu.util.selection.regression.function.error.EvaluationError;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class CosinusApproximatingFunction extends ApproximatingFunction {

    public CosinusApproximatingFunction(EvaluationError evaluationError) {
        super(evaluationError);
    }

    @Override
    protected Double getFunctionValue(Double x) {
        return cos(2 * PI * x);
    }

}
