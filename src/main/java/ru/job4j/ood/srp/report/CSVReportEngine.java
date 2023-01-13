package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class CSVReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String separator;

    public CSVReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser, String separator) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.separator = separator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(separator)
                    .append(dateTimeParser.parse(employee.getHired())).append(separator)
                    .append(dateTimeParser.parse(employee.getFired())).append(separator)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}