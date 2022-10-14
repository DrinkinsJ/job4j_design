package ru.job4j.serialization.xml;

import ru.job4j.serialization.java.Contact;
import ru.job4j.serialization.json.Worker;

public class Main {
    public static void main(String[] args) {
        final Worker worker = new Worker(true, 10, new Contact(654321, "+7(926)323-45-65"),
                new String[] {"Java", "io", "serialization"});
        System.out.println(worker);
    }
}
