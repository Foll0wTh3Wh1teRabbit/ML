package ru.nsu.util.image;

import lombok.experimental.UtilityClass;
import ru.nsu.matrix.Matrix;
import ru.nsu.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class TrainTestSetSplitter {

    private static final Integer NUM_DIRS = 10;

    private static final Integer PERCENTS = 100;

    public Pair<Matrix[], Matrix[]> trainTestSplit(String dirPath, Integer trainTestPercentage) throws IOException {
        List<Matrix> trainSet = new ArrayList<>();
        List<Matrix> testSet = new ArrayList<>();

        for (int i = 0; i < NUM_DIRS; ++i) {
            File[] files = new File(dirPath + i + "/").listFiles();

            Collections.shuffle(
                new ArrayList<>(
                    Arrays.stream(files).toList()
                )
            );

            for (int j = 0; j < files.length; ++j) {
                Matrix readMatrix = ImageReader.readMatrixFromImage(files[j].getAbsolutePath());
                if ((double) j / files.length < (double) trainTestPercentage / PERCENTS) {
                    trainSet.add(readMatrix);
                } else {
                    testSet.add(readMatrix);
                }
            }
        }

        return new Pair<>(
            trainSet.toArray(new Matrix[0]),
            testSet.toArray(new Matrix[0])
        );
    }

}
