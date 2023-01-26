package ru.job4j.ood.lsp.store.calculator;

import java.time.LocalDate;

public class LocalDateExpirationCalculator implements ExpirationCalculator<LocalDate> {
    @Override
    public double calculateInPercent(LocalDate start, LocalDate end) {
        double daysOfExpiration = end.toEpochDay() - start.toEpochDay();
        double daysUntilFoodWillBeBad = end.toEpochDay() - LocalDate.now().toEpochDay();
        return daysUntilFoodWillBeBad / daysOfExpiration * 100d;
    }
}