package ru.nsu.experiment;

import lombok.experimental.UtilityClass;
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

@UtilityClass
public class PolynomialRegressionExperiment {

    private static final BiFunction<Double, Integer, List<Double>> featureToPolynomialFeatures =
        (feature, degree) -> IntStream.range(0, degree + 1).boxed()
            .mapToDouble(it -> pow(feature, it)).boxed()
            .collect(Collectors.toList());

    public void launchRegression(int polynomialDegree,
                                 List<Sample> samples,
                                 Regularizer regularizer) {

        System.out.println("Received samples: " + samples);

        double[] regressionModel = solveMinimizationSystem(polynomialDegree, samples, regularizer);
        printRegressionModel(regressionModel);
    }

    public double launchRegressionWithTest(int polynomialDegree,
                                         List<Sample> trainSamples,
                                         List<Sample> testSamples,
                                         LossFunction lossFunction,
                                         Regularizer regularizer) {

        double[] regressionModel = solveMinimizationSystem(polynomialDegree, trainSamples, regularizer);

        return lossFunction.calculateLoss(regressionModel, testSamples);
    }

    private double[] solveMinimizationSystem(int degree, List<Sample> samples, Regularizer regularizer) {
        double[][] polynomialFeatures = getPolynomialFeatures(degree, samples);
        double[] labels = getLabels(samples);

        RealMatrix x = new Array2DRowRealMatrix(polynomialFeatures);
        RealMatrix xT = x.transpose();
        RealVector y = new ArrayRealVector(labels);

        RealMatrix xTx = xT.multiply(x);
        RealVector xTy = xT.operate(y);

        RealMatrix lambdaI = Optional.ofNullable(regularizer)
            .map(reg -> reg.evaluateMatrixRegularizationTerm(xTx.getRowDimension()))
            .orElse(null);

        RealMatrix xTxWithLambdaI = Optional.ofNullable(lambdaI)
            .map(xTx::subtract)
            .orElse(xTx);

        RealMatrix xTxInversed = new LUDecompositionImpl(xTxWithLambdaI).getSolver().getInverse();

        return xTxInversed.operate(xTy).getData();
    }

    private double[][] getPolynomialFeatures(int degree, List<Sample> samples) {
        return samples.stream()
            .map(Sample::x)
            .map(
                feature -> featureToPolynomialFeatures.apply(feature, degree)
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .toArray()
            )
            .toArray(double[][]::new);
    }

    private double[] getLabels(List<Sample> samples) {
        return samples.stream()
            .map(Sample::y)
            .mapToDouble(Double::doubleValue)
            .toArray();
    }

    private void printRegressionModel(double[] regressionModel) {
        System.out.println("Regression model:");
        for (int i = 0; i < regressionModel.length; i++) {
            System.out.print(regressionModel[i] + " * x ** " + i);

            if (i < regressionModel.length - 1) {
                System.out.print(" + ");
            }
        }
    }

}