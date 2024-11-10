package ru.nsu.util.selection.stochastic;

import java.util.Random;

public record NormalDistributionStochasticValue(Double bias, Double variance) implements StochasticValue {

    @Override
    public Double get() {
        Random random = new Random();
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();

        double standardNormalDistributedValue = Math.sqrt(-2.0 * Math.log(u2)) * Math.cos(2.0 * Math.PI * u1);

        return bias + variance * standardNormalDistributedValue;
    }

}
