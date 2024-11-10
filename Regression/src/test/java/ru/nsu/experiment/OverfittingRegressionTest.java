package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.selection.Sample;
import ru.nsu.util.selection.SelectionGenerator;
import ru.nsu.util.stochastic.NormalDistributedStochasticValue;
import ru.nsu.util.stochastic.UniformDistributedStochasticValue;

import java.util.List;
import java.util.stream.Stream;

public class OverfittingRegressionTest {

    private static Stream<Arguments> overfittingRegressionTestParameters() {
        return Stream.of(
            Arguments.of(
                new CosinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                )
            ),
            Arguments.of(
                new CosinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.2, 0.2)
                )
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                )
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.2, 0.2)
                )
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                )
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new NormalDistributedStochasticValue(0.2, 0.2)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("overfittingRegressionTestParameters")
    public void overfittingRegressionTest(ApproximatingFunction approximatingFunction) {
        List<Sample> samples = SelectionGenerator.generateSamples(approximatingFunction, 5);

        PolynomialRegressionExperiment.launchRegression(
            15,
            samples,
            null
        );
    }

}
