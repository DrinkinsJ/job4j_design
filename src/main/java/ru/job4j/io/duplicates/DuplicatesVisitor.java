package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> fileProperties = new HashSet<>();
    private final List<FileProperty> duplicates = new ArrayList<>();

    public List<FileProperty> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().getTotalSpace(), file.getFileName().toString());
        if (!fileProperties.add(currentFile)) {
            duplicates.add(currentFile);
        }
        return super.visitFile(file, attrs);
    }
}
