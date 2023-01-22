package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

import java.util.List;

public interface Store {

    boolean add(Food product);
    List<Food> getAllFood();

}
