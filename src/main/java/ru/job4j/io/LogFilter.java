package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> log = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(s -> s.contains(" 404 ")).forEach(log::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> list = logFilter.filter("log.txt");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
