package ru.nsu.util.noise.blackwhitenoise;

import ru.nsu.util.noise.NoiseFunction;

public abstract class BlackWhiteNoiseFunction extends NoiseFunction {

    protected NoiseType noiseType = NoiseType.WHITE;

    protected BlackWhiteNoiseFunction(Integer noisePercentage) {
        super(noisePercentage);
    }

    public enum NoiseType {
        BLACK, WHITE
    }

}
