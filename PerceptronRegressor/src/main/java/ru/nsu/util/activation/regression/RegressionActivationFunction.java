package ru.nsu.util.activation.regression;

import org.apache.commons.math.analysis.UnivariateRealFunction;

public interface RegressionActivationFunction extends UnivariateRealFunction {

    double getDerivative(double x);

    double getDerivativeFromActivation(double activation);

}
