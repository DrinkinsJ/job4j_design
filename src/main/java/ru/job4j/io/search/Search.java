package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (Objects.isNull(args[0]) || Objects.isNull(args[1])) {
            throw new IllegalArgumentException("Root folder is null or file.name. Usage: -jar C:\\ txt ");
        }
        validate(args[0], args[1]);
    }

    public static void validate(String s1, String s2) throws IOException {
        if (!s1.contains(":\\") || s1.isBlank()) {
            throw new IllegalArgumentException("Not correct path");
        }
        if (s2.isBlank()) {
            throw new IllegalArgumentException("File name cant be empty");
        }
        Path start = Paths.get(s1);
        search(start, p -> p.toFile().getName().endsWith(s2)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
