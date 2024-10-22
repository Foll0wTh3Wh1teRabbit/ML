package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.gradient.MeanAbsoluteErrorGradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.experiment.loss.MeanAbsoluteErrorLossFunction;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.experiment.regularizer.RidgeRegularizer;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.function.error.NormalDistributionEvaluationError;
import ru.nsu.util.function.error.UniformDistributionEvaluationError;
import ru.nsu.util.tuple.Pair;
import ru.nsu.util.selection.SelectionGenerator;
import ru.nsu.util.tuple.Triplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrossValidationTest {

    private static final Integer NUM_SAMPLES = 100;

    private static final Integer NUM_SAMPLES_TEST = 20;

    private static final Integer NUM_ITERATIONS = 200;

    private static final double[] LEARNING_RATES = {
        0.000000000000000001, 0.00000000000000001, 0.0000000000000001, 0.000000000000001, 0.00000000000001,
        0.0000000000001, 0.000000000001, 0.00000000001, 0.0000000001, 0.000000001,
        0.00000001, 0.0000001, 0.000001, 0.00001, 0.0001, 0.001, 0.01, 0.1
    };

    private static final double[] REGULARIZATION_RATES = {
        0.000000000000000001, 0.00000000000000001, 0.0000000000000001, 0.000000000000001, 0.00000000000001,
        0.0000000000001, 0.000000000001, 0.00000000001, 0.0000000001, 0.000000001,
        0.00000001, 0.0000001, 0.000001, 0.00001, 0.0001, 0.001, 0.01, 0.1
    };

    private static final Integer[] POLYNOMIAL_DEGREES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    };

    private static final UniformDistributionEvaluationError uniformError =
        new UniformDistributionEvaluationError(0.0,1.0);

    private static final NormalDistributionEvaluationError normalError =
        new NormalDistributionEvaluationError(0.1, 1);

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
    public void crossValidationTest(ApproximatingFunction function) {
        PriorityQueue<Pair<Triplet<Integer, Double, Double>, Double>> prioritizedPairsOfDegreesAndLambdas =
            new PriorityQueue<>(Comparator.comparing(Pair::second));

        List<Pair<Double, Double>> samples = SelectionGenerator.generateSamples(function, NUM_SAMPLES);

        for (int i = 0; i < POLYNOMIAL_DEGREES.length; i++) {
            for (int j = 0; j < REGULARIZATION_RATES.length; ++j) {
                for (int l = 0; l < LEARNING_RATES.length; ++l) {
                    System.out.println(
                        "Running for degree = " + POLYNOMIAL_DEGREES[i] +
                            ", lambda = " + REGULARIZATION_RATES[j] +
                            ", learningRate = " + LEARNING_RATES[l]
                    );
                    List<Double> crossValidationMetrics = new ArrayList<>();

                    for (int k = 0; k < NUM_SAMPLES / NUM_SAMPLES_TEST; ++k) {
                        List<Pair<Double, Double>> trainSamples = samples.subList(
                            k * NUM_SAMPLES_TEST,
                            (k + 1) * NUM_SAMPLES_TEST - 1
                        );

                        List<Pair<Double, Double>> testSamples = new ArrayList<>(samples);
                        testSamples.removeAll(trainSamples);

                        RegressionExperiment experiment = getRegressionExperiment(j, i, LEARNING_RATES[l]);

                        experiment.launchTrain(trainSamples, NUM_ITERATIONS);
                        crossValidationMetrics.add(experiment.launchTest(testSamples));
                    }

                    prioritizedPairsOfDegreesAndLambdas.add(
                        new Pair<>(
                            new Triplet<>(
                                POLYNOMIAL_DEGREES[i],
                                REGULARIZATION_RATES[j],
                                LEARNING_RATES[l]
                            ),
                            crossValidationMetrics.stream().collect(Collectors.averagingDouble(it -> it))
                        )
                    );
                }
            }
        }

        Triplet<Integer, Double, Double> optimalParams = prioritizedPairsOfDegreesAndLambdas.poll().first();

        System.out.println("Cross-Validation completed");
        System.out.println("Polynomial Degree: " + optimalParams.first());
        System.out.println("Lambda: " + optimalParams.second());
        System.out.println("Learning Rate: " + optimalParams.third());
    }

    private static RegressionExperiment getRegressionExperiment(int j, int i, double learningRate) {
        Regularizer l2 = new RidgeRegularizer(REGULARIZATION_RATES[j]);
        LossFunction mseLoss = new MeanAbsoluteErrorLossFunction(l2);
        GradientStepFunction stepFunction = new MeanAbsoluteErrorGradientStepFunction(learningRate, l2);

        return new PolynomialRegressionExperiment(
            POLYNOMIAL_DEGREES[i],
            mseLoss,
            stepFunction
        );
    }

}
