package ru.nsu.experiment;

import lombok.Data;
import lombok.ToString;
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
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class CrossValidationTest {

    private static final Integer NUM_TRAIN_SAMPLES = 800;

    private static final Integer NUM_TEST_SAMPLES = 200;

    private static final Integer[] POLYNOMIAL_DEGREES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    };

    private static final Double[] LAMBDAS = {
        0.0000000001, 0.000000001, 0.00000001, 0.0000001, 0.000001, 0.00001,
        0.0001, 0.001, 0.01, 0.1, 1.0
    };

    private static final UniformDistributedStochasticValue uniformError =
        new UniformDistributedStochasticValue(0.2,0.2);

    private static final NormalDistributedStochasticValue normalError =
        new NormalDistributedStochasticValue(0.2, 0.0);

    private static Stream<Arguments> crossValidationTestParameters() {
        return Stream.of(
            Arguments.of(new LinearWithSinusApproximatingFunction(uniformError)),
            Arguments.of(new LinearWithSinusApproximatingFunction(normalError)),
            Arguments.of(new CosinusApproximatingFunction(uniformError)),
            Arguments.of(new CosinusApproximatingFunction(normalError)),
            Arguments.of(new PolynomialApproximatingFunction(uniformError)),
            Arguments.of(new PolynomialApproximatingFunction(normalError))
        );
    }

    @ParameterizedTest
    @MethodSource("crossValidationTestParameters")
    public void crossValidationTest(ApproximatingFunction function) throws IOException {
        PriorityQueue<QueueParameters> parametersWithLossValue =
            new PriorityQueue<>(Comparator.comparing(Pair::getSecond));

        List<Sample> trainSamples = SelectionGenerator.generateSamples(function, NUM_TRAIN_SAMPLES);
        List<Sample> testSamples = SelectionGenerator.generateSamples(function, NUM_TEST_SAMPLES);

        for (Integer polynomialDegree : POLYNOMIAL_DEGREES) {
            for (Double lambda : LAMBDAS) {
                Regularizer regularizer = new RidgeRegularizer(lambda);

                double loss = PolynomialRegressionExperiment.launchRegressionWithTest(
                    polynomialDegree,
                    trainSamples,
                    testSamples,
                    new MeanSquaredErrorLossFunction(regularizer),
                    regularizer
                );

                parametersWithLossValue.add(
                    new QueueParameters(
                        new CrossValidationParams(polynomialDegree, lambda),
                        loss
                    )
                );
            }
        }

        System.out.println("Cross-validation completed, optimal params: " + parametersWithLossValue.poll());
    }


    @Data
    private static class Pair<F, S>{

        private final F first;

        private final S second;

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }

    }

    private static class CrossValidationParams extends Pair<Integer, Double> {

        public CrossValidationParams(Integer first, Double second) {
            super(first, second);
        }

    }

    private static class QueueParameters extends Pair<CrossValidationParams, Double> {

        public QueueParameters(CrossValidationParams first, Double second) {
            super(first, second);
        }

    }

}
