package ru.job4j.found;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Search {
    private static String type;
    private static String name;
    private String tips = "Program can scan dir with key: -d ";

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("error params");
        }
        handle(ArgsName.of(args));
    }

    private static void handle(ArgsName argsName) throws IOException {
        name = argsName.get("n");
        type = argsName.get("t");
        String path = argsName.get("d");
        String log = argsName.get("o");
        validate(argsName);
        Predicate<Path> predicate = predicate();
        Path start = Paths.get(path);
        List<Path> paths = search(start, predicate);
        saveLog(paths, log);
    }

    private static Predicate<Path> predicate() {
        Predicate<Path> pred = null;
        if (type.equals("name")) {
            pred = p -> p.toFile().getName().equals(name);
        }

        if (type.equals("mask")) {
            pred = p -> p.toFile().getName().matches(name.replace(".", "[.]")
                    .replace("*", ".+").replace("?", "."));
        }

        if (type.equals("regex")) {
            try {
                Pattern pattern = Pattern.compile(name, Pattern.UNICODE_CASE);
                pred = p -> pattern.matcher(p.toFile().getName()).find();
            } catch (PatternSyntaxException e) {
                System.out.println("Pattern exeption: " + e);
            }
        }
        return pred;
    }

    private static void validate(ArgsName argsName) {

        File directory = new File(argsName.get("d"));
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        if (!("name".equals(type) || "mask".equals(type) || "regex".equals(type))) {
            throw new IllegalArgumentException("No type param");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("File name found cant be empty");
        }
        if (!argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("output file must be .txt");
        }
    }

    private static void saveLog(List<Path> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Path p : log) {
                out.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
