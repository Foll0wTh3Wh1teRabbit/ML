package ru.nsu.util.distance;

import ru.nsu.matrix.Matrix;

public class AbsoluteDistanceFunction implements DistanceFunction {

    @Override
    public Double apply(Matrix firstMatrix, Matrix secondMatrix) {
        int acc = 0;

        for (int i = 0; i < firstMatrix.numArrays.length; ++i) {
            for (int j = 0; j < firstMatrix.numArrays[i].length; ++j) {
                acc += Math.abs(firstMatrix.numArrays[i][j] - secondMatrix.numArrays[i][j]);
            }
        }

        return (double) acc;
    }

}
