package ru.otus.lesson7.functional.programming;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsApplication {
    private static final List<Student> students = Arrays.asList(
            new Student("Alex", 22, 5, 4.5),
            new Student("Maria", 22, 5, 3.5),
            new Student("John", 12, 4, 4.7),
            new Student("Bob", 22, 5, 4.8),
            new Student("Anna", 20, 3, 4.5)
    );

    public static void main(String[] args) throws IOException {
        // creating();
        // stringsJoiner();
        // filterMapReduce();
        // System.out.println(getAvgMark());
        // groupBy();

        // streamConsumers();

        // flatMap();

        streamNotStarted();
    }

    private static void creating() {
        Stream<String> empty = Stream.empty();
        empty.forEach(System.out::println);

        Stream<Integer> single = Stream.of(10);
        single.forEach(System.out::println);

        Stream<Integer> array = Stream.of(1, 2, 3);
        array.forEach(System.out::print);

        IntStream range = IntStream.range(1, 5);
        range.forEach(System.out::print);
    }

    private static void stringsJoiner() {
        String[] arrayOfString = {"A", "B", "C", "D"};
        String result = Arrays.stream(arrayOfString).collect(Collectors.joining(","));
        System.out.println(result);

        String result2 = String.join(",", arrayOfString);
        System.out.println(result2);
    }

    private static void filterMapReduce() {
        System.out.println("filterMapReduce");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int result = list.stream()
                .filter(val -> val % 2 > 0)
                .map(val -> val * 10)
                .reduce(0, Integer::sum);
        System.out.println("result:" + result);
    }

    private static double getAvgMark() {
        return students.stream()
                .filter(st -> st.getCourse() == 5)
                .mapToDouble(Student::getAvgMark)
                .average()
                .orElseThrow(() -> new RuntimeException("Can't calculate average"));
    }

    private static void groupBy() {
        Map<Integer, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));
        System.out.println(map);
    }

    private static void streamConsumers() {
        Stream<Integer> intStream = List.of(1, 2, 3, 4, 5).stream();
        consume(intStream);
        consume(intStream);
    }

    private static void consume(Stream<Integer> stream) {
        stream.forEach(System.out::println);
    }

    private static void flatMap() {
        List<List<Integer>> data = List.of(List.of(1, 2, 3, 4),
                List.of(10, 20, 30, 40),
                List.of(100, 200, 300, 400));

        List<Integer> dataFlat = data.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(dataFlat);
    }

    private static void streamNotStarted() {
        Stream<Integer> dataStream = List.of(1, 2, 3, 4, 5).stream()
                .map(val -> val * 10)
                .peek(System.out::println);
        // .forEach(System.out::println);

        dataStream.forEach(System.out::println);
    }
}
