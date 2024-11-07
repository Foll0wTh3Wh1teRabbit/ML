package ru.nsu.experiment;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.selection.Sample;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

public class PolynomialRegressionExperiment {

    private static final BiFunction<Double, Integer, List<Double>> featureToPolynomialFeatures =
        (feature, degree) -> IntStream.range(0, degree + 1).boxed()
            .mapToDouble(it -> pow(feature, it)).boxed()
            .collect(Collectors.toList());

    public void launchRegression(int polynomialDegree,
                                 List<Sample> samples,
                                 Regularizer regularizer) {

        System.out.println("Received samples: " + samples);

        double[] weights = solveMinimizationSystem(polynomialDegree, samples, regularizer);

        System.out.println("Regression model:");
        for (int i = 0; i < weights.length; i++) {
            System.out.print(weights[i] + " * x ** " + i);

            if (i < weights.length - 1) {
                System.out.print(" + ");
            }
        }
    }

    public void launchRegressionWithTest(int polynomialDegree,
                                         List<Sample> trainSamples,
                                         List<Sample> testSamples,
                                         LossFunction lossFunction,
                                         Regularizer regularizer) {

        double[] weights = solveMinimizationSystem(polynomialDegree, trainSamples, regularizer);

        double loss = lossFunction.calculateLoss(weights, testSamples);
        System.out.println("Test completed. Loss: " + loss);
    }

    private double[] solveMinimizationSystem(int degree, List<Sample> samples, Regularizer regularizer) {
        double[][] polynomialFeatures = samples.stream()
            .map(Sample::x)
            .map(
                feature -> featureToPolynomialFeatures.apply(feature, degree)
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .toArray()
            )
            .toArray(double[][]::new);

        double[] labels = samples.stream()
            .map(Sample::y)
            .mapToDouble(Double::doubleValue)
            .toArray();

        RealMatrix x = new Array2DRowRealMatrix(polynomialFeatures);
        RealMatrix xT = x.transpose();
        RealVector y = new ArrayRealVector(labels);

        RealMatrix xTx = xT.multiply(x);
        RealVector xTy = xT.operate(y);

        RealMatrix xTxWithRegularization = Optional.ofNullable(regularizer)
            .map(reg -> reg.evaluateMatrixWithRegularization(xTx))
            .orElse(xTx);

        RealMatrix xTxInversed = new LUDecompositionImpl(xTxWithRegularization).getSolver().getInverse();

        return xTxInversed.operate(xTy).getData();
    }

}