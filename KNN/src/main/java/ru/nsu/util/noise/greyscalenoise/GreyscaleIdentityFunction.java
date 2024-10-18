package ru.nsu.util.noise.greyscalenoise;

import ru.nsu.matrix.Matrix;

public class GreyscaleIdentityFunction extends GreyscaleNoiseFunction {

    public GreyscaleIdentityFunction() {
        super(0);
    }

    @Override
    public Matrix apply(Matrix matrix) {
        return matrix;
    }

}
