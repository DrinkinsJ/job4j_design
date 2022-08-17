package ru.job4j.iterator;

import java.util.*;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int evenIndex;
    int index = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean b = false;
        int pos = index;
        while ((pos < data.length - 1) && !b) {
            pos++;
            if (data[pos] % 2 == 0) {
                evenIndex = pos;
                b = true;
            }
        }
        return b;
    }

    /**
     * @noinspection checkstyle:InnerAssignment
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = evenIndex;
        return data[index];
    }
}
