package ru.job4j.io;

import java.util.*;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw  new IllegalArgumentException("Not contain key");
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
        if (s.indexOf("=") == s.lastIndexOf("=") && s.endsWith("=")) {
            throw new IllegalArgumentException(String.format("error param %s", s));
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}