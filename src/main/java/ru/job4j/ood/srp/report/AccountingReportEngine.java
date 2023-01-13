package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReportEngine implements Report {

    private final Store store;
    private final CurrencyConverter converter;
    private final Currency target;
    private final DateTimeParser<Calendar> dateTimeParser;

    public AccountingReportEngine(Store store, CurrencyConverter converter, Currency target, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.converter = converter;
        this.target = target;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        Currency currencyFrom = Currency.RUB;
        for (Employee employee : store.findBy(filter)) {
            employee.setSalary(converter.convert(currencyFrom, employee.getSalary(), target));
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
