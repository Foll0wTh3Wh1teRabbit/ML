package ru.nsu.util.distance;

import ru.nsu.matrix.Matrix;

public class EuclideanDistanceFunction implements DistanceFunction {

    @Override
    public Double apply(Matrix firstMatrix, Matrix secondMatrix) {
        double acc = 0.0;

        for (int i = 0; i < firstMatrix.numArrays.length; ++i) {
            for (int j = 0; j < firstMatrix.numArrays[i].length; ++j) {
                acc += Math.pow(firstMatrix.numArrays[i][j] - secondMatrix.numArrays[i][j], 2);
            }
        }

        return Math.sqrt(acc);
    }

}
