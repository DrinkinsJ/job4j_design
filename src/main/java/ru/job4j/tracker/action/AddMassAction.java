package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

public class AddMassAction implements UserAction {
    @Override
    public String name() {
        return "=== Create MASS new Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 0; i < 1_000_000; i++) {
            Item item = new Item(" " + i);
            tracker.add(item);
        }
        System.out.println("Items successfully added!");
        return true;
    }
}
