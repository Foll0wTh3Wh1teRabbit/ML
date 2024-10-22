package ru.nsu.experiment.gradient;

import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.tuple.Pair;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.pow;

public abstract class GradientStepFunction {

    protected final Double learningRate;

    protected final Regularizer regularizer;

    protected GradientStepFunction(Double learningRate, Regularizer regularizer) {
        this.learningRate = learningRate;
        this.regularizer = regularizer;
    }

    public Double[] getAntiGradientTerms(List<Pair<Double, Double>> samples, double[] coefficients, double totalLoss) {
        Double[] antiGradientTerms = new Double[coefficients.length];

        for (int i = 0; i < coefficients.length; i++) {
            double sum = 0.0;

            for (Pair<Double, Double> sample : samples) {
                double argument = sample.first();

                double predicted = 0.0;
                for (int j = 0; j < coefficients.length; j++) {
                    predicted += (coefficients[j] * pow(sample.first(), j));
                }

                double actual = sample.second();

                sum += getDerivativePartOfAntiGradient(argument, actual, predicted, i);
            }

            final int coefficientIndex = i;
            double pureAntiGradientPart = sum * getCoefficientPartOfAntiGradient(samples.size(), totalLoss);
            double regularizationPart = Optional.ofNullable(regularizer)
                .map(it -> it.evaluateGradientRegularizationTerm(coefficients[coefficientIndex]))
                .orElse(0.0);

            antiGradientTerms[i] = learningRate * (pureAntiGradientPart + regularizationPart);
        }

        return antiGradientTerms;
    }

    protected abstract double getDerivativePartOfAntiGradient(
        double argument,
        double actual,
        double predicted,
        int degree
    );

    protected abstract double getCoefficientPartOfAntiGradient(int samplesNumber, double totalLoss);

}
