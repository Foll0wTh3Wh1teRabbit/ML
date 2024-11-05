package ru.nsu.perceptron.elementary.classification;

import lombok.Setter;
import ru.nsu.perceptron.ElementaryForwardPropagatingPerceptron;
import ru.nsu.perceptron.ElementaryLearningPerceptron;
import ru.nsu.util.activation.classification.binary.BinaryClassificationActivationFunction;
import ru.nsu.util.selection.classification.binary.BinaryClassificationSample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.nsu.util.constants.Constants.NUM_PIXELS;

@Setter
public class ElementaryBinaryClassifier
    extends
        ElementaryClassifier<List<Double>, Boolean, BinaryClassificationSample>
    implements
        ElementaryLearningPerceptron<List<Double>, Boolean, BinaryClassificationSample>,
        ElementaryForwardPropagatingPerceptron<List<Double>, Double> {

    private BinaryClassificationActivationFunction activationFunction;

    public ElementaryBinaryClassifier(BinaryClassificationActivationFunction activationFunction) {
        super(
            IntStream.range(0, NUM_PIXELS + 1)
                .mapToDouble(it -> 0.0)
                .boxed()
                .collect(Collectors.toList())
        );

        this.activationFunction = activationFunction;
    }

    @Override
    public void train(List<BinaryClassificationSample> trainSamples) {
        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            for (BinaryClassificationSample trainSample : trainSamples) {
                boolean predicted = predict(trainSample.getFeature());
                boolean actual = trainSample.getLabel();

                if (predicted != actual) {
                    weightAdjustment(trainSample);
                }
            }
        }
    }

    @Override
    public void test(List<BinaryClassificationSample> testSamples) {
        int truePositive = 0;
        int falsePositive = 0;
        int trueNegative = 0;
        int falseNegative = 0;

        for (BinaryClassificationSample testSample : testSamples) {
            boolean predicted = predict(testSample.getFeature());
            boolean actual = testSample.getLabel();

            if (predicted && actual) {
                truePositive++;
            } else if (predicted) {
                falsePositive++;
            } else if (!actual) {
                trueNegative++;
            } else {
                falseNegative++;
            }
        }

        double accuracy = (double) (truePositive + trueNegative) /
            (truePositive + trueNegative + falsePositive + falseNegative);
        double precision = (double) truePositive / (truePositive + falsePositive);
        double recall = (double) truePositive / (truePositive + falseNegative);

        System.out.println("Test completed");
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
    }

    @Override
    public Boolean predict(List<Double> features) {
        List<Double> weightedFeatures = getWeightedFeatureValues(features);

        return activationFunction.applyActivation(weightedFeatures);
    }

    @Override
    public Double forwardPropagation(List<Double> features) {
        List<Double> weightedFeatures = getWeightedFeatureValues(features);

        return activationFunction.getActivationValue(weightedFeatures);
    }

    private void weightAdjustment(BinaryClassificationSample incorrectPredictedSample) {
        List<Double> features = incorrectPredictedSample.getFeature();

        boolean actual = incorrectPredictedSample.getLabel();
        double error = actual ? 1.0 : -1.0;

        for (int i = 0; i < weights.size(); ++i) {
            double featureValue = i == 0 ? 0.0 : features.get(i - 1);

            weights.set(i, weights.get(i) + LEARNING_RATE * error * featureValue);
        }
    }

    private List<Double> getWeightedFeatureValues(List<Double> featureValues) {
        List<Double> weightedFeatures = new ArrayList<>();
        for (int i = 0; i < weights.size(); ++i) {
            double value = i == 0 ? 1.0 : featureValues.get(i - 1);

            weightedFeatures.add(weights.get(i) * value);
        }

        return weightedFeatures;
    }

}
