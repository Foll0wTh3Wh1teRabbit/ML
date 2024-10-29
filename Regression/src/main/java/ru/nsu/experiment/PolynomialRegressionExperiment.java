package ru.nsu.experiment;

import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.util.tuple.Pair;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PolynomialRegressionExperiment extends RegressionExperiment {

    private final double[] coefficients;

    private final Writer writer;

    public PolynomialRegressionExperiment(
        Integer polynomialDegree,
        LossFunction lossFunction,
        GradientStepFunction stepFunction,
        Writer writer
    ) {
        super(lossFunction, stepFunction);
        this.writer = writer;

        this.coefficients = new double[polynomialDegree + 1];
        Arrays.fill(coefficients, 0.0);
    }

    @Override
    public void launchTrain(List<Pair<Double, Double>> samples, int numIterations) throws IOException {
        for (int i = 0; i < numIterations; i++) {
            double loss = lossFunction.calculateLoss(coefficients, samples);

            Double[] antiGradientTerms = stepFunction.getAntiGradientTerms(samples, coefficients, loss);
            for (int j = 0; j < coefficients.length; j++) {
                coefficients[j] -= antiGradientTerms[j];
            }
        }

        for (double coefficient : coefficients) {
            writer.write(coefficient + " ");
        }
    }

    @Override
    public double launchTest(List<Pair<Double, Double>> samples) throws IOException {
        double loss = lossFunction.calculateLoss(coefficients, samples);

        writer.write("Test completed, Loss: " + loss + '\n');

        return loss;
    }

}
