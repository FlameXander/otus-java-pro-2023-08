package ru.otus.lesson3.generics;

public class ComparableBoxNonGeneric implements Comparable {
    private int size;

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof ComparableBoxNonGeneric)) {
            throw new IllegalArgumentException("Невозможно сравнить ComparableBoxNotGeneric с объектом другого типа");
        }
        ComparableBoxNonGeneric another = (ComparableBoxNonGeneric)o;
        return this.size - another.size;
    }
}
