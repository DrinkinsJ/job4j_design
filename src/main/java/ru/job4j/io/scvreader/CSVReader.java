package ru.job4j.io.scvreader;

import ru.job4j.io.ArgsName;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static final String LS = System.lineSeparator();
    private static List<String> filter;
    private static String delimiter;
    private static String source;
    private static String stdout;

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Enter right arguments");
        }
        handle(ArgsName.of(args));
    }

    public static void handle(ArgsName argsName) throws IOException {
        CSVReader csvReader = new CSVReader();
        csvReader.validate(argsName);
        stdout = argsName.get("out");
        source = argsName.get("path");
        delimiter = argsName.get("delimiter");
        filter = List.of(argsName.get("filter").split(","));
        if ("stdout".equals(stdout)) {
            System.out.print(csvReader.csvToList());
        } else {
            csvReader.resultFile(csvReader.csvToList());
        }
    }

    private StringBuilder csvToList() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Integer> indexes = indexOfFilter(filter);
        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(source)))) {
            while (scanner.hasNextLine()) {
                List<String> str = List.of(scanner.nextLine().split(delimiter));
                for (Integer index : indexes) {
                    sb.append(str.get(index)).append(delimiter);
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1).append(LS);
                }
            }
        }
        return sb;
    }

    private List<Integer> indexOfFilter(List<String> filter) throws FileNotFoundException {
        List<Integer> indexes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(source)))) {
            List<String> tableHead = List.of(scanner.nextLine().split(delimiter));
            for (String key : filter) {
                int index = tableHead.indexOf(key);
                if (index != -1) {
                    indexes.add(index);
                }
            }
        }
        return indexes;
    }

    private void resultFile(StringBuilder sb) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(stdout))) {
            bw.write(String.valueOf(sb));
        }
    }

    public void validate(ArgsName argsName) {
        if (!new File(argsName.get("path")).exists() && !argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Output file must be .csv");
        }
        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Delimiter cant be empty");
        }
        if (!"stdout".equals(argsName.get("out")) && !argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Out param can be path or stdout");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Filter param cant be empty");
        }
    }

}