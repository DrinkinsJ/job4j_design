package ru.job4j.iterator;

import java.util.*;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index = -1;
    private final int size;
    private int evenIndex;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    @Override
    public boolean hasNext() {
        boolean b = false;
        for (int i = index + 1; i < size; i++) {
            if (data[i] % 2 == 0) {
                evenIndex = i;
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = evenIndex;
        return data[index];
    }
}
