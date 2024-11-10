package ru.nsu.util.selection.stochastic;

public record UniformDistributionStochasticValue(Double lowerBound, Double upperBound) implements StochasticValue {

    @Override
    public Double get() {
        return lowerBound + (upperBound - lowerBound) * Math.random();
    }

}
