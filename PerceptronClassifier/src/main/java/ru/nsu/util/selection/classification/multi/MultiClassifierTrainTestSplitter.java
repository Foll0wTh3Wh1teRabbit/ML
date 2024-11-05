package ru.nsu.util.selection.classification.multi;

import ru.nsu.util.selection.TrainTestSplitter;
import ru.nsu.util.selection.classification.image.ImageReader;
import ru.nsu.util.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.nsu.util.constants.Constants.BASE_BLACK_WHITE_PATH;
import static ru.nsu.util.constants.Constants.NUM_LABELS;

public class MultiClassifierTrainTestSplitter extends TrainTestSplitter<MultiClassificationSample> {

    @Override
    protected List<MultiClassificationSample> fillSamples(int numSamples) throws IOException {
        List<MultiClassificationSample> samples = new ArrayList<>();
        for (int j = 0; j < NUM_LABELS; ++j) {
            String basePath = BASE_BLACK_WHITE_PATH + j + "/";

            File[] filesInCurrentDir = new File(basePath).listFiles();
            for (int k = 0; k < Math.min(numSamples / NUM_LABELS, filesInCurrentDir.length); ++k) {
                File currentImage = Objects.requireNonNull(filesInCurrentDir)[k];
                String currentImagePath = currentImage.getAbsolutePath();

                MultiClassificationSample sample = getSample(currentImagePath);
                samples.add(sample);
            }
        }

        return samples;
    }

    private MultiClassificationSample getSample(String path) throws IOException {
        Pair<List<Double>, Integer> featuresWithBinaryLabel = ImageReader.readImageWithLabel(path);

        List<Double> argument = featuresWithBinaryLabel.first();
        Integer value = featuresWithBinaryLabel.second();

        return new MultiClassificationSample(argument, value);
    }

}
