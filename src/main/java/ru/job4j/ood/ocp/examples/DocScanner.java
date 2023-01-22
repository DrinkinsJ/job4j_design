package ru.job4j.ood.ocp.examples;

/**
 * Нарушение OCP(open-closed principles), не стоит создавать объект класса без интерфейса или абстактного класса
 * Если понадобится добавить напрмер лазерный принтер и струный, придется переделывать все классы.
 * Правильнее было создать интерфейс Printer после чего создавать принтер через интерфейс.
 */

public class DocScanner {
    void scanDoc() {
        ColorPrinter printer = new ColorPrinter();
    }

    static class ColorPrinter {
        public void print() {
        }
    }
}


