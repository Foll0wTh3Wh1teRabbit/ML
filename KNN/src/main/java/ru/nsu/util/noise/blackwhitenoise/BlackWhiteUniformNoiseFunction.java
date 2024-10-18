package ru.nsu.util.noise.blackwhitenoise;

import ru.nsu.matrix.Matrix;

import java.util.Random;

public class BlackWhiteUniformNoiseFunction extends BlackWhiteNoiseFunction {

    public BlackWhiteUniformNoiseFunction(int noisePercentage) {
        super(noisePercentage);
    }

    public BlackWhiteUniformNoiseFunction(int noisePercentage, NoiseType noiseType) {
        super(noisePercentage);

        this.noiseType = noiseType;
    }

    @Override
    public Matrix apply(Matrix matrix) {
        int[][] numArraysCopy = new int[matrix.numArrays.length][matrix.numArrays[0].length];
        for (int i = 0; i < matrix.numArrays.length; i++) {
            System.arraycopy(matrix.numArrays[i], 0, numArraysCopy[i], 0, matrix.numArrays[0].length);
        }

        Matrix noisedMatrix = new Matrix(numArraysCopy, matrix.label);

        int height = matrix.numArrays.length;
        int width = matrix.numArrays[0].length;
        int pixels = height * width;

        int pixelsToBeNoised = pixels * noisePercentage / 100;

        // Выбираем рандомный пиксель, в зависимости от цвета шума - зашумляем данным цветом
        for (int i = 0; i < pixelsToBeNoised; i++) {
            int heightCoord = Math.abs(new Random().nextInt() % height);
            int widthCoord = Math.abs(new Random().nextInt() % width);

            noisedMatrix.numArrays[heightCoord][widthCoord] = (noiseType == NoiseType.BLACK ? 0 : 255);
        }

        return noisedMatrix;
    }

}
