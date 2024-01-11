package sync;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SynchronizedCollectionsDemo {

    public static void main(String... args) {
        synchronizedCollection();
        synchronizedMap();
        synchronizedList();
        synchronizedListIteratorProblem();
        synchronizedSet();
    }

    public static void synchronizedCollection() {
        Queue<String> queue = new LinkedList<>();
        queue.add("one");
        queue.add("two");

        // java.util.Collections.SynchronizedCollection
        Collection<String> synchronizedCollection = Collections.synchronizedCollection(queue);
    }

    public static void synchronizedMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        // java.util.Collections.SynchronizedMap
        map = Collections.synchronizedMap(map);
        // java.util.Collections.SynchronizedSortedMap
        map = Collections.synchronizedSortedMap(new TreeMap<>());
        // java.util.Collections.SynchronizedNavigableMap
        map = Collections.synchronizedNavigableMap(new TreeMap<>());
    }

    public static void synchronizedList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        // java.util.Collections.SynchronizedRandomAccessList
        list = Collections.synchronizedList(list);
        // java.util.Collections.SynchronizedList
        list = Collections.synchronizedList(new LinkedList<>());
    }

    public static void synchronizedListIteratorProblem() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        list = Collections.synchronizedList(list);

        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);

            // add new value -> ConcurrentModificationException
            list.add("three");
        }
    }


    public static void synchronizedSet() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");

        // java.util.Collections.SynchronizedSet
        set = Collections.synchronizedSet(set);
        // java.util.Collections.SynchronizedSortedSet
        set = Collections.synchronizedSortedSet(new TreeSet<>());
        // java.util.Collections.SynchronizedNavigableSet
        set = Collections.synchronizedNavigableSet(new TreeSet<>());

        int a = 0;
        a++;
    }
}
