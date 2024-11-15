package ru.nsu.perceptron.multilayer.propagation;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationLayer;
import ru.nsu.perceptron.multilayer.propagation.operator.PropagationOperator;

public class PropagationOutputUnit extends PropagationUnit {

    public PropagationOutputUnit(PropagationLayer prevLayer, PropagationLayer nextLayer, PropagationOperator operator) {
        super(prevLayer, nextLayer, operator);
    }

    @Override
    public void backPropagation(RealVector errors) throws FunctionEvaluationException {
        nextLayer.setErrors(errors);

        super.backPropagation(errors);
    }

}
