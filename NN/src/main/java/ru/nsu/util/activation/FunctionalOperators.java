package ru.nsu.util.activation;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.lang.Math.exp;

public class FunctionalOperators {

    public static final UnaryOperator<Double> SIGMOID = x -> (1.0 / (1.0 + exp(-x)));

    public static final UnaryOperator<Double> TANH = x -> (exp(x) - exp(-x)) / (exp(x) + exp(-x));

    public static final Function<Double, UnaryOperator<Double>> SIGMOID_WITH_SHIFT =
        shift -> (x -> (1.0 / (1.0 + exp(-x + shift))));

    public static final Function<Double, UnaryOperator<Double>> TANH_WITH_SHIFT =
        shift -> (x -> (exp(x - shift) - exp(-x + shift)) / (exp(x - shift) + exp(-x + shift)));

    public static final Function<Double, UnaryOperator<Double>> IDENTITY_WITH_SHIFT =
        shift -> (x -> x - shift);

    public static final Function<Double, UnaryOperator<Double>> RELU_WITH_SHIFT =
        shift -> (x -> Math.max(0.0, x - shift));

    public static final Function<Double, UnaryOperator<Double>> LEAKY_RELU_WITH_SHIFT =
        shift -> (x -> Math.max(0.01 * (x - shift), x - shift));

}
