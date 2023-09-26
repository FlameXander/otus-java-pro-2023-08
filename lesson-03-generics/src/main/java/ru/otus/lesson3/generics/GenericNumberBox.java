package ru.otus.lesson3.generics;

public class GenericNumberBox<N extends Number> {
    private N[] numbers;

    public GenericNumberBox(N... numbers) {
        this.numbers = numbers;
    }

    public double avg() {
        if (numbers.length == 0) {
            return 0.0;
        }
        double result = 0.0;
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i].doubleValue();
        }
        return result / numbers.length;
    }

    public boolean sameAvg(GenericNumberBox<?> another) {
        if (another == null) {
            return false;
        }
        if (another == this) {
            return true;
        }
        return Math.abs(this.avg() - another.avg()) < 0.001f;
    }
}