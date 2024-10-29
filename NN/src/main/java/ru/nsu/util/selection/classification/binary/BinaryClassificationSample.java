package ru.nsu.util.selection.classification.binary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.util.selection.Sample;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BinaryClassificationSample extends Sample<List<Double>, Boolean> {

    public BinaryClassificationSample(List<Double> features, Boolean label) {
        super(features, label);
    }

}

