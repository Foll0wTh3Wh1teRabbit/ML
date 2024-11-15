package ru.nsu.perceptron.multilayer.propagation.layer;

import ru.nsu.util.activation.regression.LinearActivationFunction;

public class PropagationOutputLayer extends PropagationLayer {

    public PropagationOutputLayer(int size) {
        super(size, new LinearActivationFunction());
    }

}