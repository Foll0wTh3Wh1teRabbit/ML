package ru.nsu.experiment.outliers.odin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.distance.AbsoluteDistanceFunction;
import ru.nsu.util.distance.ChebyshevDistanceFunction;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.distance.EuclideanDistanceFunction;
import ru.nsu.util.distance.SquaredEuclideanDistanceFunction;

import java.util.stream.Stream;

public class OdinOutliersExperimentTest {

    private static Stream<Arguments> odinOutliersDetectionParameters() {
        return Stream.of(
            Arguments.of(new OdinOutliersExperiment(3, 3), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 3), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 3), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 3), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(3, 5), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 5), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 5), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 5), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(3, 7), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 7), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 7), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(3, 7), new ChebyshevDistanceFunction()),


            Arguments.of(new OdinOutliersExperiment(5, 3), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 3), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 3), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 3), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(5, 5), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 5), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 5), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 5), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(5, 7), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 7), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 7), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(5, 7), new ChebyshevDistanceFunction()),


            Arguments.of(new OdinOutliersExperiment(7, 3), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 3), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 3), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 3), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(7, 5), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 5), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 5), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 5), new ChebyshevDistanceFunction()),

            Arguments.of(new OdinOutliersExperiment(7, 7), new AbsoluteDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 7), new EuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 7), new SquaredEuclideanDistanceFunction()),
            Arguments.of(new OdinOutliersExperiment(7, 7), new ChebyshevDistanceFunction())
        );
    }

    @ParameterizedTest
    @MethodSource("odinOutliersDetectionParameters")
    public void odinOutliersDetection(OdinOutliersExperiment odinOutliersExperiment, DistanceFunction distanceFunction) {
        odinOutliersExperiment.launchExperiment(distanceFunction);
    }

}
