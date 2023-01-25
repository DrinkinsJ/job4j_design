package ru.job4j.ood.lsp.store.calculator;

public interface ExpirationCalculator<T> {
    double calculateInPercent(T startDate, T endDate);
}