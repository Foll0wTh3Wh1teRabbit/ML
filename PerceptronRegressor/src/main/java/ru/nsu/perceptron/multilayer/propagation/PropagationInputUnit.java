package ru.nsu.perceptron.multilayer.propagation;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationInputLayer;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationLayer;
import ru.nsu.perceptron.multilayer.propagation.operator.PropagationOperator;

public class PropagationInputUnit extends PropagationUnit {

    public PropagationInputUnit(PropagationInputLayer prevLayer, PropagationLayer nextLayer, PropagationOperator operator) {
        super(prevLayer, nextLayer, operator);
    }

    @Override
    public RealVector forwardPropagation(RealVector inputs) throws FunctionEvaluationException {
        return nextLayer.processInput(
            operator.forwardPropagation(
                prevLayer.processInput(inputs)
            )
        );
    }

}
