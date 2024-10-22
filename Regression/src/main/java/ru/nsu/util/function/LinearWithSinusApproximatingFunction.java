package ru.nsu.util.function;

import ru.nsu.util.function.error.EvaluationError;

import java.util.Optional;

import static java.lang.Math.*;

public class LinearWithSinusApproximatingFunction extends ApproximatingFunction {

    public LinearWithSinusApproximatingFunction(EvaluationError evaluationError) {
        super(evaluationError);
    }

    @Override
    public Double apply(Double x) {
        double error = Optional.ofNullable(evaluationError)
            .map(EvaluationError::get)
            .orElse(0.0);

        return x * sin(x) + error;
    }
}
