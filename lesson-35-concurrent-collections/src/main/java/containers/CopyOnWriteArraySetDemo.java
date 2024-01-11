package containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CopyOnWriteArraySetDemo {

    public static void main(String... args) {
        createNewSet();
//        iteratorHashSet();
        iteratorCopyOnWriteArraySet();
    }

    public static void createNewSet() {
        Set<String> set = new CopyOnWriteArraySet<>();
        set.add("one");
        set.add("two");

        set.forEach(System.out::println);
    }

    public static void iteratorHashSet() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");

        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> ConcurrentModificationException
            set.add("three");
        }
    }

    public static void iteratorCopyOnWriteArraySet() {
        Set<String> set = new CopyOnWriteArraySet<>();
        set.add("one");
        set.add("two");

        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> it will not see this and no exception
            set.add("three");
        }

        // [one, two, three]
        System.out.println(set);
    }

}
