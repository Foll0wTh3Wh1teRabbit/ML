package ru.nsu.util.selection.regression;

import ru.nsu.util.selection.TrainTestSplitter;
import ru.nsu.util.selection.regression.function.ApproximatingFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.PI;

public class RegressionTrainTestSplitter extends TrainTestSplitter<RegressionSample> {

    private final ApproximatingFunction approximatingFunction;

    public RegressionTrainTestSplitter(ApproximatingFunction approximatingFunction) {
        this.approximatingFunction = approximatingFunction;
    }

    @Override
    protected List<RegressionSample> fillSamples(int numSamples) {
        Random random = new Random();

        List<RegressionSample> samples = new ArrayList<>();
        for (int i = 0; i < numSamples; i++) {
            double argument = 2 * PI * random.nextDouble();
            double value = approximatingFunction.apply(argument);

            RegressionSample sample = new RegressionSample(argument, value);
            samples.add(sample);
        }

        return samples;
    }

}
