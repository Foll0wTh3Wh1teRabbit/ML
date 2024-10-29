package ru.nsu.util.selection.classification.binary;

import ru.nsu.util.selection.TrainTestSplitter;
import ru.nsu.util.selection.classification.image.ImageReader;
import ru.nsu.util.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static ru.nsu.util.constants.Constants.BASE_BLACK_WHITE_PATH;
import static ru.nsu.util.constants.Constants.NUM_LABELS;

public class BinaryClassificationTrainTestSplitter extends TrainTestSplitter<BinaryClassificationSample> {

    private final Predicate<Integer> imageLabelPostProcessor;

    public BinaryClassificationTrainTestSplitter(Predicate<Integer> imageLabelPostProcessor) {
        this.imageLabelPostProcessor = imageLabelPostProcessor;
    }

    @Override
    protected List<BinaryClassificationSample> fillSamples(int numSamples) throws IOException {
        List<BinaryClassificationSample> samples = new ArrayList<>();
        for (int j = 0; j < NUM_LABELS; ++j) {
            String basePath = BASE_BLACK_WHITE_PATH + j + "/";

            File[] filesInCurrentDir = new File(basePath).listFiles();
            for (int k = 0; k < Math.min(numSamples / NUM_LABELS, filesInCurrentDir.length); ++k) {
                File currentImage = Objects.requireNonNull(filesInCurrentDir)[k];
                String currentImagePath = currentImage.getAbsolutePath();

                BinaryClassificationSample sample = getSample(currentImagePath);
                samples.add(sample);
            }
        }

        return samples;
    }

    private BinaryClassificationSample getSample(String path) throws IOException {
        Pair<List<Double>, Boolean> featuresWithBinaryLabel =
            ImageReader.readImageWithBinaryLabel(path, imageLabelPostProcessor);

        List<Double> argument = featuresWithBinaryLabel.first();
        Boolean value = featuresWithBinaryLabel.second();

        return new BinaryClassificationSample(argument, value);
    }

}
