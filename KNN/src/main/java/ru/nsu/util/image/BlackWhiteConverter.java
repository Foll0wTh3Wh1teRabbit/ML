package ru.nsu.util.image;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class BlackWhiteConverter {

    private static final String PATH_TO_GREYSCALE_DIR = "KNN/src/main/resources/MNIST_Greyscale/";

    private static final String PATH_TO_BLACK_WHITE_DIR = "KNN/src/main/resources/MNIST_BlackWhite/";

    private static final String[] DIRECTORIES_NAMES = new String[] {
        "0/", "1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/", "9/"
    };

    public void convertOriginalGreyscaleToBlackWhite() throws IOException {
        for (String dirName : DIRECTORIES_NAMES) {
            File newDirInBlackWhite = new File(PATH_TO_BLACK_WHITE_DIR + dirName);
            newDirInBlackWhite.mkdir();

            File[] files = new File(PATH_TO_GREYSCALE_DIR + dirName).listFiles();
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);

                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        int rgb = image.getRGB(x, y);

                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = rgb & 0xFF;
                        int gray = (red + green + blue) / 3;

                        int threshold = 128;
                        int bw = (gray >= threshold) ? 255 : 0;

                        int newRgb = (bw << 16) | (bw << 8) | bw;

                        image.setRGB(x, y, newRgb);
                    }
                }

                File outputFile = new File(PATH_TO_BLACK_WHITE_DIR + dirName + file.getName());
                ImageIO.write(image, "jpeg", outputFile);
            }
        }
    }

}
