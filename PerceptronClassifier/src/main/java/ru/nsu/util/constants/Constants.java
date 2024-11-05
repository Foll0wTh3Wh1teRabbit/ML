package ru.nsu.util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final Integer DIMENSION_LENGTH = 28;

    public static final Integer NUM_PIXELS = DIMENSION_LENGTH * DIMENSION_LENGTH;

    public static final Integer NUM_LABELS = 10;

    public static final String BASE_BLACK_WHITE_PATH = "src/main/resources/MNIST_BlackWhite/";

    public static final String BASE_GREYSCALE_PATH = "src/main/resources/MNIST_Greyscale/";

}
