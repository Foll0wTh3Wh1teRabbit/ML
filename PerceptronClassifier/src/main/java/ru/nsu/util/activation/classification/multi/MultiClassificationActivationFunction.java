package ru.nsu.util.activation.classification.multi;

import ru.nsu.util.activation.ActivationFunction;

public abstract class MultiClassificationActivationFunction extends ActivationFunction<Double, Integer> {

    protected MultiClassificationActivationFunction() {
        super(null);
    }

}
