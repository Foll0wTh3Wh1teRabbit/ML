package ru.nsu.perceptron.multilayer.propagation.layer;

import ru.nsu.util.activation.regression.LinearActivationFunction;

public class PropagationInputLayer extends PropagationLayer {

    public PropagationInputLayer(int size) {
        super(size, new LinearActivationFunction());
    }

}
