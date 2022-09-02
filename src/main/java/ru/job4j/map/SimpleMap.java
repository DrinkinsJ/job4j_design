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
                if (e.key == null) {
                    table[indexFor(hash(Objects.hashCode(null)))] = e;
                } else {
                    table[indexFor(hash(Objects.hashCode(e.key)))] = e;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int bucket;
        if (key == null) {
            bucket = indexFor(hash(Objects.hashCode(null)));
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
        int bucket = indexFor(hash(Objects.hashCode(key)));
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
            int index;

            @Override public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
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
