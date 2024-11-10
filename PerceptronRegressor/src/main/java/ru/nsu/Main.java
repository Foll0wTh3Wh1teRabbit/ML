package ru.nsu;

import ru.nsu.perceptron.multilayer.MultiLayerPerceptron;
import ru.nsu.util.activation.regression.LeakyReLUActivationFunction;
import ru.nsu.util.loss.MeanAbsoluteErrorLossFunction;
import ru.nsu.util.selection.RegressionSample;
import ru.nsu.util.selection.RegressionSelectionGenerator;
import ru.nsu.util.selection.function.CosinusApproximatingFunction;
import ru.nsu.util.selection.stochastic.NormalDistributionStochasticValue;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<RegressionSample> samples = RegressionSelectionGenerator.getSamples(
            new CosinusApproximatingFunction(),
            new NormalDistributionStochasticValue(0.0, 0.0),
            5000
        );

        MultiLayerPerceptron perceptron =
            new MultiLayerPerceptron(
                List.of(8, 4, 4, 1),
                new LeakyReLUActivationFunction(),
                new MeanAbsoluteErrorLossFunction(),
                1.0e-4
            );

        perceptron.train(samples, 3000);

        List<RegressionSample> testSamples = RegressionSelectionGenerator.getSamples(
            new CosinusApproximatingFunction(),
            new NormalDistributionStochasticValue(0.0, 0.0),
            30
        );

        for (RegressionSample sample : testSamples) {
            System.out.println("Actual: (" + sample.feature() + ", " + sample.label() + ")");
        }

        for (RegressionSample sample : testSamples) {
            double predicted = perceptron.predict(sample.feature());

            System.out.println("Predicted: (" + sample.feature() + ", " + predicted + ")");
        }
    }

}
