package ru.otus.lesson7.functional.programming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsApplication {
    public static void main(String[] args) {
        List<Student> students = List.of(new Student("Alex", 22, 5, 4.5),
                new Student("Maria", 22, 5, 3.5),
                new Student("John", 12, 4, 4.7),
                new Student("Bob", 22, 5, 4.8));

        List<Student> result = new ArrayList<>();
        // Old JAVA: Напечатать имена топ-студентов 5го курса с оценкой больше 4, по убыванию
        for (Student student : students) {
            if (student.getAvgMark() > 4 && student.getCourse() == 5) {
                result.add(student);
            }
        }
        result.sort((o1, o2) -> Double.compare(o2.getAvgMark(), o1.getAvgMark()));

        for (Student student : result) {
            System.out.println(student.getName());
        }

        // Stream (java 8+): обратите внимание: это чистая функция получения результата
        List<Student> resultNew = students.stream()
                .filter(student -> student.getAvgMark() > 4)
                .filter(student -> student.getCourse() == 5)
                .sorted(Comparator.comparingDouble(Student::getAvgMark).reversed())
                .collect(Collectors.toList());

        // обратите внимание: побочный эффект в виде вывода на консоль отделен от чистого кода
        resultNew.forEach(student -> System.out.println(student.getName()));

        // идеологически так лучше
        for (Student student : resultNew) {
            System.out.println(student);
        }

        // пример гнусного применения. Изменяем другой объект из лямбда-функции.
        List<Student> resultNew2 = new ArrayList<>();
        students.stream()
                .filter(student -> student.getAvgMark() > 4)
                .filter(student -> student.getCourse() == 5)
                .sorted(Comparator.comparingDouble(Student::getAvgMark).reversed())
                .forEach(resultNew2::add);

        resultNew2.forEach(student -> System.out.println(student.getName()));
    }
}
