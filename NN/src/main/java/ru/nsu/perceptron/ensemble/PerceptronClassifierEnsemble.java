package ru.nsu.perceptron.ensemble;

import ru.nsu.perceptron.elementary.classification.ElementaryBinaryClassifier;
import ru.nsu.perceptron.elementary.classification.ElementaryMultiClassifier;
import ru.nsu.util.selection.classification.multi.MultiClassificationSample;

import java.util.List;
import java.util.stream.Collectors;

import static ru.nsu.util.constants.Constants.NUM_LABELS;

public class PerceptronClassifierEnsemble {

    private final List<ElementaryBinaryClassifier> elementaryBinaryClassifiers;

    private final ElementaryMultiClassifier elementaryMultiClassifier;

    public PerceptronClassifierEnsemble(
        List<ElementaryBinaryClassifier> elementaryBinaryClassifiers,
        ElementaryMultiClassifier elementaryMultiClassifier
    ) {
        this.elementaryBinaryClassifiers = elementaryBinaryClassifiers;
        this.elementaryMultiClassifier = elementaryMultiClassifier;
    }

    public Integer predict(MultiClassificationSample sample) {
        List<Double> forwardPropagatedValues = elementaryBinaryClassifiers.stream()
            .map(classifier -> classifier.forwardPropagation(sample.getFeature()))
            .collect(Collectors.toList());

        return elementaryMultiClassifier.predict(forwardPropagatedValues);
    }

    public void test(List<MultiClassificationSample> samples) {
        int[][] confusionMatrix = new int[NUM_LABELS][NUM_LABELS];

        for (MultiClassificationSample sample : samples) {
            int predicted = predict(sample);
            int actual = sample.getLabel();

            confusionMatrix[actual][predicted]++;
        }

        System.out.println("Test completed");

        printConfusionMatrix(confusionMatrix);
        printClassesMetrics(confusionMatrix);
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
        for (int[] matrix : confusionMatrix) {
            for (int i : matrix) {
                totalExamples += i;
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

        System.out.println();
    }

}
