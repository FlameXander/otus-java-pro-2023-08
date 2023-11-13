package cache.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Cache<K, V> {

    private final Map<K, V> map = new HashMap<>();

    public boolean exists(K key) {
        return map.containsKey(key);
    }

    public V get(K key) {
        if (exists(key))
            return map.get(key);

        throw new NoSuchElementException();
    }

    public void add(K key, V value) {
        map.put(key, value);
    }
}
