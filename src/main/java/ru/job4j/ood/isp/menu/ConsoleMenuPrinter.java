package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    public static final String DELIMITER = "----";
    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            String[] spl = menuItemInfo.getNumber().split("\\.");
            int len = spl.length;
            if (len > 1) {
                String totalIndent = DELIMITER.repeat(len - 1);
                System.out.print(totalIndent);
            }
            System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName());
        });
    }
}
