package ru.otus.lesson13.example.visitor;

public class VisitorTest {
    public static void main(String[] args) {
        test(new Organization());
        test(new Person());
    }

    public static void test(Consumer consumer) {
        ConsumerVisitor visitor = new ChargeVisitor();
        consumer.accept(visitor);
    }
}
