package ru.nsu.util.activation;

import lombok.Getter;

import java.util.Collection;
import java.util.function.Function;

@Getter
public abstract class ActivationFunction<T, R> {

    protected final Function<Collection<T>, T> reduceFunction;

    protected ActivationFunction(Function<Collection<T>, T> reduceFunction) {
        this.reduceFunction = reduceFunction;
    }

    public abstract R applyActivation(Collection<T> args);

}
