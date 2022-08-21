package ru.job4j.stream;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class UserMapTest {

    @Test
    public void test() {
        List<Integer> ages = List.of(1, 2);
        List<UserMap.User> result = UserMap.map(ages);
        Iterator<UserMap.User> iterator = result.iterator();
        assertEquals(1, iterator.next()
                                .getAge());
        assertEquals(2, iterator.next()
                                .getAge());
    }
}