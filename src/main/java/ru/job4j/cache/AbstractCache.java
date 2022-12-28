package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> val = new SoftReference<>(value);
        cache.put(key, val);
    }

    public V get(K key) {
        SoftReference<V> defaultValue = new SoftReference<>(null);
        V value = cache.getOrDefault(key, defaultValue).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key);

}