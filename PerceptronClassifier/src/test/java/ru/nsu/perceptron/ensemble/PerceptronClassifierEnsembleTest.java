package ru.nsu.perceptron.ensemble;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.perceptron.elementary.classification.ElementaryBinaryClassifier;
import ru.nsu.perceptron.elementary.classification.ElementaryMultiClassifier;
import ru.nsu.util.activation.classification.binary.BinaryClassificationActivationFunction;
import ru.nsu.util.activation.classification.binary.HyperbolicTangentActivationFunction;
import ru.nsu.util.activation.classification.binary.SigmoidActivationFunction;
import ru.nsu.util.activation.classification.multi.MultiClassificationActivationFunction;
import ru.nsu.util.activation.classification.multi.OneVsAllActivationFunction;
import ru.nsu.util.activation.classification.multi.SoftmaxActivationFunction;
import ru.nsu.util.selection.classification.binary.BinaryClassificationSample;
import ru.nsu.util.selection.classification.binary.BinaryClassificationTrainTestSplitter;
import ru.nsu.util.selection.classification.multi.MultiClassificationSample;
import ru.nsu.util.selection.classification.multi.MultiClassifierTrainTestSplitter;
import ru.nsu.util.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ru.nsu.util.constants.Constants.NUM_LABELS;

public class PerceptronClassifierEnsembleTest {

    private static Stream<Arguments> perceptronClassifierEnsembleTestParameters() {
        return Stream.of(
            Arguments.of(
                "Ensemble Sigmoid + Softmax",
                new SigmoidActivationFunction(0.0),
                new SigmoidActivationFunction(0.0002),
                new SigmoidActivationFunction(-0.0002),
                new SoftmaxActivationFunction()
            ),
            Arguments.of(
                "Ensemble Tanh + Softmax",
                new HyperbolicTangentActivationFunction(0.0),
                new HyperbolicTangentActivationFunction(0.0002),
                new HyperbolicTangentActivationFunction(-0.0002),
                new SoftmaxActivationFunction()
            ),
            Arguments.of(
                "Ensemble Sigmoid + OneVsAll",
                new SigmoidActivationFunction(0.0),
                new SigmoidActivationFunction(0.0002),
                new SigmoidActivationFunction(-0.0002),
                new OneVsAllActivationFunction()
            ),
            Arguments.of(
                "Ensemble Tanh + OneVsAll",
                new HyperbolicTangentActivationFunction(0.0),
                new HyperbolicTangentActivationFunction(0.0002),
                new HyperbolicTangentActivationFunction(-0.0002),
                new OneVsAllActivationFunction()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("perceptronClassifierEnsembleTestParameters")
    public void perceptronClassifierEnsembleTest(
        String name,
        BinaryClassificationActivationFunction binaryClassificationActivationFunction,
        BinaryClassificationActivationFunction upperShiftBinaryClassificationActivationFunction,
        BinaryClassificationActivationFunction downShiftBinaryClassificationActivationFunction,
        MultiClassificationActivationFunction multiClassificationActivationFunction
    ) throws IOException {
        System.out.println(name);

        List<MultiClassificationSample> multiClassificationTests = getMultiClassificationTests();

        List<ElementaryBinaryClassifier> binaryClassifiers = new ArrayList<>();
        for (int i = 0; i < NUM_LABELS; ++i) {
            ElementaryBinaryClassifier binaryClassifier = getBinaryClassifier(binaryClassificationActivationFunction, i);
            binaryClassifiers.add(binaryClassifier);
        }
        ElementaryMultiClassifier multiClassifier = new ElementaryMultiClassifier(multiClassificationActivationFunction);
        PerceptronClassifierEnsemble ensemble = new PerceptronClassifierEnsemble(binaryClassifiers, multiClassifier);

        System.out.println("Testing with zero shift");
        ensemble.test(multiClassificationTests);

        System.out.println("Testing with upper shift");
        binaryClassifiers.get(3).setActivationFunction(upperShiftBinaryClassificationActivationFunction);
        binaryClassifiers.get(5).setActivationFunction(upperShiftBinaryClassificationActivationFunction);
        binaryClassifiers.get(8).setActivationFunction(upperShiftBinaryClassificationActivationFunction);
        binaryClassifiers.get(9).setActivationFunction(upperShiftBinaryClassificationActivationFunction);

        ensemble = new PerceptronClassifierEnsemble(binaryClassifiers, multiClassifier);
        ensemble.test(multiClassificationTests);
    }

    private ElementaryBinaryClassifier getBinaryClassifier(
        BinaryClassificationActivationFunction function,
        int label
    ) throws IOException {
        System.out.println("Binary classifier, label = " + label);

        ElementaryBinaryClassifier binaryClassifier = new ElementaryBinaryClassifier(function);

        BinaryClassificationTrainTestSplitter trainTestSplitter =
            new BinaryClassificationTrainTestSplitter(number -> number == label);
        Pair<List<BinaryClassificationSample>, List<BinaryClassificationSample>> trainTestSplit =
            trainTestSplitter.trainTestSplit(22000, 99);

        binaryClassifier.train(trainTestSplit.first());
        binaryClassifier.test(trainTestSplit.second());

        System.out.println();

        return binaryClassifier;
    }

    private List<MultiClassificationSample> getMultiClassificationTests() throws IOException {
        MultiClassifierTrainTestSplitter trainTestSplitter = new MultiClassifierTrainTestSplitter();
        Pair<List<MultiClassificationSample>, List<MultiClassificationSample>> multiTrainTestSplit =
            trainTestSplitter.trainTestSplit(420, 0);

        return multiTrainTestSplit.second();
    }

}
