package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingReportEngineTest {

    @Test
    void whenAccountantGeneratedAndConverted() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker = new Employee("Oleg", now, now, 100);
        Employee workerConverted = new Employee("Oleg", now, now, converter.convert(Currency.RUB, worker.getSalary(),
                Currency.USD));
        store.add(worker);
        Report engine = new AccountingReportEngine(store, converter, Currency.USD, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerConverted.getName()).append(" ")
                .append(parser.parse(workerConverted.getHired())).append(" ")
                .append(parser.parse(workerConverted.getFired())).append(" ")
                .append(workerConverted.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

}