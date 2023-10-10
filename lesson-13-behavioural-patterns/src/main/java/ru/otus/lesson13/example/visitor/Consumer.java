package ru.otus.lesson13.example.visitor;

public interface Consumer {
    void accept(ConsumerVisitor visitor);
}
