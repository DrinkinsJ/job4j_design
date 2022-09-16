package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> fileProperties = new HashSet<>();

    public List<FileProperty> getDuplicates() {
        return duplicates;
    }

    private final List<FileProperty> duplicates = new ArrayList<>(); 

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().getTotalSpace(), file.getFileName().toString());
        if (!fileProperties.add(currentFile)) {
            duplicates.add(currentFile);
        }
        return super.visitFile(file, attrs);
    }
}
