package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.gradient.MeanAbsoluteErrorGradientStepFunction;
import ru.nsu.experiment.gradient.MeanSquaredErrorGradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.experiment.loss.MeanAbsoluteErrorLossFunction;
import ru.nsu.experiment.loss.MeanSquaredErrorLossFunction;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.function.error.NormalDistributionEvaluationError;
import ru.nsu.util.function.error.UniformDistributionEvaluationError;
import ru.nsu.util.tuple.Pair;
import ru.nsu.util.selection.SelectionGenerator;
import ru.nsu.util.tuple.Triplet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class CrossValidationTest {

    private static final Integer NUM_SAMPLES = 5;

    private static final Integer NUM_ITERATIONS = 4000000;

    private static final double[] LEARNING_RATES = {
        0.00000000001, 0.0000000001, 0.000000001,
        0.00000001, 0.0000001, 0.000001, 0.00001
    };

    private static final Integer[] POLYNOMIAL_DEGREES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    };

    private static final UniformDistributionEvaluationError uniformError =
        new UniformDistributionEvaluationError(0.0,0.1);

    private static final NormalDistributionEvaluationError normalError =
        new NormalDistributionEvaluationError(0.0, 0.1);

    private static Stream<Arguments> crossValidationTestParameters() {
        return Stream.of(
            Arguments.of("linearWithSinusUniform.txt", new LinearWithSinusApproximatingFunction(uniformError)),
            Arguments.of("linearWithSinusNormal.txt", new LinearWithSinusApproximatingFunction(normalError)),
            Arguments.of("cosinusUniform.txt", new CosinusApproximatingFunction(uniformError)),
            Arguments.of("cosinusNormal.txt", new CosinusApproximatingFunction(normalError)),
            Arguments.of("polynomialUniform.txt", new PolynomialApproximatingFunction(uniformError)),
            Arguments.of("polynomialNormal.txt", new PolynomialApproximatingFunction(normalError))
        );
    }

    @ParameterizedTest
    @MethodSource("crossValidationTestParameters")
    public void crossValidationTest(String filename, ApproximatingFunction function) throws IOException {
        File outputFile = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        PriorityQueue<Pair<Triplet<Integer, Double, Double>, Double>> prioritizedPairsOfDegreesAndLambdas =
            new PriorityQueue<>(Comparator.comparing(Pair::second));

        List<Pair<Double, Double>> samples = SelectionGenerator.generateSamples(function, NUM_SAMPLES);

        System.out.println(samples);

        for (int i = 0; i < Math.min(POLYNOMIAL_DEGREES[i], NUM_SAMPLES); i++) {
            for (double learningRate : LEARNING_RATES) {
                writer.write(
                    "Running for degree = " + POLYNOMIAL_DEGREES[i] +
                        ", learningRate = " + learningRate + '\n'
                );

                List<Double> crossValidationMetrics = new ArrayList<>();

                RegressionExperiment experiment = getRegressionExperiment(i, learningRate, writer);

                experiment.launchTrain(samples, NUM_ITERATIONS);
                crossValidationMetrics.add(experiment.launchTest(samples));

                prioritizedPairsOfDegreesAndLambdas.add(
                    new Pair<>(
                        new Triplet<>(
                            POLYNOMIAL_DEGREES[i],
                            null,
                            learningRate
                        ),
                        crossValidationMetrics.stream().reduce(Double::min).orElse(Double.MAX_VALUE)
                    )
                );
            }
        }

        Triplet<Integer, Double, Double> optimalParams = prioritizedPairsOfDegreesAndLambdas.poll().first();

        writer.flush();

        writer.write("Cross-Validation completed\n");
        writer.write("Polynomial Degree: " + optimalParams.first() + '\n');
        writer.write("Lambda: " + optimalParams.second() + '\n');
        writer.write("Learning Rate: " + optimalParams.third() + '\n');

        writer.flush();
    }

    private static RegressionExperiment getRegressionExperiment(int i, double learningRate, Writer writer) {
        LossFunction mseLoss = new MeanAbsoluteErrorLossFunction(null);
        GradientStepFunction stepFunction = new MeanAbsoluteErrorGradientStepFunction(learningRate, null);

        return new PolynomialRegressionExperiment(
            POLYNOMIAL_DEGREES[i],
            mseLoss,
            stepFunction,
            writer
        );
    }

}
