package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    private boolean flag = true;
    private final StringBuilder sb = new StringBuilder();
    private final List<String> offStatus = Arrays.asList("400", "500");
    private final List<String> onStatus = Arrays.asList("200", "300");


    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", "data/unavailable.csv");
    }

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
              .filter(e -> !e.isEmpty() && e.contains(" "))
              .map(e -> e.split(" ", 2))
              .forEach(e -> {
                  if (offStatus.contains(e[0]) && flag) {
                      flag = false;
                      sb.append(e[1]).append(";");
                  }
                  if (onStatus.contains(e[0]) && !flag) {
                      flag = true;
                      sb.append(e[1]).append(System.lineSeparator());
                  }
              });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
