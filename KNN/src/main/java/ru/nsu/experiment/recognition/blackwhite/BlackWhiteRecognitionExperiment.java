package ru.nsu.experiment.recognition.blackwhite;

import ru.nsu.util.Pair;
import ru.nsu.util.image.TrainTestSetSplitter;
import ru.nsu.matrix.Matrix;
import ru.nsu.experiment.recognition.RecognitionExperiment;

import java.io.IOException;

public class BlackWhiteRecognitionExperiment extends RecognitionExperiment {

    private static final String BLACK_WHITE_DIR_PATH = "src/main/resources/MNIST_BlackWhite/";

    private static final Pair<Matrix[], Matrix[]> trainTestSplit;

    static {
        try {
            trainTestSplit = TrainTestSetSplitter.trainTestSplit(
                BLACK_WHITE_DIR_PATH,
                TRAIN_SET_PERCENTAGE
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BlackWhiteRecognitionExperiment() {
        super(trainTestSplit.first(), trainTestSplit.second());
    }

}
