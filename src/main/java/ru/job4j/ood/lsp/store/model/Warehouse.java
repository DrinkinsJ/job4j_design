package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {

    private final ExpirationCalculator<LocalDate> expirationCalculator;

    public Warehouse(ExpirationCalculator<LocalDate> expirationCalculator) {
    this.expirationCalculator = expirationCalculator;
    }

    @Override
    public boolean isFresh(Food food) {
        double calc = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return calc > GOOD_CONDITION;
    }
}
