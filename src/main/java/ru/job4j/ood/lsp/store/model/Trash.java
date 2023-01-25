package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    private final ExpirationCalculator<LocalDate> expirationCalculator;

    public Trash(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    public boolean isFresh(Food food) {
        return expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate()) < TRASH_CONDITION;
    }
}
