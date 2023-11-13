package cache.custom;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LruCache<K, V> {

    private final int capacity;
    private final Map<K, V> map;

    public LruCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>(capacity);
    }

    public boolean exists(K key) {
        return map.containsKey(key);
    }

    public V get(K key) {
        if (exists(key)) {
            V value = map.remove(key);
            map.put(key, value);
            return value;
        }

        throw new NoSuchElementException();
    }

    public void add(K key, V value) {
        if (exists(key))
            map.put(key, value);
        else {
            if (map.size() == capacity)
                map.remove(map.keySet().iterator().next());

            map.put(key, value);
        }
    }
}
