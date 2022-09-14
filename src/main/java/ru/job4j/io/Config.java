package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
             read.lines()
                .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                .map(e -> e.split("=", 2))
                .forEach(e -> {
                    if (e.length == 2 && (!e[0].isBlank() && !e[1].isBlank())) {
                        values.put(e[0], e[1]);
                    } else {
                        throw new IllegalArgumentException(Arrays.toString(e));
                    }
                });
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No value");
        } 
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                .forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
