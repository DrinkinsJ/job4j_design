package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class JSONReportEngineTest {

    @Test
    void whenXMLGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new JSONReportEngine(store);
        String s = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append(String.format("{\"employees\":[{\"name\":\"%s\",", worker.getName()))
                .append(String.format("\"hired\":\"%s\",", parser.parse(worker.getHired())))
                .append(String.format("\"fired\":\"%s\",", parser.parse(worker.getFired())))
                .append(String.format("\"salary\":%s}]}", worker.getSalary()));
        assertThat(engine.generate(em -> true)).isEqualToIgnoringWhitespace(expect.toString());
    }
}