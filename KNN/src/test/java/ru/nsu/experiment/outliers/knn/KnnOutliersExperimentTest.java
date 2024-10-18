package ru.nsu.experiment.outliers.knn;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.distance.AbsoluteDistanceFunction;
import ru.nsu.util.distance.ChebyshevDistanceFunction;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.distance.EuclideanDistanceFunction;
import ru.nsu.util.distance.SquaredEuclideanDistanceFunction;

import java.util.stream.Stream;

public class KnnOutliersExperimentTest {

    private static Stream<Arguments> knnOutliersDetectionParameters() {
        return Stream.of(
            Arguments.of(new KnnOutliersExperiment(1), new AbsoluteDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(1), new EuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(1), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(1), new ChebyshevDistanceFunction()),

            Arguments.of(new KnnOutliersExperiment(3), new AbsoluteDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(3), new EuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(3), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(3), new ChebyshevDistanceFunction()),

            Arguments.of(new KnnOutliersExperiment(5), new AbsoluteDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(5), new EuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(5), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(5), new ChebyshevDistanceFunction()),

            Arguments.of(new KnnOutliersExperiment(7), new AbsoluteDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(7), new EuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(7), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new KnnOutliersExperiment(7), new ChebyshevDistanceFunction())
        );
    }

    @ParameterizedTest
    @MethodSource("knnOutliersDetectionParameters")
    public void knnOutliersDetection(KnnOutliersExperiment knnOutliersExperiment, DistanceFunction distanceFunction) {
        knnOutliersExperiment.launchExperiment(distanceFunction);
    }

}
