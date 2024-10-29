package ru.nsu.perceptron.multilayer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultiLayerPerceptronTest {

    private static Stream<Arguments> multiLayerPerceptronTestParameters() {
        return Stream.of(

        );
    }

    @ParameterizedTest
    @MethodSource("multiLayerPerceptronTestParameters")
    public void multiLayerPerceptronTest() {

    }

}
