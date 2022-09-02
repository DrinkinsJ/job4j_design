package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int bucket = key == null ? hash(0) : indexFor(hash(key.hashCode()));
        boolean added = false;
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            added = true;
        }
        return added;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        capacity = capacity << 1;
        MapEntry<K, V>[] oldTab = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> e : oldTab) {
            if (e != null) {
                if (e.key == null) {
                    table[indexFor(hash(0))] = new MapEntry<>(null, e.value);
                } else {
                    table[indexFor(hash(e.key.hashCode()))] = new MapEntry<>(e.key, e.value);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int bucket;
        if (key == null) {
            bucket = indexFor(0);
        } else {
            bucket = indexFor(hash(key.hashCode()));
        }
        if (table[bucket] != null && table[bucket].key != null) {
            if (key == null) {
                if (table[bucket].key == null) {
                    value = table[0].value;
                } 
            } else if (key.hashCode() == table[bucket].key.hashCode() && key.equals(table[bucket].key)) {
                value = table[bucket].value;
            }
        } else if (key == null && table[bucket].value != null && table[bucket].key == null) {
            value = table[bucket].value;
        }
        return value;
    }


    @Override
    public boolean remove(K key) {
        int bucket = key == null ? hash(0) : indexFor(hash(key.hashCode()));
        boolean removed = false;
        if ((table[bucket] != null && key == null) || (table[bucket] != null && table[bucket].key.equals(key))) {
            table[bucket] = null;
            removed = true;
            count--;
            modCount++;
        }
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index < table.length) {
                    while (table[index] == null && index < table.length - 1) {
                        index++;
                    }
                    result = table[index] != null;
                }
                return result;
            }

            @Override public K next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }
    
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
