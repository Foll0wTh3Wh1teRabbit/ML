package ru.nsu.util.activation.classification.multi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SoftmaxActivationFunction extends MultiClassificationActivationFunction {

    @Override
    public Integer applyActivation(Collection<Double> args) {
        double max = args.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
        List<Double> exponents = args.stream().map(x -> Math.exp(x - max)).toList();

        double sum = exponents.stream().mapToDouble(Double::doubleValue).sum();
        List<Double> probabilities = exponents.stream()
            .map(exp -> exp / sum)
            .toList();

        return IntStream.range(0, probabilities.size())
            .boxed()
            .max(Comparator.comparing(probabilities::get))
            .orElse(-1);
    }

}
