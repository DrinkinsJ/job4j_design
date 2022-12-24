package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.util.ArrayList;
import java.util.List;

public class DeleteMassAction implements UserAction {

    @Override
    public String name() {
        return "=== Delete MASS Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = new ArrayList<>(tracker.findAll());
        for (Item item : items) {
            tracker.delete(item.getId());
        }
        System.out.println("Items is successfully deleted!");
        return true;
    }
}
