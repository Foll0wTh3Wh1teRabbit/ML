package ru.nsu.experiment.recognition;

import ru.nsu.matrix.Matrix;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.noise.NoiseFunction;

import java.util.Arrays;
import java.util.PriorityQueue;

public class RecognitionExperiment {

    protected static final Integer K_NEAREST_NUMBER = 7;

    protected static final Integer TRAIN_SET_PERCENTAGE = 99;

    protected final Matrix[] trainSet;

    protected final Matrix[] testSet;

    protected RecognitionExperiment(Matrix[] trainSet, Matrix[] testSet) {
        this.trainSet = trainSet;
        this.testSet = testSet;
    }

    public void launchExperiment(
        DistanceFunction distanceFunction,
        NoiseFunction noiseFunction
    ) {
        int[][] confusionMatrix = new int[10][10];

        for (Matrix testMatrix : testSet) {
            Matrix noisedMatrix = noiseFunction.apply(testMatrix);

            PriorityQueue<Matrix> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> {
                    Double d1 = distanceFunction.apply(noisedMatrix, o1);
                    Double d2 = distanceFunction.apply(noisedMatrix, o2);

                    return Double.compare(d1, d2);
                }
            );

            priorityQueue.addAll(Arrays.asList(trainSet));

            Integer predictedClass = getPredictedClass(priorityQueue);
            confusionMatrix[testMatrix.label][predictedClass]++;
        }

        System.out.println("Experiment completed!");

        System.out.println("Distance function: " + distanceFunction.getClass().getSimpleName());
        System.out.println("Noise function: " + noiseFunction.getClass().getSimpleName());
        System.out.println("Noise percentage: " + noiseFunction.noisePercentage);

        printConfusionMatrix(confusionMatrix);
        printClassesMetrics(confusionMatrix);
    }

    private Integer getPredictedClass(PriorityQueue<Matrix> priorityQueue) {
        int[] classCounter = new int[10];
        for (int i = 0; i < K_NEAREST_NUMBER; ++i) {
            classCounter[priorityQueue.poll().label]++;
        }

        int curMaxCounter = -1;
        int curMaxClass = -1;
        for (int i = 0; i < 10; ++i) {
            if (classCounter[i] > curMaxCounter) {
                curMaxCounter = classCounter[i];
                curMaxClass = i;
            }
        }

        return curMaxClass;
    }

    private void printConfusionMatrix(int[][] confusionMatrix) {
        System.out.println("Confusion matrix: ");

        System.out.print(" ".repeat(8));
        for (int j = 0; j < confusionMatrix.length; j++) {
            System.out.printf("%7s", "Pred_" + j);
        }
        System.out.println();
        System.out.println("–".repeat(78));

        for (int i = 0; i < confusionMatrix.length; i++) {
            System.out.print("True_" + i + " |");
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                System.out.printf("%7d", confusionMatrix[i][j]);
            }
            System.out.println();
        }
    }

    private void printClassesMetrics(int[][] confusionMatrix) {
        int totalExamples = 0;
        for (int i = 0; i < confusionMatrix.length; i++) {
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                totalExamples += confusionMatrix[i][j];
            }
        }

        //Accuracy
        int correctlyPredicted = 0;
        for (int i = 0; i < confusionMatrix.length; i++) {
            correctlyPredicted += confusionMatrix[i][i];
        }

        System.out.println();
        System.out.println("Accuracy: " + (double) correctlyPredicted / totalExamples);
        System.out.println();

        //Precision for each class
        System.out.println("Precision: ");
        for (int i = 0; i < confusionMatrix.length; i++) {
            System.out.print(i + " | ");

            int predictedAsCurrent = 0;
            for (int j = 0; j < confusionMatrix.length; j++) {
                if (i == j) {
                    continue;
                }

                predictedAsCurrent += confusionMatrix[j][i];
            }

            System.out.println((double) confusionMatrix[i][i] / (predictedAsCurrent + confusionMatrix[i][i]));
        }
        System.out.println();

        //Recall for each class
        System.out.println("Recall: ");
        for (int i = 0; i < confusionMatrix.length; i++) {
            System.out.print(i + " | ");

            int predictedAsCurrent = 0;
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                if (i == j) {
                    continue;
                }

                predictedAsCurrent += confusionMatrix[i][j];
            }

            System.out.println((double) confusionMatrix[i][i] / (predictedAsCurrent + confusionMatrix[i][i]));
        }
    }

}
