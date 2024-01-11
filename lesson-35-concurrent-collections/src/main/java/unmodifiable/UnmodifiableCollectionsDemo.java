package unmodifiable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnmodifiableCollectionsDemo {

    public static void main(String... args) {
        unmodifiableCollection();
        unmodifiableMap();
        unmodifiableList();
        unmodifiableSet();
    }

    public static void unmodifiableCollection() {
        Queue<String> queue = new LinkedList<>();
        queue.add("one");
        queue.add("two");

        // java.util.Collections.UnmodifiableCollection
        Collection<String> unmodifiableCollection = Collections.unmodifiableCollection(queue);
    }

    public static void unmodifiableMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        // java.util.Collections.UnmodifiableMap
        map = Collections.unmodifiableMap(map);
        // java.util.ImmutableCollections.MapN
        map = Map.of("one", 1, "two", 2);
    }

    public static void unmodifiableList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        // java.util.Collections.UnmodifiableRandomAccessList
        list = Collections.unmodifiableList(list);
        // java.util.Collections.UnmodifiableList
        list = Collections.unmodifiableList(new LinkedList<>());
        // java.util.ImmutableCollections.ListN
        list = List.of("one", "two");
    }

    public static void unmodifiableSet() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");

        // java.util.Collections.UnmodifiableSet
        set = Collections.unmodifiableSet(set);
        // java.util.ImmutableCollections.SetN
        set = Set.of("one", "two");
    }
}
