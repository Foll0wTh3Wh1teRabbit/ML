package ru.nsu.util.selection.regression;

import lombok.Getter;
import ru.nsu.util.selection.Sample;

@Getter
public class RegressionSample extends Sample<Double, Double> {

    public RegressionSample(Double feature, Double label) {
        super(feature, label);
    }

}
