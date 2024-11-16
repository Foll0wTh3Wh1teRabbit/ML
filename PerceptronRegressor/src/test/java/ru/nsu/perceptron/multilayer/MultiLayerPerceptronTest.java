package ru.nsu.perceptron.multilayer;

import org.apache.commons.math.FunctionEvaluationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.util.activation.regression.HyperbolicTangentActivationFunction;
import ru.nsu.util.activation.regression.LeakyReLUActivationFunction;
import ru.nsu.util.activation.regression.RegressionActivationFunction;
import ru.nsu.util.loss.MeanAbsoluteErrorLossFunction;
import ru.nsu.util.selection.RegressionSample;
import ru.nsu.util.selection.RegressionSelectionGenerator;
import ru.nsu.util.selection.function.ApproximatingFunction;
import ru.nsu.util.selection.function.CosinusApproximatingFunction;
import ru.nsu.util.selection.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.selection.function.PolynomialApproximatingFunction;
import ru.nsu.util.selection.stochastic.NormalDistributionStochasticValue;
import ru.nsu.util.selection.stochastic.StochasticValue;

import java.util.List;
import java.util.stream.Stream;

public class MultiLayerPerceptronTest {

    private static final StochasticValue normalDistributedValue = new NormalDistributionStochasticValue(0.0, 0.03);

    private static Stream<Arguments> multiLayerPerceptronTestParameters() {
        return Stream.of(
            Arguments.of(List.of(3, 1, 1), List.of(new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1), List.of(new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1), List.of(new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),

            Arguments.of(List.of(3, 1, 1, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 1, 2, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 1, 3, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 2, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 3, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 2, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 3, 1), List.of(new HyperbolicTangentActivationFunction(), new HyperbolicTangentActivationFunction()), new CosinusApproximatingFunction()),


            Arguments.of(List.of(3, 1, 1), List.of(new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1), List.of(new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1), List.of(new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),

            Arguments.of(List.of(3, 1, 1, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 1, 2, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 1, 3, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 2, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 2, 3, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 2, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),
            Arguments.of(List.of(3, 3, 3, 1), List.of(new LeakyReLUActivationFunction(), new HyperbolicTangentActivationFunction()), new LinearWithSinusApproximatingFunction()),



            Arguments.of(List.of(3, 1, 1), List.of(new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1), List.of(new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1), List.of(new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),

            Arguments.of(List.of(3, 1, 1, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 1, 2, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 1, 3, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 2, 1, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 2, 2, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 2, 3, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 3, 1, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 3, 2, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction()),
            Arguments.of(List.of(3, 3, 3, 1), List.of(new LeakyReLUActivationFunction(), new LeakyReLUActivationFunction()), new PolynomialApproximatingFunction())
        );
    }

    @ParameterizedTest
    @MethodSource("multiLayerPerceptronTestParameters")
    public void multiLayerPerceptronTest(
        List<Integer> layerSizes,
        List<RegressionActivationFunction> activationFunctions,
        ApproximatingFunction function
    ) throws FunctionEvaluationException {
        MultiLayerPerceptron perceptron = new MultiLayerPerceptron(layerSizes, activationFunctions, new MeanAbsoluteErrorLossFunction(), 0.00001);

        List<RegressionSample> trainSamples = RegressionSelectionGenerator.getSamples(function, normalDistributedValue, 1000);
        List<RegressionSample> testSamples = RegressionSelectionGenerator.getSamples(function, normalDistributedValue, 100);

        perceptron.train(trainSamples, 100000);
        perceptron.test(testSamples);
    }

}
