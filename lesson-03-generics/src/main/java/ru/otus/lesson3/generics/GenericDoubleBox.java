package ru.otus.lesson3.generics;

public class GenericDoubleBox<T1, T2> {
    private T1 obj1;
    private T2 obj2;

    public T1 getObj1() {
        return obj1;
    }

    public void setObj1(T1 obj1) {
        this.obj1 = obj1;
    }

    public T2 getObj2() {
        return obj2;
    }

    public void setObj2(T2 obj2) {
        this.obj2 = obj2;
    }

    public GenericDoubleBox(T1 obj1, T2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
}
