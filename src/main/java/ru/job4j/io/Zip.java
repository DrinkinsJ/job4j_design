package ru.job4j.io;

import ru.job4j.io.search.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    
    private String directory;
    private String output;
    private String exclude;
    private Path target;
    private List<Path> sources;


    public Zip(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        this.directory = argsName.get("d");
        this.output = argsName.get("o");
        this.exclude = argsName.get("e");
        Path start = Paths.get(directory);
        Search.search(start, p -> p.toFile().getName().contains(exclude)).forEach(e -> sources.add(e));
        System.out.println(sources.toString());
    }

    public Zip() {
    }

    public void packFiles(List<Path> sources, File target) throws IOException {
        
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        Zip zip2 = new Zip(args);
        System.out.println(zip2.sources.toString());
    }
}