package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizyTest {

    @Test
    void testWithCurrentServerLog(@TempDir Path tempDir) throws IOException {
        File target = tempDir.resolve("source.txt")
                             .toFile();
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
              .forEach(rsl::append);
        }
        assertThat("10:58:01;10:59:0111:01:02;11:03:02").isEqualTo(rsl.toString());
    }

    @Test
    void testWithTestLog(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log")
                             .toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:58:01");
            out.println("200 10:59:01");
        }
        File target = tempDir.resolve("target.txt")
                             .toFile();
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
              .forEach(rsl::append);
        }
        assertThat("10:58:01;10:59:01").isEqualTo(rsl.toString());
    }
}