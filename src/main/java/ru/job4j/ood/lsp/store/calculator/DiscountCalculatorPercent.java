package ru.job4j.ood.lsp.store.calculator;

public class DiscountCalculatorPercent implements DiscountCalculator {
    @Override
    public double calculateInPercent(double discount, double price) {
        return (1 - discount / 100) * price;
    }
}
