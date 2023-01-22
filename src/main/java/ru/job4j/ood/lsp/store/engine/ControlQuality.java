package ru.job4j.ood.lsp.store.engine;

import ru.job4j.ood.lsp.store.model.Store;
import ru.job4j.ood.lsp.store.product.Food;

import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> list) {
        this.stores = list;
    }

    public void addToStorage(Food product) {
        for (Store store : stores) {
            if (store.add(product)) {
                System.out.println(product + " was added to " + store.getClass().getSimpleName());
                break;
            }
        }
    }
}
