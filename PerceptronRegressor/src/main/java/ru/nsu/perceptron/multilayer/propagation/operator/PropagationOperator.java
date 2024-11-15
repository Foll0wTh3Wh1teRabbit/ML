package ru.nsu.perceptron.multilayer.propagation.operator;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;

import java.util.Random;

public class PropagationOperator {

    private RealMatrix weights;

    private RealVector bias;

    private final double learningRate;

    public PropagationOperator(int prevLayerSize, int nextLayerSize, double learningRate) {
        this.learningRate = learningRate;

        this.weights = new Array2DRowRealMatrix(nextLayerSize, prevLayerSize);
        this.bias = new ArrayRealVector(nextLayerSize);

        initializeWeightsAndBias();
    }

    public RealVector forwardPropagation(RealVector input) {
        return weights.operate(input).add(bias);
    }

    public RealVector errorBackPropagation(RealVector error, RealVector derivativeVector) {
        return weights.transpose()
            .operate(error)
            .ebeMultiply(derivativeVector);
    }

    public void updateWeights(RealMatrix weightsUpdate) {
        weights = weights.subtract(weightsUpdate.scalarMultiply(learningRate));
    }

    public void updateBias(RealVector biasUpdate) {
        bias = bias.subtract(biasUpdate.mapMultiply(learningRate));
    }

    private void initializeWeightsAndBias() {
        Random random = new Random();

        double stdDev = Math.sqrt(2.0 / weights.getColumnDimension());

        for (int i = 0; i < weights.getRowDimension(); i++) {
            for (int j = 0; j < weights.getColumnDimension(); j++) {
                weights.setEntry(i, j, random.nextGaussian() * stdDev);
            }

            bias.setEntry(i, 0.0);
        }
    }

}
