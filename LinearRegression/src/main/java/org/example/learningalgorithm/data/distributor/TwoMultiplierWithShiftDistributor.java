package org.example.learningalgorithm.data.distributor;

import org.example.learningalgorithm.data.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoMultiplierWithShiftDistributor implements PointsDistributor {

    @Override
    public List<Point> distributePoints() {
        return IntStream.range(0, 100).boxed()
            .map(x -> new Point(x, 2 * x + 1 + randomDispersionByUnaryModule.get()))
            .collect(Collectors.toList());
    }

}
