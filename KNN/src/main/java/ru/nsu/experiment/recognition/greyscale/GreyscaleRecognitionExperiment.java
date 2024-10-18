package ru.nsu.experiment.recognition.greyscale;

import ru.nsu.experiment.recognition.RecognitionExperiment;
import ru.nsu.matrix.Matrix;
import ru.nsu.util.Pair;
import ru.nsu.util.image.TrainTestSetSplitter;

import java.io.IOException;

public class GreyscaleRecognitionExperiment extends RecognitionExperiment {

    private static final String GREYSCALE_DIR_PATH = "src/main/resources/MNIST_Greyscale/";

    private static final Pair<Matrix[], Matrix[]> trainTestSplit;

    static {
        try {
            trainTestSplit = TrainTestSetSplitter.trainTestSplit(
                GREYSCALE_DIR_PATH,
                TRAIN_SET_PERCENTAGE
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GreyscaleRecognitionExperiment() {
        super(trainTestSplit.first(), trainTestSplit.second());
    }

}
