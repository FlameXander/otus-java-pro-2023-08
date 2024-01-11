package containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CopyOnWriteArrayListDemo {

    public static void main(String... args) {
        createNewList();
//        iteratorArrayList();
        iteratorCopyOnWriteArrayList();
    }

    public static void createNewList() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("one");
        list.add("two");

        list.forEach(System.out::println);
    }

    public static void iteratorArrayList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> ConcurrentModificationException
            list.add("three");
        }
    }

    public static void iteratorCopyOnWriteArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("one");
        list.add("two");

        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> it will not see this and no exception
            list.add("three");
        }

        // [one, two, three, three]
        System.out.println(list);
    }

}
