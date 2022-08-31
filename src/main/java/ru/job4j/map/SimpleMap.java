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
        int bucket = key == null ? indexFor(0) : indexFor(hash(key.hashCode()));
        boolean added = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            added = true;
        }
        return added;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    public int size() {
        return count;
    }


    @SuppressWarnings("unchecked")
    private void expand() {
        capacity = capacity << 1;
        MapEntry<K, V>[] oldTab = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> e : oldTab) {
            if (e != null) {
                if (e.key == null) {
                    table[0] = new MapEntry<>(null, e.value);
                } else {
                    table[indexFor(hash(e.key.hashCode()))] = e;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int bucket = key == null ? indexFor(hash(0)) : indexFor(hash(key.hashCode()));

        if ((key == null) || (table[bucket] != null && table[bucket].key.equals(key))) {
            value = table[bucket].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int bucket = key == null ? indexFor(hash(0)) : indexFor(hash(key.hashCode()));
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
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[index] == null && index < table.length - 1) {
                    index++;
                }
                return table[index] != null;
            }

            @Override public K next() {
                
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = table[index].key;
                index++;
                return key;
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
