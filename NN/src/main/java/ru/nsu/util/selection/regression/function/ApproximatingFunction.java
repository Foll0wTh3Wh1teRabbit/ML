package ru.nsu.util.selection.regression.function;

import ru.nsu.util.selection.regression.function.error.EvaluationError;

import java.util.Optional;
import java.util.function.UnaryOperator;

public abstract class ApproximatingFunction implements UnaryOperator<Double> {

    protected final EvaluationError evaluationError;

    protected ApproximatingFunction(EvaluationError evaluationError) {
        this.evaluationError = evaluationError;
    }

    @Override
    public Double apply(Double x) {
        double error = Optional.ofNullable(evaluationError)
            .map(EvaluationError::get)
            .orElse(0.0);

        return getFunctionValue(x) + error;
    }

    protected abstract Double getFunctionValue(Double x);

}
