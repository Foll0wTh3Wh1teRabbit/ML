package ru.nsu.experiment;

import ru.nsu.experiment.gradient.GradientStepFunction;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.util.tuple.Pair;

import java.util.List;

public abstract class RegressionExperiment {

    protected final LossFunction lossFunction;

    protected final GradientStepFunction stepFunction;

    protected RegressionExperiment(LossFunction lossFunction, GradientStepFunction stepFunction) {
        this.lossFunction = lossFunction;
        this.stepFunction = stepFunction;
    }

    public abstract void launchTrain(List<Pair<Double, Double>> samples, int numIterations);

    public abstract double launchTest(List<Pair<Double, Double>> samples);

}
