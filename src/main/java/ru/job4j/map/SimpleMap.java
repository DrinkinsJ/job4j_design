package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        int bucket = indexFor(hash(Objects.hashCode(key)));
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
                table[indexFor(hash(Objects.hashCode(e.key)))] = e;
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int bucket = indexFor(hash(Objects.hashCode(key)));
        if (table[bucket] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[bucket].key)
                    && Objects.equals(key, table[bucket].key)) {
                value = table[bucket].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int bucket = indexFor(hash(Objects.hashCode(key)));
        boolean removed = false;
        if (table[bucket] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[bucket].key)
                    && Objects.equals(key, table[bucket].key)) {
                table[bucket] = null;
                removed = true;
                count--;
                modCount++;
            }
        }
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
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
