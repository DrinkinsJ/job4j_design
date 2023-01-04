package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {
    private final List<Integer> list = List.of(1, -5, 2, 22, -22);
    private final List<Integer> empty = List.of();
    private  final Comparator<Integer> comparator = (val1, val2) -> val1 > val2 ? -1 : 1;

    @Test
    void whenReturnMaxValue() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(list, comparator)).isEqualTo(22);
    }
    @Test
    void whenReturnMinValue() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(list, comparator)).isEqualTo(-22);
    }
    @Test
    void whenMinReturnNullValue() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(empty, comparator)).isEqualTo(null);
    }
    @Test
    void whenMaxReturnNullValue() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(empty, comparator)).isEqualTo(null);
    }
}