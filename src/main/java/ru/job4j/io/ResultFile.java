package ru.job4j.io;

import java.io.*;

public class ResultFile {
    public  static final int SIZE = 9;
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < SIZE; i++) {
                out.write(System.lineSeparator().getBytes());
                for (int j = 0; j < SIZE; j++) {
                    String s = (j + 1) * (i + 1) + "\t";
                    out.write(s.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
