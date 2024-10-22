package ru.nsu.experiment;

import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.util.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class PolynomialRegressionExperiment extends RegressionExperiment {

    private final double[] coefficients;

    public PolynomialRegressionExperiment(
        Integer polynomialDegree,
        LossFunction lossFunction,
        GradientStepFunction stepFunction
    ) {
        super(lossFunction, stepFunction);

        this.coefficients = new double[polynomialDegree + 1];
        Arrays.fill(coefficients, 0.0);
    }

    @Override
    public void launchTrain(List<Pair<Double, Double>> samples, int numIterations) {
        for (int i = 0; i < numIterations; i++) {
            double loss = lossFunction.calculateLoss(coefficients, samples);

            Double[] antiGradientTerms = stepFunction.getAntiGradientTerms(samples, coefficients, loss);
            for (int j = 0; j < coefficients.length; j++) {
                coefficients[j] -= antiGradientTerms[j];
            }
        }

        for (double coefficient : coefficients) {
            System.out.print(coefficient + " ");
        }
        System.out.println();
    }

    @Override
    public double launchTest(List<Pair<Double, Double>> samples) {
        double loss = lossFunction.calculateLoss(coefficients, samples);

        System.out.println("Test completed, Loss: " + loss);
        System.out.println();

        return loss;
    }

}
