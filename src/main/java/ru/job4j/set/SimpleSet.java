package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.*;

public class SimpleSet<T> implements Set<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean added = false;
        if (!contains(value)) {
            set.add(value);
            added = true;
        }
        return added;
    }

    @Override
    public boolean contains(T value) {
        boolean contain = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
