package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.function.error.NormalDistributionEvaluationError;
import ru.nsu.util.function.error.UniformDistributionEvaluationError;

import java.io.IOException;
import java.util.stream.Stream;

public class CrossValidationTest {

    private static final Integer NUM_SAMPLES = 5;

    private static final Integer[] POLYNOMIAL_DEGREES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    };

    private static final UniformDistributionEvaluationError uniformError =
        new UniformDistributionEvaluationError(0.2,0.5);

    private static final NormalDistributionEvaluationError normalError =
        new NormalDistributionEvaluationError(0.0, 0.5);

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
