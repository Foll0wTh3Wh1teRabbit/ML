package ru.nsu.experiment.outliers;

import ru.nsu.matrix.Matrix;
import ru.nsu.util.Pair;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.image.TrainTestSetSplitter;
import ru.nsu.util.noise.NoiseFunction;
import ru.nsu.util.noise.greyscalenoise.GreyscaleUnevenNoiseFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class OutliersExperiment {

    protected static final Pair<Matrix[], Set<Integer>> examplesWithOutliers;

    private static final String PATH_TO_GREYSCALE_DIR = "src/main/resources/MNIST_Greyscale/";

    static {
        NoiseFunction noiseFunction = new GreyscaleUnevenNoiseFunction(80);

        //Генерируем сплитованные train и test с целью зашумить test
        Pair<Matrix[], Matrix[]> trainTestSplit;
        try {
            trainTestSplit = TrainTestSetSplitter.trainTestSplit(PATH_TO_GREYSCALE_DIR, 99);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Зашумляем тестовую выборку
        for (int i = 0; i < trainTestSplit.second().length; ++i) {
            trainTestSplit.second()[i] = noiseFunction.apply(trainTestSplit.second()[i]);
        }

        //Объединяем тренировочную и тестовую выборки с запоминанием индексов выбросов
        List<Pair<Matrix, Integer>> unionOfExamples = new ArrayList<>();
        for (int i = 0; i < trainTestSplit.first().length + trainTestSplit.second().length; i++) {
            Matrix matrixToAdd = (i < trainTestSplit.first().length) ?
                trainTestSplit.first()[i] :
                trainTestSplit.second()[i - trainTestSplit.first().length];

            unionOfExamples.add(
                new Pair<>(matrixToAdd, i)
            );
        }

        //Генерируем рандомную перестановку экземпляров, содержащих выбросы
        Collections.shuffle(unionOfExamples);

        //Оставим только 10% экземпляров, при исходной выборке до посинения будем ждать результатов
        unionOfExamples = unionOfExamples.subList(0, unionOfExamples.size() / 10);

        //Получаем множество индексов выбросов в полученной перестановке
        Set<Integer> outliersIndices = new HashSet<>();
        for (int i = 0; i < unionOfExamples.size(); ++i) {
            if (unionOfExamples.get(i).second() >= trainTestSplit.first().length) {
                outliersIndices.add(i);
            }
        }

        Matrix[] matricesForExperiments = unionOfExamples.stream()
            .map(Pair::first)
            .toArray(Matrix[]::new);

        examplesWithOutliers = new Pair<>(
            matricesForExperiments,
            outliersIndices
        );
    }

    protected final Integer kNearestNumber;

    protected final Matrix[] matrices;

    public final Set<Integer> indicesOfOutliers;

    protected OutliersExperiment(Integer kNearestNumber, Matrix[] matrices, Set<Integer> indicesOfOutliers) {
        this.kNearestNumber = kNearestNumber;
        this.matrices = matrices;
        this.indicesOfOutliers = indicesOfOutliers;
    }

    public abstract Set<Integer> launchExperiment(DistanceFunction distanceFunction);

    protected void printResults(
        String algorithm,
        String distanceFunction,
        Integer kNearestNumber,
        Integer inDegreeNumber,
        Set<Integer> outliersCandidates
    ) {
        System.out.println("Experiment completed!");
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Distance function: " + distanceFunction);
        System.out.println("K-Nearest number: " + kNearestNumber);
        System.out.println("In-Degree number: " + inDegreeNumber);

        System.out.println("Confusion matrix: ");
        System.out.printf("%18s%10s\n", "Positive", "Negative");
        System.out.println("–".repeat(28));

        Set<Integer> truePositiveSet = new HashSet<>(outliersCandidates);
        truePositiveSet.retainAll(indicesOfOutliers);

        Set<Integer> falsePositiveSet = new HashSet<>(outliersCandidates);
        falsePositiveSet.removeAll(indicesOfOutliers);

        Set<Integer> trueNegativeSet = new HashSet<>();
        for (int i = 0; i < matrices.length; i++) {
            if (!outliersCandidates.contains(i) && !indicesOfOutliers.contains(i)) {
                trueNegativeSet.add(i);
            }
        }

        Set<Integer> falseNegativeSet = new HashSet<>(indicesOfOutliers);
        falseNegativeSet.removeAll(outliersCandidates);

        int truePositiveCount = truePositiveSet.size();
        int falsePositiveCount = falsePositiveSet.size();
        int trueNegativeCount = trueNegativeSet.size();
        int falseNegativeCount = falseNegativeSet.size();

        System.out.printf("%8s%10d%10d\n", "True | ", truePositiveCount, trueNegativeCount);
        System.out.printf("%8s%10d%10d\n", "False | ", falsePositiveCount, falseNegativeCount);

        double accuracy = (double) (truePositiveCount + trueNegativeCount) / (truePositiveCount + trueNegativeCount + falsePositiveCount + falseNegativeCount);

        double precision = (truePositiveCount + falsePositiveCount == 0) ?
            Double.NaN : (double) truePositiveCount / (truePositiveCount + falsePositiveCount);

        double recall = (truePositiveCount + falseNegativeCount == 0) ?
            Double.NaN : (double) truePositiveCount / (truePositiveCount + falseNegativeCount);

        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);

        System.out.println();
    }

}
