package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    public static final ActionDelegate COMMON_ACTION = System.out::println;
    public static final int USER_CHOICE_ADD_ROOT = 1;
    public static final int USER_CHOICE_ADD_CHILD = 2;
    public static final int USER_CHOICE_DEFAULT_ACTION = 3;
    public static final int USER_CHOICE_PRINT = 4;

    public static final String MENU_INFO = "ENTER 1 FOR ADD ROOT NOTE\n"
            + "ENTER 2 FOR ADD CHILD NOTE\n"
            + "ENTER 3 FOR DEFAULT ACTION\n"
            + "ENTER 4 FOR PRINT NOTES\n"
            + "ENTER ANY KEY FOR EXIT\n";

    public static void main(String[] args) {
        System.out.println(MENU_INFO);
        TodoApp app = new TodoApp();
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        app.start(scanner, menu);
    }

    private void start(Scanner scanner, Menu menu) {
        boolean run = true;
        String name = "";
        while (run) {
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == USER_CHOICE_ADD_ROOT) {
                System.out.println("Enter element name");
                name = scanner.nextLine();
                menu.add(Menu.ROOT, name, COMMON_ACTION);
                System.out.println(name + " was added to root.");
            } else if (userChoice == USER_CHOICE_ADD_CHILD) {
                System.out.println("Enter ROOT element name");
                name = scanner.nextLine();
                System.out.println("Enter CURRENT element name");
                String currName = scanner.nextLine();
                menu.add(name, currName, COMMON_ACTION);
                System.out.printf("%s was added to %s", currName, name);
            } else if (userChoice == USER_CHOICE_DEFAULT_ACTION) {
                DEFAULT_ACTION.delegate();
            } else if (userChoice == USER_CHOICE_PRINT) {
                MenuPrinter menuPrinter = new ConsoleMenuPrinter();
                menuPrinter.print(menu);
            } else {
                run = false;
                System.out.println("End.");
            }
        }

    }
}
