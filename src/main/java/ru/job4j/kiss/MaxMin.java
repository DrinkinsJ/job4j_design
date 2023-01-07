package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findExtremum(value, (el1, el2) ->  comparator.compare(el1, el2) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findExtremum(value, (el1, el2) ->  comparator.compare(el1, el2) < 0);
    }

    public <T> T findExtremum(List<T> value, BiPredicate<T, T> biPredicate) {
        if (value.isEmpty()) {
            return  null;
        }
        T result = value.get(0);
        for (T t : value) {
            if (biPredicate.test(result, t)) {
                result = t;
            }
        }
        return result;
    }
}