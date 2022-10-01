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
        validate(argsName);
        File output = new File(argsName.get("o"));
        Path path = Paths.get(argsName.get("d"));
        sources = new ArrayList<>(Search.search(path, p -> !p.toFile()
                                                             .getName()
                                                             .endsWith(argsName.get("e"))));
        Zip zip = new Zip();
        zip.packFiles(sources, output);
    }

    private static void validate(ArgsName argsName) {
        if (!new File(argsName.get("o")).canWrite() || !argsName.get("o")
                                                              .endsWith(".zip")) {
            throw new IllegalArgumentException("Output file must be .zip");
        }
        if (!new File(argsName.get("d")).isDirectory()) {
            throw new IllegalArgumentException("Not directory");
        }
        if (!argsName.get("e")
                     .contains(".class")) {
            throw new IllegalArgumentException("exclude must end .class");
        }
    }

    public void packFiles(List<Path> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile()
                                                  .getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()
                                                                                               .getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }
}