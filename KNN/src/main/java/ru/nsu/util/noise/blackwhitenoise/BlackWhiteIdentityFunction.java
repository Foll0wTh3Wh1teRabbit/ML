package ru.nsu.util.noise.blackwhitenoise;

import ru.nsu.matrix.Matrix;

public class BlackWhiteIdentityFunction extends BlackWhiteNoiseFunction {

    public BlackWhiteIdentityFunction() {
        super(0);
    }

    @Override
    public Matrix apply(Matrix matrix) {
        return matrix;
    }

}
