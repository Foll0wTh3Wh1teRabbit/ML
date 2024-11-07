package ru.nsu.util.selection;

import ru.nsu.util.tuple.Pair;

import java.util.List;

public class RegressionTrainTestSplitter {

    public static Pair<List<RegressionSample>, List<RegressionSample>> trainTestSplit(
        List<RegressionSample> samples,
        int trainPercentage
    ) {
        int separationIndex = samples.size() * trainPercentage / 100;

        List<RegressionSample> trainSamples = samples.subList(0, separationIndex);
        List<RegressionSample> testSamples = samples.subList(separationIndex, samples.size());

        return new Pair<>(trainSamples, testSamples);
    }

}
