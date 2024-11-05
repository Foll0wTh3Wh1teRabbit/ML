package ru.nsu;

import ru.nsu.experiment.PolynomialRegressionExperiment;
import ru.nsu.experiment.loss.MeanSquaredErrorLossFunction;
import ru.nsu.experiment.regularizer.RidgeRegularizer;
import ru.nsu.util.function.CosinusApproximatingFunction;
import ru.nsu.util.function.error.NormalDistributionEvaluationError;

public class Main {

    public static void main(String[] args) {
        new PolynomialRegressionExperiment().launchExperiment(
            5,
            new CosinusApproximatingFunction(new NormalDistributionEvaluationError(0.0,0.33)),
            new MeanSquaredErrorLossFunction(null),
            new RidgeRegularizer(1e-10)
        );
    }

}
