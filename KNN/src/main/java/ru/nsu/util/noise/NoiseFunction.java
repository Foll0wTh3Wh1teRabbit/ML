package ru.nsu.util.noise;

import ru.nsu.matrix.Matrix;

import java.util.function.UnaryOperator;

public abstract class NoiseFunction implements UnaryOperator<Matrix> {

    public final Integer noisePercentage;

    protected NoiseFunction(Integer noisePercentage) {
        this.noisePercentage = noisePercentage;
    }

}
