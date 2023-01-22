package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

public class Trash extends AbstractStore {

    @Override
    public boolean isFresh(Food food, ExpirationCalculator expirationCalculator) {
        return expirationCalculator.calculatorExtDate(food.getCreateDate(), food.getExpiryDate()) < 0;
    }

}
