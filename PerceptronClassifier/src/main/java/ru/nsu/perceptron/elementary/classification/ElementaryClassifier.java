package ru.nsu.perceptron.elementary.classification;

import ru.nsu.perceptron.ElementaryPerceptron;
import ru.nsu.util.selection.Sample;

import java.util.List;

public abstract class ElementaryClassifier<Input, Output, T extends Sample<? extends Input, ? extends Output>>
    implements
        ElementaryPerceptron<Input, Output, T> {

    protected static final Integer NUM_ITERATIONS = 100;

    protected static final Double LEARNING_RATE = 0.000001;

    protected final List<Double> weights;

    protected ElementaryClassifier(List<Double> weights) {
        this.weights = weights;
    }

}
