package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> fileProperties = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile()
                                                        .getTotalSpace(), file.getFileName()
                                                                              .toString());
        if (fileProperties.contains(currentFile)) {
            System.out.println(currentFile.getName());
        } else {
            fileProperties.add(currentFile);
        }
        return super.visitFile(file, attrs);
    }
}
