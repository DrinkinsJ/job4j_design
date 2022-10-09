package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

public class Main {
    public static void main(String[] args) {
        final Worker worker = new Worker(true, 10, new Contact(654321, "+7(926)323-45-65"),
                new String[] {"Java", "io", "serialization"});
        
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(worker));
        
        final String workerJson =
                "{"
                        + "\"isActive\":false,"
                        + "\"salary\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":55555,"
                        + "\"phone\":\"+7(924)123-45-67\""
                        + "},"
                        + "\"skills\":"
                        + "[\"Jdbc\",\"Collections\",\"Maven\"]"
                        + "}";
        final Worker workerMod = gson.fromJson(workerJson, Worker.class);
        System.out.println(workerMod);
    }
}