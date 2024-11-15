package ru.nsu.perceptron.multilayer;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.perceptron.multilayer.propagation.PropagationInputUnit;
import ru.nsu.perceptron.multilayer.propagation.PropagationOutputUnit;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationInputLayer;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationLayer;
import ru.nsu.perceptron.multilayer.propagation.layer.PropagationOutputLayer;
import ru.nsu.perceptron.multilayer.propagation.operator.PropagationOperator;
import ru.nsu.perceptron.multilayer.propagation.PropagationUnit;
import ru.nsu.util.activation.regression.RegressionActivationFunction;
import ru.nsu.util.loss.LossFunction;
import ru.nsu.util.selection.RegressionSample;
import ru.nsu.util.tuple.Triplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class MultiLayerPerceptron {

    private final List<PropagationUnit> propagationUnits;

    private final LossFunction lossFunction;

    private final int inputSize;

    private final double learningRate;

    public MultiLayerPerceptron(
        List<Integer> layerSizes,
        List<RegressionActivationFunction> activationFunctions,
        LossFunction lossFunction,
        double learningRate
    ) {
        int inputSize = layerSizes.get(0);

        this.lossFunction = lossFunction;
        this.inputSize = inputSize;
        this.learningRate = learningRate;

        this.propagationUnits = initNetworkArchitecture(
            layerSizes,
            activationFunctions
        );
    }

    public void train(List<RegressionSample> samples, int epochs) throws FunctionEvaluationException {
        for (int i = 0; i < epochs; i++) {
            double epochLoss = 0.0;

            for (RegressionSample sample : samples) {
                RealVector propagatedInput = generateFeatures(sample.feature());

                for (PropagationUnit unit : propagationUnits) {
                    propagatedInput = unit.forwardPropagation(propagatedInput);
                }

                double predicted = propagatedInput.getEntry(0);
                double actual = sample.label();

                double loss = lossFunction.calculate(predicted, actual);
                double lossGradient = lossFunction.calculateGradient(predicted, actual);

                RealVector error = propagationUnits.get(propagationUnits.size() - 1)
                    .getNextLayer()
                    .getInputDerivativeVector()
                    .mapMultiply(lossGradient);

                List<PropagationUnit> propagationUnitsReversed = new LinkedList<>(propagationUnits);
                Collections.reverse(propagationUnitsReversed);

                for (PropagationUnit unit : propagationUnitsReversed) {
                    unit.backPropagation(error);
                }

                epochLoss += loss;
            }

            System.out.println("Epoch " + i + ": " + epochLoss);
        }
    }

    public void test(List<RegressionSample> samples) throws FunctionEvaluationException {
        double totalLoss = 0.0;

        List<RegressionSample> predictedSamples = new LinkedList<>();

        for (RegressionSample sample : samples) {
            RealVector propagatedInput = generateFeatures(sample.feature());

            for (PropagationUnit unit : propagationUnits) {
                propagatedInput = unit.forwardPropagation(propagatedInput);
            }

            double predicted = propagatedInput.getEntry(0);
            double actual = sample.label();

            predictedSamples.add(new RegressionSample(sample.feature(), predicted));

            totalLoss += lossFunction.calculate(predicted, actual);
        }

        System.out.println("Predicted: " + predictedSamples);
        System.out.println("Actual: " + samples);

        System.out.println();
        System.out.println("Test completed. Loss: " + totalLoss);
    }

    private List<PropagationUnit> initNetworkArchitecture(
        List<Integer> layerSizes,
        List<RegressionActivationFunction> activationFunctions
    ) {
        Triplet<PropagationInputLayer, List<PropagationLayer>, PropagationOutputLayer> layers =
            initLayers(layerSizes, activationFunctions);

        PropagationInputLayer inputLayer = layers.first();
        List<PropagationLayer> forwardLayers = layers.second();
        PropagationOutputLayer outputLayer = layers.third();

        return initUnits(layerSizes, inputLayer, forwardLayers, outputLayer);
    }

    private Triplet<PropagationInputLayer, List<PropagationLayer>, PropagationOutputLayer> initLayers(
        List<Integer> layerSizes,
        List<RegressionActivationFunction> activationFunctions
    ) {
        PropagationInputLayer propagationInputLayer = new PropagationInputLayer(layerSizes.get(0));
        PropagationOutputLayer propagationOutputLayer = new PropagationOutputLayer(layerSizes.get(layerSizes.size() - 1));

        List<PropagationLayer> propagationLayers = new LinkedList<>();
        for (int i = 1; i < layerSizes.size() - 1; i++) {
            PropagationLayer propagationLayer = new PropagationLayer(layerSizes.get(i), activationFunctions.get(i - 1));

            propagationLayers.add(propagationLayer);
        }

        return new Triplet<>(propagationInputLayer, propagationLayers, propagationOutputLayer);
    }

    private List<PropagationUnit> initUnits(
        List<Integer> layerSizes,
        PropagationInputLayer propagationInputLayer,
        List<PropagationLayer> propagationLayers,
        PropagationOutputLayer propagationOutputLayer
    ) {
        List<PropagationUnit> propagationUnitList = new LinkedList<>();
        for (int i = 0; i < layerSizes.size() - 1; i++) {
            PropagationOperator propagationOperator = new PropagationOperator(
                layerSizes.get(i),
                layerSizes.get(i + 1),
                learningRate
            );

            PropagationUnit propagationUnit;
            if (i == 0) {
                propagationUnit = new PropagationInputUnit(
                    propagationInputLayer,
                    propagationLayers.get(i),
                    propagationOperator
                );
            } else if (i == layerSizes.size() - 2) {
                propagationUnit = new PropagationOutputUnit(
                    propagationLayers.get(i - 1),
                    propagationOutputLayer,
                    propagationOperator
                );
            } else {
                propagationUnit = new PropagationUnit(
                    propagationLayers.get(i - 1),
                    propagationLayers.get(i),
                    propagationOperator
                );
            }

            propagationUnitList.add(propagationUnit);
        }

        return propagationUnitList;
    }

    private RealVector generateFeatures(double x) {
        List<Double> polynomialFeatures = IntStream.range(0, inputSize - 2).boxed()
            .map(deg -> Math.pow(x, deg))
            .toList();

        List<Double> allFeatures = new ArrayList<>(polynomialFeatures);

        allFeatures.add(Math.sin(x));
        allFeatures.add(Math.cos(x));

        double[] featuresArray = allFeatures.stream()
            .mapToDouble(Double::doubleValue)
            .toArray();

        return new ArrayRealVector(featuresArray);
    }

}