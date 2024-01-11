package containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConcurrentHashMapDemo {

    public static void main(String... args) {
//        createNewMap();
//        createNewMapWithConcurrencyLevel();
//        iteratorHashMap();
//        failSaveIteratorConcurrentHashMap();
        atomicConcurrentMapModification();
    }

    public static void createNewMap() {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        map.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void createNewMapWithConcurrencyLevel() {
        int initialCapacity = 16;
        float loadFactor = 0.75f;
        // количество сегментов будет выбрано как ближайшая степень двойки, большая чем concurrencyLevel
        int concurrencyLevel = 1;   // one thread for write

        Map<String, Integer> map = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);
        map.put("one", 1);
        map.put("two", 2);

        map.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void iteratorHashMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        Iterator<String> it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> ConcurrentModificationException
            map.put("three", 3);
        }
    }

    public static void failSaveIteratorConcurrentHashMap() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        Iterator<String> it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> it will not see this and no exception
            map.put("three", 3);
        }

        // {two=2, three=3, one=1}
        System.out.println(map);
    }

    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    public static void atomicConcurrentMapModification() {

        Random random = new Random();

        // not thread safe
        for (int i = 0; i < 20; i++) {
            String key = String.valueOf(random.nextInt(10));

            if (!map.containsKey(key))
                map.put(key, i);
        }

        // thread safe
        for (int i = 0; i < 20; i++) {
            String key = String.valueOf(random.nextInt(10));
            final int ii = i;

            map.computeIfAbsent(key, new Function<String, Integer>() {
                // Эти лямбды - потоко-безопасны
                @Override
                public Integer apply(String k) {
                    return ii;
                }
            });
        }
    }

}
