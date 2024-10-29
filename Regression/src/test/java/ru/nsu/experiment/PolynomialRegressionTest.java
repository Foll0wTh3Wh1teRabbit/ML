package ru.nsu.experiment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.gradient.MeanAbsoluteErrorGradientStepFunction;
import ru.nsu.experiment.gradient.MeanSquaredErrorGradientStepFunction;
import ru.nsu.experiment.gradient.RootMeanSquaredErrorGradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.experiment.loss.MeanAbsoluteErrorLossFunction;
import ru.nsu.experiment.loss.MeanSquaredErrorLossFunction;
import ru.nsu.experiment.loss.RootMeanSquaredErrorLossFunction;
import ru.nsu.experiment.regularizer.LassoRegularizer;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.experiment.regularizer.RidgeRegularizer;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.function.PolynomialApproximatingFunction;
import ru.nsu.util.function.error.EvaluationError;
import ru.nsu.util.function.error.NormalDistributionEvaluationError;
import ru.nsu.util.function.error.UniformDistributionEvaluationError;
import ru.nsu.util.tuple.Pair;
import ru.nsu.util.selection.SelectionGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PolynomialRegressionTest {

    private static final Integer NUM_ITERATIONS = 400000;

    private static final Integer NUM_SAMPLES = 150;

    private static final Integer POLYNOMIAL_DEGREE = 4;

    private static final Double LEARNING_RATE = 0.00001;

    private static final Double REGULARIZATION_RATE = 1.0;

    private static final EvaluationError uniformEvaluationError =
        new UniformDistributionEvaluationError(0.0, 0.0);

    private static final EvaluationError normalEvaluationError =
        new NormalDistributionEvaluationError(0.0, 0.00);

    private static final Regularizer l1Regularizer = new LassoRegularizer(REGULARIZATION_RATE);

    private static final Regularizer l2Regularizer = new RidgeRegularizer(REGULARIZATION_RATE);


    private static final List<Arguments> cosinusApproximatingWithMAETests = List.of(
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static final List<Arguments> cosinusApproximatingWithMSETests = List.of(
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );

    private static final List<Arguments> cosinusApproximatingWithRMSETests = List.of(
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new CosinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static final List<Arguments> linearWithSinusApproximatingWithMAETests = List.of(
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static final List<Arguments> linearWithSinusApproximatingWithMSETests = List.of(
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );

    private static final List<Arguments> linearWithSinusApproximatingWithRMSETests = List.of(
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new LinearWithSinusApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static final List<Arguments> polynomialApproximatingWithMAETests = List.of(
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(null),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l1Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanAbsoluteErrorLossFunction(l2Regularizer),
            new MeanAbsoluteErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static final List<Arguments> polynomialApproximatingWithMSETests = List.of(
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(null),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l1Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new MeanSquaredErrorLossFunction(l2Regularizer),
            new MeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );

    private static final List<Arguments> polynomialApproximatingWithRMSETests = List.of(
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(uniformEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        ),

        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(null),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, null)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l1Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l1Regularizer)
        ),
        Arguments.of(
            new PolynomialApproximatingFunction(normalEvaluationError),
            new RootMeanSquaredErrorLossFunction(l2Regularizer),
            new RootMeanSquaredErrorGradientStepFunction(LEARNING_RATE, l2Regularizer)
        )
    );


    private static Stream<Arguments> polynomialRegressionTestParameters() {
        return Stream.of(
            cosinusApproximatingWithMAETests,
            cosinusApproximatingWithMSETests,
            cosinusApproximatingWithRMSETests,
            linearWithSinusApproximatingWithMAETests,
            linearWithSinusApproximatingWithMSETests,
            linearWithSinusApproximatingWithRMSETests,
            polynomialApproximatingWithMAETests,
            polynomialApproximatingWithMSETests,
            polynomialApproximatingWithRMSETests
        ).flatMap(
            List::stream
        );
    }

    @ParameterizedTest
    @MethodSource("polynomialRegressionTestParameters")
    public void polynomialRegressionTest(
        ApproximatingFunction function,
        LossFunction lossFunction,
        GradientStepFunction stepFunction
    ) throws IOException {
        File file = new File("polynomialRegressionTest.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        System.out.println("Polynomial Degree: " + POLYNOMIAL_DEGREE);
        System.out.println("Learning Rate: " + LEARNING_RATE);
        System.out.println("Loss Function: " + lossFunction.getClass().getSimpleName());
        System.out.println(
            "Regularization Type: " + Optional.ofNullable(lossFunction.getRegularizer())
                .map(it -> it.getClass().getSimpleName())
                .orElse(null)
        );
        System.out.println("Regularization Rate: " + REGULARIZATION_RATE);
        System.out.println("Coefficients:");

        RegressionExperiment experiment = new PolynomialRegressionExperiment(POLYNOMIAL_DEGREE, lossFunction, stepFunction, writer);
        List<Pair<Double, Double>> samples = SelectionGenerator.generateSamples(function, NUM_SAMPLES);

        experiment.launchTrain(samples.subList(0, NUM_SAMPLES - 10), NUM_ITERATIONS);
        experiment.launchTest(samples.subList(NUM_SAMPLES - 10, NUM_SAMPLES));
    }

}
