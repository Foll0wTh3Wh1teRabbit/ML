package ru.nsu.experiment.recognition.blackwhite;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.recognition.greyscale.GreyscaleRecognitionExperiment;
import ru.nsu.util.distance.AbsoluteDistanceFunction;
import ru.nsu.util.distance.ChebyshevDistanceFunction;
import ru.nsu.util.distance.DistanceFunction;
import ru.nsu.util.distance.EuclideanDistanceFunction;
import ru.nsu.util.distance.SquaredEuclideanDistanceFunction;
import ru.nsu.util.noise.blackwhitenoise.BlackWhiteIdentityFunction;
import ru.nsu.util.noise.blackwhitenoise.BlackWhiteNoiseFunction;
import ru.nsu.util.noise.blackwhitenoise.BlackWhiteUnevenNoiseFunction;
import ru.nsu.util.noise.blackwhitenoise.BlackWhiteUniformNoiseFunction;

import java.util.stream.Stream;

import static ru.nsu.util.noise.blackwhitenoise.BlackWhiteNoiseFunction.NoiseType.*;

public class BlackWhiteRecognitionExperimentTest {

    private static Stream<Arguments> blackWhiteRecognitionWithDifferentMetricsAndNoiseParameters() {
        return Stream.of(
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteIdentityFunction()),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, BLACK)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, BLACK)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, BLACK)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, WHITE)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, BLACK)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, BLACK)),
            Arguments.of(new AbsoluteDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, BLACK)),


            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteIdentityFunction()),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, BLACK)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, BLACK)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, BLACK)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, WHITE)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, BLACK)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, BLACK)),
            Arguments.of(new EuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, BLACK)),


            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteIdentityFunction()),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, BLACK)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, BLACK)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, BLACK)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, WHITE)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, BLACK)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, BLACK)),
            Arguments.of(new SquaredEuclideanDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, BLACK)),


            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteIdentityFunction()),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(30, BLACK)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(45, BLACK)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUniformNoiseFunction(60, BLACK)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, WHITE)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(30, BLACK)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(45, BLACK)),
            Arguments.of(new ChebyshevDistanceFunction(), new BlackWhiteUnevenNoiseFunction(60, BLACK))
        );
    }

    @ParameterizedTest
    @MethodSource("blackWhiteRecognitionWithDifferentMetricsAndNoiseParameters")
    public void blackWhiteRecognitionWithDifferentMetricsAndNoise(
        DistanceFunction distanceFunction,
        BlackWhiteNoiseFunction noiseFunction
    ) {
        for (int i = 3; i <= 9; i = i + 2) {
            System.out.println("Starting BlackWhite Recognition for K = " + i + "\n");

            new GreyscaleRecognitionExperiment(i).launchExperiment(distanceFunction, noiseFunction);
        }

        System.out.println("Starting BlackWhite Recognition for K = " + 101);

        new GreyscaleRecognitionExperiment(101).launchExperiment(distanceFunction, noiseFunction);
    }

}
