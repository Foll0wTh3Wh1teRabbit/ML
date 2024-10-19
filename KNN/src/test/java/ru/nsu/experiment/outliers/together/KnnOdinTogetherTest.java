package ru.nsu.experiment.outliers.together;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.outliers.knn.KnnOutliersExperiment;
import ru.nsu.experiment.outliers.odin.OdinOutliersExperiment;
import ru.nsu.util.distance.ChebyshevDistanceFunction;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.distance.EuclideanDistanceFunction;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class KnnOdinTogetherTest {

    private static Stream<Arguments> knnOdinTogetherParameters() {
        return Stream.of(
            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 3),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 5),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 7),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 3),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 5),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 7),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 3),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 5),
                new EuclideanDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 7),
                new EuclideanDistanceFunction()
            ),



            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 3),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 5),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(3),
                new OdinOutliersExperiment(3, 7),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 3),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 5),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(5),
                new OdinOutliersExperiment(5, 7),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 3),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 5),
                new ChebyshevDistanceFunction()
            ),
            Arguments.of(
                new KnnOutliersExperiment(7),
                new OdinOutliersExperiment(7, 7),
                new ChebyshevDistanceFunction()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("knnOdinTogetherParameters")
    public void knnOdinTogether(
        KnnOutliersExperiment knnOutliersExperiment,
        OdinOutliersExperiment odinOutliersExperiment,
        DistanceFunction distanceFunction
    ) {
        Set<Integer> originalOutliers = knnOutliersExperiment.indicesOfOutliers;
        Set<Integer> knnOutliersDetected = knnOutliersExperiment.launchExperiment(distanceFunction);
        Set<Integer> odinOutliersDetected = odinOutliersExperiment.launchExperiment(distanceFunction);

        Set<Integer> typeFirstOutliers = new HashSet<>(originalOutliers);
        typeFirstOutliers.removeAll(knnOutliersDetected);

        Set<Integer> typeSecondOutliers = new HashSet<>(originalOutliers);
        typeSecondOutliers.removeAll(odinOutliersDetected);

        System.out.println("Outliers, type0: " + originalOutliers.size());
        System.out.println("Outliers, type1: " + typeFirstOutliers.size());
        System.out.println("Outliers, type2: " + typeSecondOutliers.size());
        System.out.println();
    }

}
