package ru.nsu.util.function;

import ru.nsu.util.function.error.EvaluationError;

import java.util.Optional;

import static java.lang.Math.*;

public class PolynomialApproximatingFunction extends ApproximatingFunction {

    public PolynomialApproximatingFunction(EvaluationError evaluationError) {
        super(evaluationError);
    }

    @Override
    public Double apply(Double x) {
        double error = Optional.ofNullable(evaluationError)
            .map(EvaluationError::get)
            .orElse(0.0);

        return (5 * pow(x, 3) + pow(x, 2) + 5) + error;
    }

}
