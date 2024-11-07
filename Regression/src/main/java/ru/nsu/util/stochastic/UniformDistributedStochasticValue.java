package ru.nsu.util.stochastic;

public class UniformDistributedStochasticValue implements StochasticValue {

    private final double lowerBound;

    private final double upperBound;

    public UniformDistributedStochasticValue(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Double get() {
        return lowerBound + (upperBound - lowerBound) * Math.random();
    }

}
