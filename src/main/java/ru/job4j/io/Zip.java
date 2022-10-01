package ru.job4j.io;

import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class Zip {

    public static void main(String[] args) throws IOException {
        List<Path> sources;
        if (args.length != 3) {
            throw new IllegalArgumentException("Enter right arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        File output = new File(argsName.get("o"));
        Path path = Paths.get(argsName.get("d"));
        sources = new ArrayList<>(Search.search(path, p -> !p.toFile()
                                                             .getName()
                                                             .endsWith(argsName.get("e"))));
        Zip zip = new Zip();
        zip.packFiles(sources, output);
    }

    public void packFiles(List<Path> sources, File target) throws IOException {
        List<File> files = new ArrayList<>();
        for (Path path : sources) {
            files.add(path.toFile());
        }
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : files) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }
}