package org.example;

import org.example.learningalgorithm.data.DataSet;
import org.example.learningalgorithm.data.distributor.TwoMultiplierWithShiftDistributor;
import org.example.learningalgorithm.regressor.LinearRegressor;

public class Main {
    public static void main(String[] args) {
        DataSet dataSet = new DataSet(new TwoMultiplierWithShiftDistributor());
        LinearRegressor linearRegressor = new LinearRegressor(dataSet);

        dataSet.printDataset();

        for (int i = 0; i < 10000; ++i) {
            linearRegressor.launchIteration();
        }

        System.out.printf("Final w0 = %f, final w1 = %f%n", linearRegressor.getW0(), linearRegressor.getW1());
    }
}