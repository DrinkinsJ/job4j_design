package ru.job4j.ood.srp.examples;

/**
 * Нарушение SRP. Неявное преобразование.
 */
public class DocScanner implements DocScan {
    Object o;

    @Override
    public Object scan() {
        return o.toString() + "something";
    }
}
