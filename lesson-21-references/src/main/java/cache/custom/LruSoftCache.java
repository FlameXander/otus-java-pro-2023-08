package cache.custom;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LruSoftCache<K, V> {

    private final int capacity;
    private final Map<K, SoftReference<V>> map;

    public LruSoftCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>(capacity);
    }

    public boolean exists(K key) {
        SoftReference<V> softValue = map.get(key);
        return softValue != null && softValue.get() != null;
    }

    public V get(K key) {
        SoftReference<V> softValue = map.remove(key);
        V value = softValue == null ? null : softValue.get();

        if (value == null)
            throw new NoSuchElementException();

        map.put(key, softValue);
        return value;
    }

    public void add(K key, V value) {
        if (exists(key))
            map.put(key, new SoftReference<>(value));
        else {
            if (map.size() == capacity)
                map.remove(map.keySet().iterator().next());

            map.put(key, new SoftReference<>(value));
        }
    }
}
