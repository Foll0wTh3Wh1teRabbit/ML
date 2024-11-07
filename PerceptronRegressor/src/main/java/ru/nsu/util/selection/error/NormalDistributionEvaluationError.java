package ru.nsu.util.selection.error;

import java.util.Random;

public class NormalDistributionEvaluationError implements EvaluationError {

    private final double bias;

    private final double variance;

    public NormalDistributionEvaluationError(double bias, double variance) {
        this.bias = bias;
        this.variance = variance;
    }

    @Override
    public Double get() {
        Random random = new Random();
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();

        double normalDistributedValue = Math.sqrt(-2.0 * Math.log(u2)) * Math.cos(2.0 * Math.PI * u1);

        return bias + variance * normalDistributedValue;
    }

}
