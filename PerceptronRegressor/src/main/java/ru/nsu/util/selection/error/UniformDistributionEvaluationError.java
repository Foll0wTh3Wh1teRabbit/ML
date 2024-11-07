package ru.nsu.util.selection.error;

public class UniformDistributionEvaluationError implements EvaluationError {

    private final double lowerBound;

    private final double upperBound;

    public UniformDistributionEvaluationError(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Double get() {
        return lowerBound + (upperBound - lowerBound) * Math.random();
    }

}
