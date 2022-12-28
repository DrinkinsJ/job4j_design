package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    static private final String USER_MENU = """
            1 - загрузить содержимое файла в кэш
            2 - получить содержимое файла из кэша
            0 - выход""";

    static private final String ASK_FILE_NAME = "Введите название файла: ";
    static private final String CACHED = "Файл загружен в кэш. ";
    static private final int USER_CHOICE_EXIT = 0;
    static private final int USER_CHOICE_SAVE = 1;
    static private final int USER_CHOICE_LOAD = 2;
    static private final String EXIT = "Выход";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = collectCache(scanner);
        Emulator emulator = new Emulator();
        emulator.start(dirFileCache, scanner);
    }

    private static DirFileCache collectCache(Scanner scanner) {
        System.out.println("Введите директорию: ");
        String res = scanner.nextLine();
        return new DirFileCache(res);
    }

    private void start(DirFileCache dirFileCache, Scanner scanner) {
        System.out.println(USER_MENU);
        boolean run = true;
        while (run) {
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == USER_CHOICE_SAVE) {
                System.out.println(ASK_FILE_NAME);
                dirFileCache.get(scanner.nextLine());
                System.out.println(CACHED);
                System.out.println(USER_MENU);
            } else if (userChoice == USER_CHOICE_LOAD) {
                System.out.println(ASK_FILE_NAME);
                System.out.println(dirFileCache.get(scanner.nextLine()));
                System.out.println(USER_MENU);
            } else if (userChoice == USER_CHOICE_EXIT) {
                System.out.println(EXIT);
                run = false;
            }
        }
    }
}
