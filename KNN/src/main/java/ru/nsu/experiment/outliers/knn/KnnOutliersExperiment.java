package ru.nsu.experiment.outliers.knn;

import ru.nsu.experiment.outliers.OutliersExperiment;
import ru.nsu.util.Pair;
import ru.nsu.util.distance.DistanceFunction;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KnnOutliersExperiment extends OutliersExperiment {

    public KnnOutliersExperiment(Integer kNearestNumber) {
        super(kNearestNumber, examplesWithOutliers.first(), examplesWithOutliers.second());
    }

    @Override
    public void launchExperiment(DistanceFunction distanceFunction) {
        Set<Integer> outliersCandidates = detectOutliers(distanceFunction);

        printResults(
            this.getClass().getSimpleName(),
            distanceFunction.getClass().getSimpleName(),
            kNearestNumber,
            null,
            outliersCandidates
        );
    }

    public Set<Integer> detectOutliers(DistanceFunction distanceFunction) {
        Set<Integer> outliersCandidates = new HashSet<>();

        for (int i = 0; i < matrices.length; i++) {
            PriorityQueue<Pair<Double, Integer>> prioritizedClassesByDistance =
                new PriorityQueue<>(Comparator.comparing(Pair::first));

            for (int j = 0; j < matrices.length; j++) {
                if (i == j) {
                    continue;
                }

                prioritizedClassesByDistance.add(
                    new Pair<>(
                        distanceFunction.apply(matrices[i], matrices[j]),
                        matrices[j].label
                    )
                );
            }

            int[] classCount = new int[10];
            for (int j = 0; j < Math.min(kNearestNumber, prioritizedClassesByDistance.size()); ++j) {
                classCount[prioritizedClassesByDistance.poll().second()]++;
            }

            int mostFrequentClass = -1;
            int quantityOfMostFrequentClass = -1;
            for (int j = 0; j < classCount.length; ++j) {
                if (classCount[j] > quantityOfMostFrequentClass) {
                    mostFrequentClass = j;
                    quantityOfMostFrequentClass = classCount[j];
                }
            }

            if (!(mostFrequentClass == matrices[i].label)) {
                outliersCandidates.add(i);
            }
        }

        return outliersCandidates;
    }

}
