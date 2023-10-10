package ru.otus.lesson13.example.visitor;

public class Organization implements Consumer {
    public void accept(ConsumerVisitor visitor) {
        visitor.visit(this);
    }
}
