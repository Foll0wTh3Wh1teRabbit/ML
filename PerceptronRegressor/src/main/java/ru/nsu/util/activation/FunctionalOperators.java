package ru.nsu.util.activation;

import java.util.function.UnaryOperator;

import static java.lang.Math.exp;

public class FunctionalOperators {

    public static final UnaryOperator<Double> SIGMOID =
        x -> 1.0 / (1.0 + exp(-x));

    public static final UnaryOperator<Double> SIGMOID_DERIVATIVE =
        x -> SIGMOID.apply(x) * (1.0 - SIGMOID.apply(x));

    public static final UnaryOperator<Double> SIGMOID_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> x * (1.0 - x);



    public static final UnaryOperator<Double> TANH =
        x -> (exp(x) - exp(-x)) / (exp(x) + exp(-x));

    public static final UnaryOperator<Double> TANH_DERIVATIVE =
        x -> 1.0 - TANH.apply(x) * TANH.apply(x);

    public static final UnaryOperator<Double> TANH_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> 1.0 - x * x;



    public static final UnaryOperator<Double> LINEAR =
        x -> x;

    public static final UnaryOperator<Double> LINEAR_DERIVATIVE =
        x -> 1.0;

    public static final UnaryOperator<Double> LINEAR_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> 1.0;



    public static final UnaryOperator<Double> RELU =
        x -> Math.max(0.0, x);

    public static final UnaryOperator<Double> RELU_DERIVATIVE =
        x -> (x > 0 ? 1.0 : 0.0);

    public static final UnaryOperator<Double> RELU_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> (x > 0 ? 1.0 : 0.0);



    public static final UnaryOperator<Double> LEAKY_RELU =
        x -> Math.max(0.01 * x, x);

    public static final UnaryOperator<Double> LEAKY_RELU_DERIVATIVE =
        x -> (x > 0 ? -0.01 : 1.0);

    public static final UnaryOperator<Double> LEAKY_RELU_DERIVATIVE_FROM_ACTIVATION_VALUE =
        x -> (x > 0 ? -0.01 : 1.0);

}
