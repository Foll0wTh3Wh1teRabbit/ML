package ru.nsu.perceptron.multilayer.propagation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationLayer;
import ru.nsu.perceptron.multilayer.propagation.operator.PropagationOperator;

@Data
@RequiredArgsConstructor
public class PropagationUnit {

    protected final PropagationLayer prevLayer;

    protected final PropagationLayer nextLayer;

    protected final PropagationOperator operator;

    public RealVector forwardPropagation(RealVector inputs) throws FunctionEvaluationException {
        return nextLayer.processInput(
            operator.forwardPropagation(
                prevLayer.getActivations()
            )
        );
    }

    public void backPropagation(RealVector errors) throws FunctionEvaluationException {
        // 1. Error back-propagation

        RealVector prevLayerInputDerivativeVector = prevLayer.getInputDerivativeVector();
        RealVector nextLayerErrors = nextLayer.getErrors();

        RealVector prevLayerError = operator.errorBackPropagation(nextLayerErrors, prevLayerInputDerivativeVector);

        // 2. Calculating gradient of loss by weights

        RealVector nextLayerError = nextLayer.getErrors();
        RealVector prevLayerActivations = prevLayer.getActivations();

        RealMatrix weightsGradient = outerProduct(nextLayerError, prevLayerActivations);

        // 3. Updating weights and bias

        operator.updateWeights(weightsGradient);
        operator.updateBias(nextLayerError);

        // 4. Set previous layer error

        prevLayer.setErrors(prevLayerError);
    }

    private RealMatrix outerProduct(RealVector first, RealVector second) {
        RealMatrix weightsUpdate = MatrixUtils.createRealMatrix(first.getDimension(), second.getDimension());

        for (int i = 0; i < first.getDimension(); i++) {
            for (int j = 0; j < second.getDimension(); j++) {
                weightsUpdate.setEntry(i, j, first.getEntry(i) * second.getEntry(j));
            }
        }

        return weightsUpdate;
    }

}
