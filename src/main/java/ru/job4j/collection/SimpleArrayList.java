package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static final Object[] EMPTY_CONTAINER = {};
    private static final int DEFAULT_CAPACITY = 10;
    
    private T[] container;

    private int size;

    private int modCount;
    
    @SuppressWarnings("unchecked")
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        try {
            container[size] = value;
        } catch (ArrayIndexOutOfBoundsException ex) {
            grow();
            container[size] = value;
        }
        size++;
        modCount++;
    }

    private Object[] grow() {
        return grow(size + 1);
    }
    
    @SuppressWarnings("unchecked")
    private Object[] grow(int minCapacity) {
        int oldCapacity = container.length;
        if (oldCapacity > 0 || container != EMPTY_CONTAINER) {
            int newCapacity = (oldCapacity * 2);
            container = Arrays.copyOf(container, newCapacity);
        } else {
            container = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
        return container;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        try {
            container[index] = newValue;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(container, index + 1, container, index, numMoved);
        }
        modCount++;
        container[--size] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override public String toString() {
        return "SimpleArrayList{" 
                + "container=" + Arrays.toString(container) 
                + ", size=" + size 
                + ", modCount=" + modCount 
                + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int cursor = 0;
            private final int expectedModCount = modCount;

            public final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("Array was changed while iteration ");
                }
            }

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                checkForComodification();
                int i = cursor;
                if (i >= size) {
                    throw new NoSuchElementException("Out of bounds");
                }
                T[] containerData = SimpleArrayList.this.container;
                if (i >= containerData.length) {
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                return containerData[i];
            }

        };
    }
}
