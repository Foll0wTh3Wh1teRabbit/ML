package ru.nsu.util.selection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Sample<Feature, Label> {

    protected Feature feature;

    protected Label label;

    protected Sample(Feature feature, Label label) {
        this.feature = feature;
        this.label = label;
    }

}
