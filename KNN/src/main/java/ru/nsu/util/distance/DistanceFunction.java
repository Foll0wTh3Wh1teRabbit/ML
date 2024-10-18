package ru.nsu.util.distance;

import ru.nsu.matrix.Matrix;

import java.util.function.BiFunction;

public interface DistanceFunction extends BiFunction<Matrix, Matrix, Double> {
}
