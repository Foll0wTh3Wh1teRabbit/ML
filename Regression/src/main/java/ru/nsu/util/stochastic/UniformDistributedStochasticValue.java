package ru.nsu.util.stochastic;

public record UniformDistributedStochasticValue(Double lowerBound, Double upperBound) implements StochasticValue {

    @Override
    public Double get() {
        return lowerBound + (upperBound - lowerBound) * Math.random();
    }

}
