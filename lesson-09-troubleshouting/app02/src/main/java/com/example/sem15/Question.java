package com.example.sem15;

import java.util.stream.Collectors;

public class Question {

    public static void main(String[] args) {
//        first();
//        second();

        System.out.println(createMessage(args));
    }

    static void first() {
        third();
    }

    static void second() {
        third();
    }

    static void third() {
        System.out.println("Main thread");
    }

    static String createMessage(String[] args) {
        var name = "World";

        if (args.length != 0) {
            name = args[0];
        }

        name = mixUp(name);

        return String.format("Hello %s!", name);
    }

    private static String mixUp(String name) {
        name = name.chars()
                .map(i -> i + 1)
                .mapToObj(x -> Character.toString((char) x))
                .collect(Collectors.joining());
        return name;
    }
}
