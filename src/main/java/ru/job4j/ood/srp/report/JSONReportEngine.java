package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarAdapterJSON;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JSONReportEngine implements Report {
    private final Store store;
    private final Gson gson;


    public JSONReportEngine(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().registerTypeAdapter(GregorianCalendar.class, new CalendarAdapterJSON())
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        return gson.toJson(employees);
    }
}
