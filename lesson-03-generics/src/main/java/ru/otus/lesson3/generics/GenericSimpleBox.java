package ru.otus.lesson3.generics;

public class GenericSimpleBox<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public GenericSimpleBox(T obj) {
        this.obj = obj;
    }
}
