package ru.nsu.perceptron.elementary.classification;

import ru.nsu.util.activation.classification.multi.MultiClassificationActivationFunction;
import ru.nsu.util.selection.classification.multi.MultiClassificationSample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.nsu.util.constants.Constants.NUM_LABELS;

public class ElementaryMultiClassifier extends ElementaryClassifier<List<Double>, Integer, MultiClassificationSample> {

    private final MultiClassificationActivationFunction activationFunction;

    public ElementaryMultiClassifier(MultiClassificationActivationFunction activationFunction) {
        super(
            IntStream.range(0, NUM_LABELS)
                .mapToDouble(it -> 1.0)
                .boxed()
                .collect(Collectors.toList())
        );

        this.activationFunction = activationFunction;
    }

    public ElementaryMultiClassifier(
        MultiClassificationActivationFunction activationFunction,
        List<Double> weights
    ) {
        super(weights);
        this.activationFunction = activationFunction;
    }

    @Override
    public Integer predict(List<Double> features) {
        List<Double> weightedFeatures = getWeightedFeatureValues(features);

        return activationFunction.applyActivation(weightedFeatures);
    }

    private List<Double> getWeightedFeatureValues(List<Double> featureValues) {
        List<Double> weightedFeatures = new ArrayList<>();
        for (int i = 0; i < weights.size(); ++i) {
            weightedFeatures.add(weights.get(i) * featureValues.get(i));
        }

        return weightedFeatures;
    }

}
