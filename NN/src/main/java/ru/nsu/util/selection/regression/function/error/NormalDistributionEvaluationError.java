package ru.nsu.util.selection.regression.function.error;

import java.util.Random;

public class NormalDistributionEvaluationError implements EvaluationError {

    private final double bias;

    private final double variance;

    public NormalDistributionEvaluationError(double bias, double variance) {
        this.bias = bias;
        this.variance = variance;
    }

    /**
     * <p>Метод Бокса-Мюллера.</p>
     * <p>Преобразует две равномерно распределенные случайные величины в нормально распределенные случайные величины.</p>
     *
     * <p>Пусть u1, u2 ~ U[0, 1]. Тогда cos(2 * pi * u1) * sqrt(-2 * ln(u2)) ~ N(0, 1).</p>
     *
     * <p>
     * Величины
     * </p>
     * <p>z0 = cos(2 * pi * u1) * sqrt(-2 * ln(u2))</p>
     * <p>z1 = sin(2 * pi * u1) * sqrt(-2 * ln(u2))</p>
     * являются независимыми случайными величинами, распределёнными по стандартному нормальному закону N(0, 1).
     * </p>
     */
    @Override
    public Double get() {
        Random random = new Random();
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();

        double normalDistributedValue = Math.sqrt(-2.0 * Math.log(u2)) * Math.cos(2.0 * Math.PI * u1);

        return bias + variance * normalDistributedValue;
    }

}
