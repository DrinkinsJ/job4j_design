package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XMLReportEngine implements Report {

    private final Store store;
    private final Marshaller marshaller;

    public XMLReportEngine(Store store) throws JAXBException {
        this.store = store;
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        this.marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        String xml = "";
        try (StringWriter stringWriter = new StringWriter()) {
            marshaller.marshal(employees, stringWriter);
            xml = stringWriter.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
