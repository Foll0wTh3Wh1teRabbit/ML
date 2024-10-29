package ru.nsu.util.activation.classification.multi;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static ru.nsu.util.activation.FunctionalOperators.SIGMOID;

public class OneVsAllActivationFunction extends MultiClassificationActivationFunction {

    @Override
    public Integer applyActivation(Collection<Double> args) {
        List<Double> probabilities = args.stream().map(SIGMOID).toList();

        return IntStream.range(0, probabilities.size())
            .boxed()
            .max(Comparator.comparing(probabilities::get))
            .orElse(-1);
    }

}
