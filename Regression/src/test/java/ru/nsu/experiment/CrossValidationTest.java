package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.stochastic.NormalDistributedStochasticValue;
import ru.nsu.util.stochastic.UniformDistributedStochasticValue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class CrossValidationTest {

    private static final Integer NUM_SAMPLES = 5;

    private static final Integer[] POLYNOMIAL_DEGREES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    };

    private static final Double[] LAMBDAS = {
        0.0000000001, 0.000000001, 0.00000001, 0.0000001, 0.000001, 0.00001,
        0.0001, 0.001, 0.01, 0.1, 1.0
    };

    private static final UniformDistributedStochasticValue uniformError =
        new UniformDistributedStochasticValue(0.2,0.5);

    private static final NormalDistributedStochasticValue normalError =
        new NormalDistributedStochasticValue(0.0, 0.5);

    private static Stream<Arguments> crossValidationTestParameters() {
        return Stream.of(
            Arguments.of("src/test/resources/linearWithSinusUniform.txt", new LinearWithSinusApproximatingFunction(uniformError)),
            Arguments.of("src/test/resources/linearWithSinusNormal.txt", new LinearWithSinusApproximatingFunction(normalError)),
            Arguments.of("src/test/resources/cosinusUniform.txt", new CosinusApproximatingFunction(uniformError)),
            Arguments.of("src/test/resources/cosinusNormal.txt", new CosinusApproximatingFunction(normalError)),
            Arguments.of("src/test/resources/polynomialUniform.txt", new PolynomialApproximatingFunction(uniformError)),
            Arguments.of("src/test/resources/polynomialNormal.txt", new PolynomialApproximatingFunction(normalError))
        );
    }

    @ParameterizedTest
    @MethodSource("crossValidationTestParameters")
    public void crossValidationTest(String filename, ApproximatingFunction function) throws IOException {

    }

}
