package ru.job4j.io;

import java.io.*;

public class Analizy {
    private boolean flag = true;
    private final StringBuilder sb = new StringBuilder();
    
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
                  if (("400".equals(e[0]) || "500".equals(e[0])) == flag) {
                      sb.append(e[1])
                        .append(flag ? ';' : System.lineSeparator());
                      flag = !flag;
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
