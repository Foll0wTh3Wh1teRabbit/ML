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

    private static final Integer POLYNOMIAL_DEGREE = 3;

    private static Stream<Arguments> polynomialRegressionTestParameters() {
        return Stream.of(
            Arguments.of(
                new CosinusApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                null
            ),
            Arguments.of(
                new CosinusApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                new RidgeRegularizer(0.0001)
            ),

            Arguments.of(
                new CosinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                null
            ),
            Arguments.of(
                new CosinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                new RidgeRegularizer(0.0001)
            ),

            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                null
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                new RidgeRegularizer(0.0001)
            ),

            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                null
            ),
            Arguments.of(
                new LinearWithSinusApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                new RidgeRegularizer(0.0001)
            ),

            Arguments.of(
                new PolynomialApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                null
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new UniformDistributedStochasticValue(0.0, 0.1)
                ),
                new RidgeRegularizer(0.0001)
            ),

            Arguments.of(
                new PolynomialApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                null
            ),
            Arguments.of(
                new PolynomialApproximatingFunction(
                    new NormalDistributedStochasticValue(0.0, 0.033)
                ),
                new RidgeRegularizer(0.0001)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("polynomialRegressionTestParameters")
    public void polynomialRegressionTest(ApproximatingFunction approximatingFunction, Regularizer regularizer) {
        List<Sample> trainSamples = SelectionGenerator.generateSamples(approximatingFunction, 200000);
        List<Sample> testSamples = SelectionGenerator.generateSamples(approximatingFunction, 20000);

        System.out.println("Function: " + approximatingFunction.getClass().getSimpleName());
        System.out.println("Regularizer: " + (regularizer != null ? regularizer.getClass().getSimpleName() : null));

        double loss = PolynomialRegressionExperiment.launchRegressionWithTest(
            POLYNOMIAL_DEGREE,
            trainSamples,
            testSamples,
            new MeanSquaredErrorLossFunction(regularizer),
            regularizer
        );

        System.out.println("Loss: " + loss);
        System.out.println();
    }

}
