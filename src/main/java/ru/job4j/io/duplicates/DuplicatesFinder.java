package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        findDuplicates(Path.of("./")).forEach(System.out::println);
    }

    public static List<FileProperty> findDuplicates(Path root) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(root, duplicatesVisitor);
        return duplicatesVisitor.getDuplicates();
    }
}
