package ru.nsu.util.image;

import lombok.experimental.UtilityClass;
import ru.nsu.matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class ImageReader {

    public Matrix readMatrixFromImage(String path) throws IOException {
        String[] partsOfPath = path.split("/");
        String dirNameMatchingLabel = partsOfPath[partsOfPath.length - 2];
        int label = Integer.parseInt(dirNameMatchingLabel);

        File inputFile = new File(path);
        BufferedImage image = ImageIO.read(inputFile);

        int[][] readMatrix = new int[image.getWidth()][image.getHeight()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                readMatrix[x][y] = (red + green + blue) / 3;
            }
        }

        return new Matrix(readMatrix, label);
    }

}