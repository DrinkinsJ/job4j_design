package ru.job4j.ood.lsp.store.calculator;

public class DiscountCalculator {

    public double discount(double discount, double price) {
        return (1 - discount / 100) * price;
    }

}
