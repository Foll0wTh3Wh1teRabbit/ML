package ru.nsu.util.function;

import ru.nsu.util.function.error.EvaluationError;

import java.util.function.UnaryOperator;

public abstract class ApproximatingFunction implements UnaryOperator<Double> {

    protected final EvaluationError evaluationError;

    protected ApproximatingFunction(EvaluationError evaluationError) {
        this.evaluationError = evaluationError;
    }

}
