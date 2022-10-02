package ru.job4j.io.scvreader;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
    private static final String SOURCE = "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\scvreader\\data"
            + ".csv";
    private static final String DELIMITER_DEFAULT = "|\n";
    private static final String LS = System.lineSeparator();

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
    public static void handle(ArgsName argsName) throws IOException {
        String delimiter = argsName.get("delimiter");
        List<String> filter = List.of(argsName.get("filter"));
        System.out.println(filter);
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(SOURCE))).useDelimiter(delimiter
                + DELIMITER_DEFAULT);
        List<String> list = new ArrayList<>();
        
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
        for (int i = 0; i < list.size(); i++) {
            if (i % 4 == 0) {
                System.out.println(list.get(i));   
            }
        }
        System.out.println("size " + list.size());
        
    }
}