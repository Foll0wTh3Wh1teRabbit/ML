package ru.nsu.perceptron.multilayer;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.util.activation.regression.RegressionActivationFunction;
import ru.nsu.util.loss.LossFunction;
import ru.nsu.util.selection.RegressionSample;

import java.util.List;

public class MultiLayerPerceptron {

    public MultiLayerPerceptron(List<Integer> layerSizes,
                                RegressionActivationFunction activationFunction,
                                LossFunction lossFunction,
                                double learningRate) {


    }

    public double predict(double x) {

    }

    public void train(List<RegressionSample> samples, int epochs) {

    }

    private RealVector generatePolynomialFeatures(double x, int degree) {
        double[] features = new double[degree];
        features[0] = 1.0;

        for (int i = 1; i < degree; i++) {
            features[i] = Math.pow(x, degree);
        }

        return new ArrayRealVector(features);
    }

}