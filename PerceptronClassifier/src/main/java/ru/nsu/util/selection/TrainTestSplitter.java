package ru.nsu.util.selection;

import ru.nsu.util.tuple.Pair;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public abstract class TrainTestSplitter<T extends Sample<?, ?>> {

    public Pair<List<T>, List<T>> trainTestSplit(int numSamples, int trainPercentage) throws IOException {
        int numTrainSamples = numSamples * trainPercentage / 100;
        int numTestSamples = numSamples - numTrainSamples;

        List<T> trainSamples = fillSamples(numTrainSamples);
        List<T> testSamples = fillSamples(numTestSamples);

        Collections.shuffle(trainSamples);
        Collections.shuffle(testSamples);

        return new Pair<>(trainSamples, testSamples);
    }

    protected abstract List<T> fillSamples(int numSamples) throws IOException;

}
