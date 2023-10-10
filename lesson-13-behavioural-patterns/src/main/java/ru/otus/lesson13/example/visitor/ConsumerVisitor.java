package ru.otus.lesson13.example.visitor;

public interface ConsumerVisitor {
    void visit(Organization subject);
    void visit(Person subject);

}
