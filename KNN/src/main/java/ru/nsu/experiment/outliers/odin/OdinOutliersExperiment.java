package ru.nsu.experiment.outliers.odin;

import ru.nsu.experiment.outliers.OutliersExperiment;
import ru.nsu.util.Pair;
import ru.nsu.util.distance.DistanceFunction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class OdinOutliersExperiment extends OutliersExperiment {

    private final Integer inDegreeNumber;

    public OdinOutliersExperiment(Integer kNearestNumber, Integer inDegreeNumber) {
        super(kNearestNumber, examplesWithOutliers.first(), examplesWithOutliers.second());

        this.inDegreeNumber = inDegreeNumber;
    }

    @Override
    public Set<Integer> launchExperiment(DistanceFunction distanceFunction) {
        List<Integer> verticesDegrees = getVerticesDegrees(distanceFunction);

        Set<Integer> outliersCandidates = new HashSet<>();
        for (int i = 0; i < verticesDegrees.size(); i++) {
            if (verticesDegrees.get(i) < inDegreeNumber) {
                outliersCandidates.add(i);
            }
        }

        printResults(
            this.getClass().getSimpleName(),
            distanceFunction.getClass().getSimpleName(),
            kNearestNumber,
            inDegreeNumber,
            outliersCandidates
        );

        return outliersCandidates;
    }

    /**
     * R - отношение ближайшего соседства, не симметричное.
     * aRb <--> b - один из ближайших соседей а
     */
    private List<Integer> getVerticesDegrees(DistanceFunction distanceFunction) {
        Integer[] verticesDegrees = new Integer[matrices.length];
        for (int i = 0; i < matrices.length; i++) {
            verticesDegrees[i] = 0;
        }

        for (int i = 0 ; i < matrices.length ; i++) {
            PriorityQueue<Pair<Double, Integer>> distancePrioritizedMatrices =
                new PriorityQueue<>(Comparator.comparing(Pair::first));

            for (int j = 0 ; j < matrices.length ; j++) {
                if (i == j) {
                    continue;
                }

                distancePrioritizedMatrices.add(
                    new Pair<>(
                        distanceFunction.apply(matrices[i], matrices[j]),
                        j
                    )
                );
            }

            for (int k = 0; k < Math.min(kNearestNumber, distancePrioritizedMatrices.size()); k++) {
                Integer indexToIncrement = distancePrioritizedMatrices.poll().second();
                verticesDegrees[indexToIncrement]++;
            }
        }

        return Arrays.asList(verticesDegrees);
    }

}
