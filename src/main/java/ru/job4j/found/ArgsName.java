package ru.job4j.found;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Not contain key");
        } else {
            return values.get(key);
        }
    }

    private void parse(String[] args) {
        List<String> stings = List.of(args);
        if (stings.isEmpty()) {
            throw new IllegalArgumentException("Empty params");
        }
        stings.stream()
                .map(String::trim)
                .filter(this::validate)
                .map(e -> e.split("=", 2))
                .forEach(e -> values.put(Objects.requireNonNull(e[0].substring(1)), Objects.requireNonNull(e[1])));
    }

    private boolean validate(String s) {
        if (!s.startsWith("-") || s.startsWith("-=") || !s.contains("=")) {
            throw new IllegalArgumentException(String.format("error param %s", s));
        }
        if (s.indexOf("=") == s.length() - 1) {
            throw new IllegalArgumentException(String.format("error param %s", s));
        }
        return true;
    }
}