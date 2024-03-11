package org.example.learningalgorithm.data.distributor;

import org.example.learningalgorithm.data.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NegativeDiagonalDistributor implements PointsDistributor {

    @Override
    public List<Point> distributePoints() {
        return IntStream.range(0, 1000).boxed()
            .map(x -> new Point(-x, x + randomDispersionByUnaryModule.get()))
            .collect(Collectors.toList());
    }

}
