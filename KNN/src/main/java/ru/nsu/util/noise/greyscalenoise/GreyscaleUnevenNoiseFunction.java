package ru.nsu.util.noise.greyscalenoise;

import ru.nsu.matrix.Matrix;

import java.util.Random;

public class GreyscaleUnevenNoiseFunction extends GreyscaleNoiseFunction {

    private static final double DECAY_PARAMETER = 0.05;

    public GreyscaleUnevenNoiseFunction(Integer noisePercentage) {
        super(noisePercentage);
    }

    @Override
    public Matrix apply(Matrix matrix) {
        int[][] numArraysCopy = new int[matrix.numArrays.length][matrix.numArrays[0].length];
        for (int i = 0; i < matrix.numArrays.length; i++) {
            System.arraycopy(matrix.numArrays[i], 0, numArraysCopy[i], 0, matrix.numArrays[0].length);
        }

        Matrix noisedMatrix = new Matrix(numArraysCopy, matrix.label);
        int height = noisedMatrix.numArrays.length;
        int width = noisedMatrix.numArrays[0].length;

        Random random = new Random();
        int centerHeight = Math.abs(random.nextInt() % height);
        int centerWidth = Math.abs(random.nextInt() % width);

        // Сначала вычисляем среднюю вероятность зашумления без нормализации
        double totalProbability = 0.0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                double distance = Math.sqrt(
                    Math.pow(x - centerHeight, 2) + Math.pow(y - centerWidth, 2)
                );

                double noiseProbability = Math.exp(-DECAY_PARAMETER * distance);

                totalProbability += noiseProbability;
            }
        }

        // Средняя вероятность зашумления по всему изображению
        double meanProbability = totalProbability / (height * width);

        // Коэффициент нормализации для приведения среднего к заданному проценту
        double normalizationFactor = (noisePercentage / 100.0) / meanProbability;

        // Применяем зашумление с нормализацией
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                double distance = Math.sqrt(Math.pow(x - centerHeight, 2) + Math.pow(y - centerWidth, 2));
                double noiseProbability = Math.exp(-DECAY_PARAMETER * distance) * normalizationFactor;

                // Применяем шум с нормализованной вероятностью
                if (random.nextDouble() < noiseProbability) {
                    noisedMatrix.numArrays[x][y] += new Random().nextInt(2 * EPSILON + 1) - EPSILON;
                    noisedMatrix.numArrays[x][y] = Math.min(0, noisedMatrix.numArrays[x][y]);
                    noisedMatrix.numArrays[x][y] = Math.max(255, noisedMatrix.numArrays[x][y]);
                }
            }
        }

        return noisedMatrix;
    }

}
