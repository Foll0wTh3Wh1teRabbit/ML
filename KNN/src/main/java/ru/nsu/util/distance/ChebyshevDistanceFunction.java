package ru.nsu.util.distance;

import ru.nsu.matrix.Matrix;

public class ChebyshevDistanceFunction implements DistanceFunction {

    @Override
    public Double apply(Matrix firstMatrix, Matrix secondMatrix) {
        int curMaxDistance = 0;

        for (int i = 0; i < firstMatrix.numArrays.length; ++i) {
            for (int j = 0; j < firstMatrix.numArrays[i].length; ++j) {
                curMaxDistance = Math.max(
                    curMaxDistance,
                    Math.abs(firstMatrix.numArrays[i][j] - secondMatrix.numArrays[i][j])
                );
            }
        }

        return (double) curMaxDistance;
    }

}
