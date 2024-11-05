package ru.nsu.perceptron.elementary;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.perceptron.elementary.classification.ElementaryBinaryClassifier;
import ru.nsu.util.activation.classification.binary.BinaryClassificationActivationFunction;
import ru.nsu.util.activation.classification.binary.HyperbolicTangentActivationFunction;
import ru.nsu.util.activation.classification.binary.SigmoidActivationFunction;
import ru.nsu.util.selection.classification.binary.BinaryClassificationSample;
import ru.nsu.util.selection.classification.binary.BinaryClassificationTrainTestSplitter;
import ru.nsu.util.tuple.Pair;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ElementaryBinaryClassifierTest {

    private static Stream<Arguments> binaryClassificationTestParameters() {
        return Stream.of(
            Arguments.of("Label0, sigmoid", (Predicate<Integer>) integer -> integer == 0, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label1, sigmoid", (Predicate<Integer>) integer -> integer == 1, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label2, sigmoid", (Predicate<Integer>) integer -> integer == 2, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label3, sigmoid", (Predicate<Integer>) integer -> integer == 3, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label4, sigmoid", (Predicate<Integer>) integer -> integer == 4, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label5, sigmoid", (Predicate<Integer>) integer -> integer == 5, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label6, sigmoid", (Predicate<Integer>) integer -> integer == 6, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label7, sigmoid", (Predicate<Integer>) integer -> integer == 7, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label8, sigmoid", (Predicate<Integer>) integer -> integer == 8, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),
            Arguments.of("Label9, sigmoid", (Predicate<Integer>) integer -> integer == 9, new SigmoidActivationFunction(0.0), new SigmoidActivationFunction(0.00001), new SigmoidActivationFunction(-0.00001)),

            Arguments.of("Label0, tanh", (Predicate<Integer>) integer -> integer == 0, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label1, tanh", (Predicate<Integer>) integer -> integer == 1, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label2, tanh", (Predicate<Integer>) integer -> integer == 2, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label3, tanh", (Predicate<Integer>) integer -> integer == 3, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label4, tanh", (Predicate<Integer>) integer -> integer == 4, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label5, tanh", (Predicate<Integer>) integer -> integer == 5, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label6, tanh", (Predicate<Integer>) integer -> integer == 6, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label7, tanh", (Predicate<Integer>) integer -> integer == 7, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label8, tanh", (Predicate<Integer>) integer -> integer == 8, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001)),
            Arguments.of("Label9, tanh", (Predicate<Integer>) integer -> integer == 9, new HyperbolicTangentActivationFunction(0.0), new HyperbolicTangentActivationFunction(0.00001), new HyperbolicTangentActivationFunction(-0.00001))
        );
    }

    @ParameterizedTest
    @MethodSource("binaryClassificationTestParameters")
    public void binaryClassificationTest(
        String name,
        Predicate<Integer> labelToClassify,
        BinaryClassificationActivationFunction trainActivationFunction,
        BinaryClassificationActivationFunction upperShiftActivationFunction,
        BinaryClassificationActivationFunction downShiftActivationFunction
    ) throws IOException {
        System.out.println(name);

        ElementaryBinaryClassifier binaryClassifier = new ElementaryBinaryClassifier(trainActivationFunction);

        BinaryClassificationTrainTestSplitter trainTestSplitter =
            new BinaryClassificationTrainTestSplitter(labelToClassify);
        Pair<List<BinaryClassificationSample>, List<BinaryClassificationSample>> trainTestSplit =
            trainTestSplitter.trainTestSplit(20000, 80);

        binaryClassifier.train(trainTestSplit.first());

        binaryClassifier.setActivationFunction(trainActivationFunction);
        binaryClassifier.test(trainTestSplit.second());
        System.out.println();

        binaryClassifier.setActivationFunction(upperShiftActivationFunction);
        binaryClassifier.test(trainTestSplit.second());
        System.out.println();

        binaryClassifier.setActivationFunction(downShiftActivationFunction);
        binaryClassifier.test(trainTestSplit.second());
        System.out.println();
    }

}
