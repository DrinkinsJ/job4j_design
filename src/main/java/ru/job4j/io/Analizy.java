package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    boolean flag = true;
    StringBuilder sb = new StringBuilder();

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
                  if ((e[0].equals("400") || e[0].equals("500")) && flag) {
                      flag = false;
                      sb.append(e[1])
                        .append(";");
                  }
                  if ((e[0].equals("200") || e[0].equals("300")) && !flag) {
                      flag = true;
                      sb.append(e[1])
                        .append(System.lineSeparator());
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
