package org.example.learningalgorithm.data.distributor;

import org.example.learningalgorithm.data.Point;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public interface PointsDistributor {

    Supplier<Double> randomDispersionByUnaryModule = () -> (2.0 * new Random().nextDouble() - 1.0);

    List<Point> distributePoints();

}
