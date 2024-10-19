package ru.nsu.experiment.recognition.greyscale;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.noise.greyscalenoise.GreyscaleIdentityFunction;
import ru.nsu.util.noise.greyscalenoise.GreyscaleNoiseFunction;
import ru.nsu.util.noise.greyscalenoise.GreyscaleUnevenNoiseFunction;
import ru.nsu.util.noise.greyscalenoise.GreyscaleUniformNoiseFunction;
import ru.nsu.util.distance.AbsoluteDistanceFunction;
import ru.nsu.util.distance.ChebyshevDistanceFunction;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.distance.EuclideanDistanceFunction;
import ru.nsu.util.distance.SquaredEuclideanDistanceFunction;

import java.util.stream.Stream;

public class GreyscaleRecognitionExperimentTest {

    private static Stream<Arguments> greyscaleRecognitionWithDifferentMetricsAndNoiseParameters() {
        return Stream.of(
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleIdentityFunction()),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUniformNoiseFunction(30)),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUniformNoiseFunction(45)),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUniformNoiseFunction(60)),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUnevenNoiseFunction(30)),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUnevenNoiseFunction(45)),
            Arguments.of(new AbsoluteDistanceFunction(), new GreyscaleUnevenNoiseFunction(60)),

            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleIdentityFunction()),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(30)),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(45)),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(60)),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(30)),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(45)),
            Arguments.of(new EuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(60)),


            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleIdentityFunction()),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(30)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(45)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUniformNoiseFunction(60)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(30)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(45)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new GreyscaleUnevenNoiseFunction(60)),


            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleIdentityFunction()),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUniformNoiseFunction(30)),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUniformNoiseFunction(45)),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUniformNoiseFunction(60)),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUnevenNoiseFunction(30)),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUnevenNoiseFunction(45)),
            Arguments.of(new ChebyshevDistanceFunction(), new GreyscaleUnevenNoiseFunction(60))
        );
    }

    @ParameterizedTest
    @MethodSource("greyscaleRecognitionWithDifferentMetricsAndNoiseParameters")
    public void greyscaleRecognitionWithDifferentMetricsAndNoise(
        DistanceFunction distanceFunction,
        GreyscaleNoiseFunction noiseFunction
    ) {
        for (int i = 3; i <= 9; i = i + 2) {
            System.out.println("Starting Greyscale Recognition for K = " + i);

            new GreyscaleRecognitionExperiment(i).launchExperiment(distanceFunction, noiseFunction);
        }

        System.out.println("Starting Greyscale Recognition for K = " + 101);

        new GreyscaleRecognitionExperiment(101).launchExperiment(distanceFunction, noiseFunction);
    }

}
