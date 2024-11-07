package ru.nsu.util.selection;

import ru.nsu.util.selection.error.EvaluationError;
import ru.nsu.util.selection.function.ApproximatingFunction;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.*;
import static java.lang.Math.random;

public class RegressionSelectionGenerator {

    public static List<RegressionSample> getSamples(
        ApproximatingFunction function,
        EvaluationError error,
        int samplesNum
    ) {
        return IntStream.range(0, samplesNum).boxed()
            .mapToDouble(it -> 2 * PI * random()).boxed()
            .map(
                arg -> new RegressionSample(
                    arg,
                    function.apply(arg, error)
                )
            )
            .toList();
    }

}
