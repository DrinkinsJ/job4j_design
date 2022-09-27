package ru.job4j.io.search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null or file.name. Usage: -jar C:\\ txt ");
        }
        validate(args[0], args[1]);
    }

    public static void validate(String s1, String s2) throws IOException {
        File file = new File(s1);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!s2.startsWith(".")) {
            throw new IllegalArgumentException("File format must start with dot");
        }
        Path start = Paths.get(file.getName());
        search(start, p -> p.toFile().getName().endsWith(s2)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
