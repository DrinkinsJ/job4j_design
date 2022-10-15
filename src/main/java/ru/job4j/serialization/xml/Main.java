package ru.job4j.serialization.xml;

import javax.xml.bind.*;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        final Worker worker = new Worker(true, 10, new Contact(654321, "+7(926)323-45-65"),
                new String[]{"Java", "io", "serialization"});
        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(worker, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
