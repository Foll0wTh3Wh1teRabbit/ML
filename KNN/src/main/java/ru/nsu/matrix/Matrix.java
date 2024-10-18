package ru.nsu.matrix;

public class Matrix {

    public final int[][] numArrays;

    public final int label;

    public Matrix(int[][] numArrays) {
        this.numArrays = numArrays;
        this.label = -1;
    }

    public Matrix(int[][] numArrays, int label) {
        this.numArrays = numArrays;
        this.label = label;
    }

}
