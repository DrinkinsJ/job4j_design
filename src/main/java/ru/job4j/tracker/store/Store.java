package ru.job4j.tracker.store;

import ru.job4j.tracker.model.Item;

import java.util.List;

    public interface Store extends AutoCloseable {
    Item add(Item item);
    boolean replace(int id, Item item);
    boolean delete(int id);
    List<Item> findAll();
    List<Item> findByName(String name);
    Item findById(int id);
}
