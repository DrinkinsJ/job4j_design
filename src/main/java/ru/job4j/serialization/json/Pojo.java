package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;

import java.util.ArrayList;
import java.util.List;

public class Pojo {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject();
        jsonContact.put("zipCode", 55555);
        jsonContact.put("phone", "+7(924)111-111-11-11");

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("io");
        list.add("serialization");
        JSONArray jsonSkills = new JSONArray(list);

        final Worker worker = new Worker(true, 10, new Contact(55555, "+7(924)111-111-11-11"),
                new String[]{"Java", "io", "serialization"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isActive", worker.isActive());
        jsonObject.put("salary", worker.getSalary());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonSkills);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(worker));
    }
}
