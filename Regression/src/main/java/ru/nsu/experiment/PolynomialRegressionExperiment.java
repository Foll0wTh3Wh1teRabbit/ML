package ru.nsu.experiment;

import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecomposition;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.linear.SingularMatrixException;
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import ru.nsu.experiment.loss.LossFunction;
import ru.nsu.experiment.regularizer.Regularizer;
import ru.nsu.util.function.ApproximatingFunction;
import ru.nsu.util.selection.SelectionGenerator;
import ru.nsu.util.tuple.Pair;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PolynomialRegressionExperiment {

    private static final int POLYNOMIAL_DEGREE = 5; // Установите нужную степень полинома
    private static final double LAMBDA = 0; // Коэффициент регуляризации

    public Double launchExperiment(
        int numSamples,
        ApproximatingFunction approximatingFunction,
        LossFunction lossFunction,
        Regularizer regularizer
    ) {
        // Генерация выборок в виде пары <feature, label>
        List<Pair<Double, Double>> samples = SelectionGenerator.generateSamples(approximatingFunction, numSamples);

        samples.forEach(it -> System.out.println(it + ","));

        // Создание матрицы Вандермонда
        double[][] featuresVandermondMatrix = samples.stream()
            .map(Pair::first)
            .map(feature -> IntStream.range(0, POLYNOMIAL_DEGREE + 1)
                .mapToDouble(degree -> Math.pow(feature, degree))
                .toArray())
            .toArray(double[][]::new);

        // Создание вектора меток (labels)
        double[] labelsVector = samples.stream()
            .map(Pair::second)
            .mapToDouble(Double::doubleValue)
            .toArray();

        // Создание матриц и векторов для дальнейших вычислений
        RealMatrix x = MatrixUtils.createRealMatrix(featuresVandermondMatrix);
        RealVector y = MatrixUtils.createRealVector(labelsVector);

        // Вычисление X^T * X и X^T * y
        RealMatrix xT = x.transpose();
        RealMatrix xTx = xT.multiply(x);
        RealVector xTy = xT.operate(y);

        // Добавление L2-регуляризации (если LAMBDA > 0)
        RealMatrix lambdaI = MatrixUtils.createRealIdentityMatrix(xTx.getRowDimension()).scalarMultiply(LAMBDA);
        RealMatrix xTxReg = xTx.add(lambdaI);

        // Проверка сингулярности и обращение матрицы
        RealMatrix inversedPart;
        try {
            DecompositionSolver solver = new SingularValueDecompositionImpl(xTxReg).getSolver();
            if (!solver.isNonSingular()) {
                throw new SingularMatrixException(); // Прерываем, если матрица вырождена
            }
            inversedPart = solver.getInverse();
        } catch (SingularMatrixException e) {
            System.err.println("Матрица вырождена, невозможно вычислить обратную.");
            return null;
        }

        // Решение для коэффициентов (весов)
        RealVector result = inversedPart.operate(xTy);

        // Печать полинома
        System.out.print("Полином: ");
        for (int i = 0; i < result.getDimension(); i++) {
            System.out.printf(Locale.US, "%f * x ** %d", result.getEntry(i), i);
            if (i < result.getDimension() - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println();

        // Возврат значения ошибки (например, метрика ошибки MSE может быть рассчитана с использованием lossFunction)
        return null; // Возвращайте значение ошибки, если нужно
    }
}