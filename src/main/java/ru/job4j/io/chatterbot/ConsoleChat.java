package ru.job4j.io.chatterbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String ANSWERS = "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\chatterbot"
            + "\\ConsoleChatAnswers.txt";
    private static final String LOG =
            "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\chatterbot\\log.txt";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) throws IOException {
        System.out.printf(
                "Управление ботом: %n %s - выключить программу, %n %s -  замутить бота, %n %s - размутить бота%n",
                OUT,
                STOP,
                CONTINUE);
        ConsoleChat cc = new ConsoleChat(LOG, ANSWERS);
        cc.run();
    }

    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s;
        List<String> log = new ArrayList<>();
        Random random = new Random();
        List<String> answers = readPhrases();
        String answer;
        boolean isEnable = true;
        do {
            s = scanner.nextLine();
            log.add(String.format("User: %s" + System.lineSeparator(), s));
            if (STOP.equals(s) || OUT.equals(s)) {
                isEnable = false;
            }
            if (CONTINUE.equals(s)) {
                isEnable = true;
            } else if (isEnable) {
                answer = answers.get(random.nextInt(answers.size()));
                log.add(String.format("Bot: %s" + System.lineSeparator(), answer));
                System.out.println(answer);
            }
        } while (!(OUT.equals(s)));
        saveLog(log);
    }

    private List<String> readPhrases() throws IOException {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers))) {
            in.lines()
                    .forEach(phrases::add);
        }
        return phrases;
    }

    private void saveLog(List<String> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String str : logs) {
                bw.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
