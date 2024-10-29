package ru.nsu.util.selection.regression.function;

import ru.nsu.util.selection.regression.function.error.EvaluationError;

import static java.lang.Math.pow;

public class PolynomialApproximatingFunction extends ApproximatingFunction {

    public PolynomialApproximatingFunction(EvaluationError evaluationError) {
        super(evaluationError);
    }

    @Override
    protected Double getFunctionValue(Double x) {
        return 5 * pow(x, 3) + pow(x, 2) + 5;
    }

}
