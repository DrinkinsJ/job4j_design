package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        long totalSize = 0L;
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            totalSize += subfile.length();
            long d = subfile.length();
            System.out.printf("file name: %s, size: %d byte%n", subfile.getName(), d);
        }
        System.out.printf("File: %s, size : %s, byte%n", file.getName(), totalSize);
    }
}