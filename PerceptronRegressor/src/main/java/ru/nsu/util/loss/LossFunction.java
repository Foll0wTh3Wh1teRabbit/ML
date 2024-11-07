package ru.nsu.util.loss;

import java.util.function.BiFunction;

public interface LossFunction extends BiFunction<Double, Double, Double> {
}
