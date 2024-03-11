package org.example.learningalgorithm.data;

import org.example.learningalgorithm.data.distributor.PointsDistributor;

import java.util.List;

public class DataSet {

    private final List<Point> points;

    public DataSet(PointsDistributor pointsDistributor) {
        this.points = pointsDistributor.distributePoints();
    }

    public void printDataset() {
        points.stream()
            .map(it -> String.format("(%f, %f)", it.getX(), it.getY()))
            .forEachOrdered(System.out::println);
    }

    public List<Point> getPoints() {
        return points;
    }

}
