package ru.nsu.perceptron.multilayer.propagation.layer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.util.activation.regression.RegressionActivationFunction;

@Data
@RequiredArgsConstructor
public class PropagationLayer {

    private RealVector inputs;

    private RealVector activations;

    private RealVector errors;

    private final int size;

    private final RegressionActivationFunction activationFunction;

    public RealVector processInput(RealVector input) throws FunctionEvaluationException {
        inputs = input;
        activations = input.map(activationFunction);

        return activations;
    }

    public RealVector getInputDerivativeVector() throws FunctionEvaluationException {
        return inputs.map(activationFunction::getDerivative);
    }

}
