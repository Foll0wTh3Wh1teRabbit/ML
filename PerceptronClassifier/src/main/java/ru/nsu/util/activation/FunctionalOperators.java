package ru.nsu.util.activation;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class FunctionalOperators {

    public static final UnaryOperator<Double> SIGMOID =
        x -> 1.0 / (1.0 + exp(-x));

    public static final Function<Double, UnaryOperator<Double>> SIGMOID_WITH_SHIFT =
        shift -> x -> 1.0 / (1.0 + exp(-x + shift));

    public static final UnaryOperator<Double> SIGMOID_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> x * (1 - x);



    public static final UnaryOperator<Double> TANH =
        x -> (exp(x) - exp(-x)) / (exp(x) + exp(-x));

    public static final Function<Double, UnaryOperator<Double>> TANH_WITH_SHIFT =
        shift -> x -> (exp(x - shift) - exp(-x + shift)) / (exp(x - shift) + exp(-x + shift));

    public static final UnaryOperator<Double> TANH_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> 1 - TANH.apply(x) * TANH.apply(x);



    public static final UnaryOperator<Double> LINEAR =
        x -> x;

    public static final Function<Double, UnaryOperator<Double>> LINEAR_WITH_SHIFT =
        shift -> x -> (x - shift);

    public static final UnaryOperator<Double> LINEAR_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> 1.0;



    public static final Function<Double, UnaryOperator<Double>> POLYNOMIAL =
        degree -> x -> (pow(x, degree));

    public static final Function<Double, Function<Double, UnaryOperator<Double>>> POLYNOMIAL_WITH_SHIFT =
        shift -> degree -> (x -> (pow(x - shift, degree)));

    public static final Function<Double, UnaryOperator<Double>> POLYNOMIAL_DERIVATIVE_FROM_ACTIVATION_VALUE =
        degree -> x -> degree * pow(x, (degree - 1) / degree);



    public static final UnaryOperator<Double> RELU =
        x -> Math.max(0.0, x);

    public static final Function<Double, UnaryOperator<Double>> RELU_WITH_SHIFT =
        shift -> x -> Math.max(0.0, x - shift);

    public static final UnaryOperator<Double> RELU_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> (x > 0 ? 1.0 : 0.0);



    public static final UnaryOperator<Double> LEAKY_RELU =
        x -> Math.max(0.01 * x, x);

    public static final Function<Double, UnaryOperator<Double>> LEAKY_RELU_WITH_SHIFT =
        shift -> x -> Math.max(0.01 * (x - shift), (x - shift));

    public static final UnaryOperator<Double> LEAKY_RELU_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> (x > 0 ? -0.01 : 1.0);

}
