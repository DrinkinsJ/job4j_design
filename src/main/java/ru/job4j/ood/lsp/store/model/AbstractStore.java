package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.product.Food;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private final List<Food> foods = new ArrayList<>();
    public static final double TRASH_CONDITION = 0d;
    public static final double MIN_CONDITION = 25d;
    public static final double GOOD_CONDITION = 75d;

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (isFresh(food)) {
            foods.add(food);
            result = true;
        }
        return result;
    }

    public abstract boolean isFresh(Food food);

    public List<Food> getAllFood() {
        return new ArrayList<>(foods);
    }

    public void clear() {
        foods.clear();
    }
 }
