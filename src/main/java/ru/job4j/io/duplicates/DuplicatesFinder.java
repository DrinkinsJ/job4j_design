package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}
