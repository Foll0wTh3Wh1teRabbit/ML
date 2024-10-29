package ru.nsu.util.selection.classification.multi;

import lombok.Getter;
import ru.nsu.util.selection.Sample;

import java.util.List;

@Getter
public class MultiClassificationSample extends Sample<List<Double>, Integer> {

    public MultiClassificationSample(List<Double> features, Integer label) {
        super(features, label);
    }

}
