package ru.nsu.util.selection.classification.image;

import lombok.experimental.UtilityClass;
import ru.nsu.util.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@UtilityClass
public class ImageReader {

    public Pair<List<Double>, Integer> readImageWithLabel(String path) throws IOException {
        String[] partsOfPath = path.split("/");
        String dirNameMatchingLabel = partsOfPath[partsOfPath.length - 2];

        int label = Integer.parseInt(dirNameMatchingLabel);

        File inputFile = new File(path);
        BufferedImage image = ImageIO.read(inputFile);
        List<Double> features = readFeaturesList(image);

        return new Pair<>(features, label);
    }

    public Pair<List<Double>, Boolean> readImageWithBinaryLabel(
        String path,
        Predicate<Integer> labelPredicate
    ) throws IOException {
        String[] partsOfPath = path.split("/");
        String dirNameMatchingLabel = partsOfPath[partsOfPath.length - 2];

        int label = Integer.parseInt(dirNameMatchingLabel);
        boolean binaryLabel = labelPredicate.test(label);

        File inputFile = new File(path);
        BufferedImage image = ImageIO.read(inputFile);
        List<Double> features = readFeaturesList(image);

        return new Pair<>(features, binaryLabel);
    }

    private List<Double> readFeaturesList(BufferedImage image) {
        List<Double> features = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                int rgb = image.getRGB(j, i);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                double normalizedPixelValue = ((double) (red + green + blue) / 3.0) / 255.0;

                features.add(normalizedPixelValue);
            }
        }

        return features;
    }

}