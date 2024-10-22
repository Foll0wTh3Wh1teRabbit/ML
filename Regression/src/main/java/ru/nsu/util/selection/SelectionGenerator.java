package ru.nsu.util.selection;

import lombok.experimental.UtilityClass;
import ru.nsu.util.tuple.Pair;
import ru.nsu.util.function.ApproximatingFunction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.random;

@UtilityClass
public class SelectionGenerator {

    private static final Double lowerBound = 0.0;

    private static final Double upperBound = 2 * PI;

    public List<Pair<Double, Double>> generateSamples(ApproximatingFunction function, Integer numSamples) {
        List<Pair<Double, Double>> samples = new ArrayList<>();

        for (int i = 0; i < numSamples; i++) {
            Double argumentValue = lowerBound + (upperBound - lowerBound) * random();
            Double functionValue = function.apply(argumentValue);

            samples.add(
                new Pair<>(argumentValue, functionValue)
            );
        }

        return samples;
    }

}
