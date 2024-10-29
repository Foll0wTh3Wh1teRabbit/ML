package ru.nsu.perceptron.elementary.regression;

import ru.nsu.perceptron.ElementaryLearningPerceptron;
import ru.nsu.perceptron.ElementaryForwardPropagatingPerceptron;
import ru.nsu.util.activation.regression.RegressionActivationFunction;
import ru.nsu.util.selection.regression.RegressionSample;

import java.util.List;

public class ElementaryRegressor
    implements
        ElementaryLearningPerceptron<Double, Double, RegressionSample>,
        ElementaryForwardPropagatingPerceptron<List<Double>, Double> {

    private final RegressionActivationFunction activationFunction;

    public ElementaryRegressor(RegressionActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
    public void train(List<RegressionSample> trainSamples) {

    }

    @Override
    public void test(List<RegressionSample> testSample) {

    }

    @Override
    public Double predict(Double aDouble) {
        return 0.0;
    }

    @Override
    public Double forwardPropagation(List<Double> doubles) {
        return 0.0;
    }

}
