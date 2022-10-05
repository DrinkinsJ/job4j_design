package ru.job4j.io.scvreader;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CSVReaderTest {
    public static final String DATA = String.join(
            System.lineSeparator(),
            "name;age;last_name;education",
            "Tom;20;Smith;Bachelor",
            "Jack;25;Johnson;Undergraduate",
            "William;30;Brown;Secondary special"
    );

    @Test
    void whenFilterTwoColumns(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv")
                          .toFile();
        File target = folder.resolve("target.csv")
                            .toFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(target.getAbsolutePath());
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,education"});
        Files.writeString(file.toPath(), DATA);
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterThreeColumns(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"
        });
        Files.writeString(file.toPath(), DATA);
        String expected = String.join(
                System.lineSeparator(),
                "education;age;last_name",
                "Bachelor;20;Smith",
                "Undergraduate;25;Johnson",
                "Secondary special;30;Brown"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenTwoColumnsConsoleOut(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv").toFile();
        Files.writeString(file.toPath(), DATA);
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=stdout", "-filter=name,education"
        });
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        try (PrintStream sysOut = new PrintStream(outBuf, false, StandardCharsets.UTF_8)) {
            System.setOut(sysOut);
            CSVReader.handle(argsName);
        }
        String output = outBuf.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, Objects.requireNonNull(output));
    }

    @Test
    void whenThreeColumnsConsoleOut(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv").toFile();
        Files.writeString(file.toPath(), DATA);
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=stdout", "-filter=education,age,last_name"
        });
        String expected = String.join(
                System.lineSeparator(),
                "education;age;last_name",
                "Bachelor;20;Smith",
                "Undergraduate;25;Johnson",
                "Secondary special;30;Brown"
        )
                                .concat(System.lineSeparator());
        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        try (PrintStream sysOut = new PrintStream(outBuf, false, StandardCharsets.UTF_8)) {
            System.setOut(sysOut);
            CSVReader.handle(argsName);
        }
        String output = outBuf.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, Objects.requireNonNull(output));
    }

    @Test
    void whenNotEnoughParams(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv")
                          .toFile();
        Files.writeString(file.toPath(), DATA);
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
        });
        assertThatThrownBy(() -> CSVReader.handle(argsName)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void whenSourceEmpty(@TempDir Path folder) throws IOException {
        File file = folder.resolve("source.csv").toFile();
        Files.createFile(file.toPath());
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=stdout", "-filter=education,age,last_name"
        });
        assertThatThrownBy(() -> CSVReader.handle(argsName)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenPathEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{
                "-path=", "-delimiter=;",
                "-out=stdout", "-filter=education,age,last_name"
        })).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenDelimiterEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{
                "-path=notEmpty", "-delimiter=",
                "-out=stdout", "-filter=education,age,last_name"
        })).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOutEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{
                "-path=notEmpty", "-delimiter=;",
                "-out=", "-filter=education,age,last_name"
        })).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFilterEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{
                "-path=notEmpty", "-delimiter=;",
                "-out=stdout", "-filter="
        })).isInstanceOf(IllegalArgumentException.class);
    }
}