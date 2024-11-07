package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.loss.MeanSquaredErrorLossFunction;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.experiment.regularizer.RidgeRegularizer;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.selection.Sample;
import ru.nsu.util.selection.SelectionGenerator;
import ru.nsu.util.stochastic.NormalDistributedStochasticValue;
import ru.nsu.util.stochastic.UniformDistributedStochasticValue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PolynomialRegressionTest {

    private static Stream<Arguments> polynomialRegressionTestParameters() {
        return Stream.of(
            Arguments.of(
                new CosinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                null
            ),
            Arguments.of(
                new CosinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                new RidgeRegularizer(0.001)
            ),

            Arguments.of(
                new CosinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                null
            ),
            Arguments.of(
                new CosinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                new RidgeRegularizer(0.001)
            ),

            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                null
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                new RidgeRegularizer(0.001)
            ),

            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                null
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                new RidgeRegularizer(0.001)
            ),

            Arguments.of(
                new PolynomialApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                null
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new UniformDistributedStochasticValue(-0.5, 0.5)
                ),
                new RidgeRegularizer(0.001)
            ),

            Arguments.of(
                new PolynomialApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                null
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.33)
                ),
                new RidgeRegularizer(0.001)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("polynomialRegressionTestParameters")
    public void polynomialRegressionTest(ApproximatingFunction approximatingFunction, Regularizer regularizer) throws IOException {
        List<Sample> trainSamples = SelectionGenerator.generateSamples(approximatingFunction, 200);
        List<Sample> testSamples = SelectionGenerator.generateSamples(approximatingFunction, 20);

        for (int i = 1; i < 16; ++i) {
            System.out.println("Approximating with degree = " + i);

            new PolynomialRegressionExperiment().launchRegressionWithTest(
                i,
                trainSamples,
                testSamples,
                new MeanSquaredErrorLossFunction(regularizer),
                regularizer
            );
        }
    }

}
