package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.DiscountCalculator;
import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;

public class Shop extends AbstractStore {

    DiscountCalculator discountCalculator = new DiscountCalculator();
    @Override
    public boolean isFresh(Food food, ExpirationCalculator expirationCalculator) {

        double condition = expirationCalculator.calculatorExtDate(food.getCreateDate(), food.getExpiryDate());

        if (condition < 25) {
            food.setPrice(discountCalculator.discount(food.getDiscount(), food.getPrice()));
        }

        return condition > 0 && condition <= 75;
    }
}
