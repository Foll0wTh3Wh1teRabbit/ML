package ru.nsu.util.noise.greyscalenoise;

import ru.nsu.matrix.Matrix;

import java.util.Random;

public class GreyscaleUniformNoiseFunction extends GreyscaleNoiseFunction {

    public GreyscaleUniformNoiseFunction(Integer noisePercentage) {
        super(noisePercentage);
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

        // Выбираем рандомный пиксель со значением х, делаем его значение рандомным в eps-окрестности x
        for (int i = 0; i < pixelsToBeNoised; i++) {
            int x = Math.abs(new Random().nextInt() % height);
            int y = Math.abs(new Random().nextInt() % width);

            noisedMatrix.numArrays[x][y] += new Random().nextInt(2 * EPSILON + 1) - EPSILON;
            noisedMatrix.numArrays[x][y] = Math.min(0, noisedMatrix.numArrays[x][y]);
            noisedMatrix.numArrays[x][y] = Math.max(255, noisedMatrix.numArrays[x][y]);
        }

        return noisedMatrix;
    }

}
