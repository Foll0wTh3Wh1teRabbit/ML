package ru.nsu.util.selection;

public record Sample(Double x, Double y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
