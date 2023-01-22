package ru.job4j.ood.lsp.store.calculator;

import java.time.LocalDate;

public class ExpirationCalculator {

    public double calculatorExtDate(LocalDate start, LocalDate end) {
        double daysOfExpiration = end.toEpochDay() - start.toEpochDay();
        double daysUntilFoodWillBeBad = end.toEpochDay() - LocalDate.now().toEpochDay();
        return daysUntilFoodWillBeBad / daysOfExpiration * 100;
    }

}
