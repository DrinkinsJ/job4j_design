package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    List<Food> foods = new ArrayList<>();
    ExpirationCalculator expirationCalculator = new ExpirationCalculator();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (isFresh(food, expirationCalculator)) {
            foods.add(food);
            result = true;
        }
        return result;
    }

    public abstract boolean isFresh(Food food, ExpirationCalculator expirationCalculator);

    public List<Food> getAllFood() {
        return new ArrayList<>(foods);
    }
 }
