package ru.nsu.util.noise.greyscalenoise;

import ru.nsu.util.noise.NoiseFunction;

public abstract class GreyscaleNoiseFunction extends NoiseFunction {

    protected static final Integer EPSILON = 80;

    protected GreyscaleNoiseFunction(Integer noisePercentage) {
        super(noisePercentage);
    }

}
