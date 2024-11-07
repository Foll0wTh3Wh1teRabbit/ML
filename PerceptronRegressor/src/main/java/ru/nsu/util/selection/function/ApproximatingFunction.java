package ru.nsu.util.selection.function;

import ru.nsu.util.selection.error.EvaluationError;

import java.util.function.BiFunction;

public interface ApproximatingFunction extends BiFunction<Double, EvaluationError, Double> {
}
