package ru.otus.lesson3.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        List<String> src = new ArrayList<>(Arrays.asList("A","B"));
        List<Object> dst = new ArrayList<>(Arrays.asList(null, null, "C","D"));

        System.out.println(src);
        System.out.println(dst);
    }

    public static double sumOfList(List<? extends Number> numbers) {
        double res = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            res += numbers.get(i).doubleValue();
        }
        return res;
    }

    public static <T> T getFirstElement(List<T> in) {
        return in.get(0);
    }
}