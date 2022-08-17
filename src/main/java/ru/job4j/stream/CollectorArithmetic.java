package ru.job4j.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class CollectorArithmetic {
    public static Integer collect(List<Integer> list) {
        Supplier<List<Integer>> supplier = LinkedList::new;
        BiConsumer<List<Integer>, Integer> consumer = List::add;
        BinaryOperator<List<Integer>> merger = (xs, ys) -> {
            xs.addAll(ys);
            return xs;
        };
        Function<List<Integer>, Integer> function = ns -> {
            int number = 1;
            for (Integer n : ns) {
                number *= n;
            }
            return number;
        };
        return list.stream()
                   .collect(Collector.of(supplier, consumer, merger, function));
    }
}
