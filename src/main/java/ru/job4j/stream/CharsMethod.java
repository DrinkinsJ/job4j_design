package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;


public class CharsMethod {
    public static List<Character> symbols(String string) {
        
        return  string.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }
}
