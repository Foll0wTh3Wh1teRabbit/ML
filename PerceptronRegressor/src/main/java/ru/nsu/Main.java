package ru.nsu;

import org.apache.commons.math.FunctionEvaluationException;
import ru.nsu.perceptron.multilayer.MultiLayerPerceptron;
import ru.nsu.util.activation.regression.LeakyReLUActivationFunction;
import ru.nsu.util.loss.MeanSquaredErrorLossFunction;
import ru.nsu.util.selection.RegressionSample;
import ru.nsu.util.selection.RegressionSelectionGenerator;
import ru.nsu.util.selection.function.LinearWithSinusApproximatingFunction;
import ru.nsu.util.selection.stochastic.NormalDistributionStochasticValue;

import java.util.List;

public class Main {

    public static void main(String[] args) throws FunctionEvaluationException {
        MultiLayerPerceptron perceptron = new MultiLayerPerceptron(
            List.of(5, 16, 32, 16, 1),
            List.of(
                new LeakyReLUActivationFunction(),
                new LeakyReLUActivationFunction(),
                new LeakyReLUActivationFunction()
            ),
            new MeanSquaredErrorLossFunction(),
            0.0001
        );

        List<RegressionSample> trainSamples = RegressionSelectionGenerator.getSamples(
            new LinearWithSinusApproximatingFunction(),
            new NormalDistributionStochasticValue(0.0, 0.0),
            1000
        );

        List<RegressionSample> testSamples = RegressionSelectionGenerator.getSamples(
            new LinearWithSinusApproximatingFunction(),
            new NormalDistributionStochasticValue(0.0, 0.0),
            200
        );

        perceptron.train(trainSamples, 20000);
        perceptron.test(testSamples);
    }

}
