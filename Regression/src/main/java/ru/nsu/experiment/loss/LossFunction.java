package ru.nsu.experiment.loss;

import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.selection.Sample;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.pow;

public abstract class LossFunction {

    protected final Regularizer regularizer;

    protected LossFunction(Regularizer regularizer) {
        this.regularizer = regularizer;
    }

    public double calculateLoss(double[] regressionModel, List<Sample> samples) {
        double totalLoss = 0.0;

        for (Sample sample : samples) {
            double predicted = 0.0;

            for (int i = 0; i < regressionModel.length; i++) {
                predicted += (regressionModel[i] * pow(sample.x(), i));
            }

            double actual = sample.y();

            totalLoss += getDistance(predicted, actual);
        }

        double regularizationTerm = Optional.ofNullable(regularizer)
            .map(reg -> reg.evaluateLossRegularizationTerm(regressionModel))
            .orElse(0.0);

        totalLoss += regularizationTerm;

        return totalLoss;
    }

    protected abstract double getDistance(double predicted, double actual);

}
