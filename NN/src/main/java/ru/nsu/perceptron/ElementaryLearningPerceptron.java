package ru.nsu.perceptron;

import ru.nsu.util.selection.Sample;

import java.util.List;

public interface ElementaryLearningPerceptron<Input, Output, T extends Sample<? extends Input, ? extends Output>>
    extends ElementaryPerceptron<Input, Output, T> {

    void train(List<T> trainSamples);

    void test(List<T> testSample);

}
