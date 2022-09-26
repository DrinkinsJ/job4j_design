package ru.job4j.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .forEach(e -> values.put(e[0].substring(1), e[1]));
    }

    private boolean validate(String param) {
        if (!param.startsWith("-") && !param.contains("=") && !param.contains("-=")) {
            throw new IllegalArgumentException(String.format("error param %s", param));
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