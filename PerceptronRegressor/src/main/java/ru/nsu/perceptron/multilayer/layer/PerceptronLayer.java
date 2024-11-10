package ru.nsu.perceptron.multilayer.layer;

import lombok.Getter;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.util.activation.regression.RegressionActivationFunction;

import java.util.Random;

public class PerceptronLayer {



    public PerceptronLayer(int inputSize, int outputSize, RegressionActivationFunction activationFunction) {

    }

    private void initializeWeightsAndBiases() {

    }

    public RealVector forward(RealVector input) {

    }

    public RealVector calculateGradients(RealVector input, RealVector delta) {

    }

    public void updateWeights(RealVector input, RealVector delta, double learningRate) {

    }

}
