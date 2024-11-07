package ru.nsu.util.selection;

import lombok.experimental.UtilityClass;
import ru.nsu.util.stochastic.UniformDistributedStochasticValue;
import ru.nsu.util.function.ApproximatingFunction;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.PI;

@UtilityClass
public class SelectionGenerator {

    private static final Double lowerBound = 0.0;

    private static final Double upperBound = 2.0 * PI;

    public List<Sample> generateSamples(ApproximatingFunction function, Integer numSamples) {
        return IntStream.range(0, numSamples).boxed()
            .map(it -> new UniformDistributedStochasticValue(lowerBound, upperBound))
            .map(UniformDistributedStochasticValue::get)
            .map(it -> new Sample(it, function.apply(it)))
            .collect(Collectors.toList());
    }

}
