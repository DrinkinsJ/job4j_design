package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        Config config = new Config("data/noKey.properties");
        config.load();
        for (Map.Entry<String, String> entry : config.values.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(config.value(""));
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                .filter(e -> e.contains("=") && !e.contains("#"))
                .map(e -> e.split("=", 2))
                .forEach(e -> values.put(e[0] != null ? e[0] : null, e[1] != null ? e[0] : null));
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