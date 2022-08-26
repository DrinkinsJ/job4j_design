package ru.job4j.iterator;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3)
                         .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3)
                         .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void removeIfOne() {
        ListUtils.removeIf(input, i -> i == 1);
        assertThat(input).hasSize(1)
                         .containsSequence(3);
    }

    @Test
    void removeIfEvenAndContainsMoreOneNumbers() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, i -> i % 2 == 0);
        assertThat(input).hasSize(2)
                         .containsSequence(1, 3);
    }

    @Test
    void whenNothingRemove() {
        ListUtils.removeIf(input, i -> i > 5);
        assertThat(input).hasSize(2)
                         .containsSequence(1, 3);
    }

    @Test
    void replaceIfOneByTwo() {
        ListUtils.replaceIf(input, i -> i == 1, 2);
        assertThat(input).hasSize(2)
                         .containsSequence(2, 3);
    }

    @Test
    void replaceIfEvenByOne() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, i -> i % 2 == 0, 1);
        assertThat(input).hasSize(4)
                         .containsSequence(1, 1, 3, 1);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2)
                         .containsSequence(input);
    }

    @Test
    void whenRemoveAllElementsFromInput() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).isEmpty();
    }
}