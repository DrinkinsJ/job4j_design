package ru.job4j.ood.lsp.store.model;

import ru.job4j.ood.lsp.store.calculator.DiscountCalculator;
import ru.job4j.ood.lsp.store.calculator.DiscountCalculatorPercent;
import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.product.Food;
import java.time.LocalDate;

public class Shop extends AbstractStore {
    private final ExpirationCalculator<LocalDate> expirationCalculator;
    private final DiscountCalculator discountCalculatorPercent;

    public Shop(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
        this.discountCalculatorPercent = new DiscountCalculatorPercent();
    }

    @Override
    public boolean isFresh(Food food) {
        double condition = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        if (condition < MIN_CONDITION) {
            food.setPrice(discountCalculatorPercent.calculateInPercent(food.getDiscount(), food.getPrice()));
        }
        return condition > TRASH_CONDITION && condition <= GOOD_CONDITION;
    }
}
