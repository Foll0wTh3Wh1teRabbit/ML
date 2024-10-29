package ru.nsu.perceptron;

import ru.nsu.util.selection.Sample;

public interface ElementaryPerceptron<Input, Output, T extends Sample<? extends Input, ? extends Output>> {

    Output predict(Input input);

}
