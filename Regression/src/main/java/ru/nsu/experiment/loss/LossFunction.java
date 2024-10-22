package ru.nsu.experiment.loss;

import lombok.Getter;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.tuple.Pair;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.pow;

@Getter
public abstract class LossFunction {

    protected final Regularizer regularizer;

    protected LossFunction(Regularizer regularizer) {
        this.regularizer = regularizer;
    }

    public double calculateLoss(double[] coefficients, List<Pair<Double, Double>> samples) {
        double totalLoss = 0.0;

        for (Pair<Double, Double> sample : samples) {
            double predictedValue = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                predictedValue += (coefficients[i] * pow(sample.first(), i));
            }

            double actualValue = sample.second();

            totalLoss += getDistance(predictedValue, actualValue);
        }

        totalLoss += Optional.ofNullable(regularizer)
            .map(it -> it.evaluateLossRegularizationTerm(coefficients))
            .orElse(0.0);

        return totalLoss / samples.size();
    }

    protected abstract double getDistance(double predicted, double actual);

}
